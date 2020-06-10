package com.bslota.refactoring.library.loyalties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.subethamail.wiser.Wiser;

@Configuration
class SmtpConfig {

    @Bean
    public Wiser wiser() {
        final Wiser wiser = new Wiser();
        wiser.setHostname("localhost");
        wiser.setPort(10025);
        wiser.start();
        return wiser;
    }
}
