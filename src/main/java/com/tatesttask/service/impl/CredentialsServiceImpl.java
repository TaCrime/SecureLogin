package com.tatesttask.service.impl;

import com.tatesttask.model.Credential;
import com.tatesttask.service.CredentialsService;
import com.tatesttask.util.HibernateUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class CredentialsServiceImpl implements CredentialsService {

    private static final Log log = LogFactory.getLog(HibernateUtil.class);

    @Override
    public Credential getUserByLogin(String login) {
        Session session = HibernateUtil.openSession();
        Transaction transaction = null;
        Credential credential = null;
        try {
            transaction = session.getTransaction();
            transaction.begin();
            Query query = session.createQuery("from Credential where login=:login");
            query.setParameter("login", login);
            query.setMaxResults(1);
            credential = (Credential)query.uniqueResult();
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.error("Get user by login failed ." + ex.getMessage());
        } finally {
            session.close();
        }
        return credential;
    }
}
