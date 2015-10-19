package com.tatesttask.service;

import com.tatesttask.model.Credential;

public interface RegisterService {

    public boolean register(Credential credential) throws Exception;
    public boolean ifCredentialExists(Credential credential) throws Exception;
}
