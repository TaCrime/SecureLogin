package com.tatesttask.service;

public interface PasswordStrengthService {
    public String getPasswordStrength(String password);
    public boolean isNotPasswordStrong(String password);
}
