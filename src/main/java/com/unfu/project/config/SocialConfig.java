package com.unfu.project.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.social.google.connect.GoogleConnectionFactory;

@Configuration
public class SocialConfig {

    private final Logger log = LoggerFactory.getLogger(SocialConfig.class);

    private final Environment environment;

    public SocialConfig(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public GoogleConnectionFactory googleConnectionFactory() {
        String googleClientId = environment.getProperty("spring.social.google.clientId");
        String googleClientSecret = environment.getProperty("spring.social.google.clientSecret");
        if (googleClientId != null && googleClientSecret != null) {
            log.info("Configure GoogleConnectionFactory");
            return new GoogleConnectionFactory(googleClientId, googleClientSecret);
        }
        log.error("Can not configure GoogleConnectionFactory");
        throw new RuntimeException("Can not configure GoogleConnectionFactory");
    }
}
