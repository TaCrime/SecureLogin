package com.tatesttask.service.impl;

import com.tatesttask.model.Credential;
import com.tatesttask.service.CredentialsService;
import com.tatesttask.service.CredentialsVerifier;
import com.tatesttask.service.RegisterService;
import com.tatesttask.util.ApplicationContextProvider;
import org.springframework.stereotype.Service;

public class CredentialsVerifierImpl implements CredentialsVerifier {
    private CredentialsService credentialsService;
    @Override
    public boolean isValidCreadential(String login, String password) {
        Credential credential = credentialsService.getUserByLogin(login);
        if (credential != null && credential.getPassword().equals(password)) {
            return true;
        } else {
            return false;
        }
    }

    public void setCredentialsService(CredentialsService credentialsService) {
        this.credentialsService = credentialsService;
    }
}
