package com.ordereat.OrderEat;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing(auditorAwareRef="auditorProvider")
public class OrderEatConfig {
	@Bean
    AuditorAware<String> auditorProvider() {
        return new AuditorAwareImpl();
    }
}