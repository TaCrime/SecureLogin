package com.tatesttask.service.impl;

import com.tatesttask.service.PasswordStrengthService;
import com.tatesttask.util.PasswordStrengths;

public class PasswordStrengthServiceImpl implements PasswordStrengthService {
    @Override
    public String getPasswordStrength(String password) {
        if (password == null) {
            return PasswordStrengths.NONE.getValue();
        } else if (password.length() <= 4) {
            return PasswordStrengths.WEAK.getValue();
        } else if (password.length() < 8) {
            return PasswordStrengths.MEDIUM.getValue();
        } else {
            return PasswordStrengths.STRONG.getValue();
        }
    }

    @Override
    public boolean isNotPasswordStrong(String password) {
        if (getPasswordStrength(password).equals(PasswordStrengths.STRONG.getValue())) {
            return false;
        }
        return true;

    }
}
