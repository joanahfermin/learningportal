package com.kuyajon.learningportal.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ProfileConfig {

    @Value("${spring.profiles.active}")
    private String activeProfile;

    public EEnvironment getEnvironment() {
        return "dev".equalsIgnoreCase(activeProfile) ? EEnvironment.DEV : EEnvironment.PROD;
    }
}
