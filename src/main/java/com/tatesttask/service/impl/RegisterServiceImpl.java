package com.tatesttask.service.impl;

import com.tatesttask.model.Credential;
import com.tatesttask.service.CredentialsService;
import com.tatesttask.service.PasswordStrengthService;
import com.tatesttask.service.RegisterService;
import com.tatesttask.util.HibernateUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class RegisterServiceImpl implements RegisterService {

    private static final Log log = LogFactory.getLog(RegisterServiceImpl.class);
    private CredentialsService credentialsService;
    private PasswordStrengthService passwordStrengthService;

    @Override
    public boolean register(Credential credential) throws Exception {
        Session session = HibernateUtil.openSession();
        if (StringUtils.isBlank(credential.getLogin())) {
            throw new Exception("Empty login");
        }
        if (StringUtils.isBlank(credential.getPassword())) {
            throw new Exception("Empty password");
        }
        if(ifCredentialExists(credential)) {
            return false;
        }
        if (isNotPasswordStrong(credential)) {
            return false;
        }
        Transaction transaction = null;
        try {
            transaction = session.getTransaction();
            transaction.begin();
            session.saveOrUpdate(credential);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.error("Register credential failed." + ex.getMessage());
        } finally {
            session.close();
        }
        return true;
    }

    @Override
    public boolean ifCredentialExists(Credential credentialToVerify) throws Exception {
        Credential credential = credentialsService.getUserByLogin(credentialToVerify.getLogin());
        if (credential != null) {
            throw new Exception("Login exists");
        }
        return false;
    }

    private boolean isNotPasswordStrong(Credential credentialToVerify) throws Exception {
        if (passwordStrengthService.isNotPasswordStrong(credentialToVerify.getPassword())) {
            throw new Exception("Password is not strong!");
        }
        return false;
    }

    public void setCredentialsService(CredentialsService credentialsService) {
        this.credentialsService = credentialsService;
    }

    public void setPasswordStrengthService(PasswordStrengthService passwordStrengthService) {
        this.passwordStrengthService = passwordStrengthService;
    }
}
