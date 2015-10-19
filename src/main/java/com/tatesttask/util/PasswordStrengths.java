package com.tatesttask.util;

public enum PasswordStrengths {
    STRONG("strong"),
    MEDIUM("medium"),
    WEAK("weak"),
    NONE("")
    ;
    private final String strengthValue;

    private PasswordStrengths(String value) {
        this.strengthValue = value;
    }

    public String getValue() {
        return strengthValue;
    }


}

