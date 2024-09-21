package com.kuyajon.learningportal.model.sys;

public enum ERole {
    ADMIN("System Admin"),
    STUDENT("Student");

    private final String name;

    ERole(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
