package com.ordereat.OrderEat;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;

public class AuditorAwareImpl implements AuditorAware<String> {
	 
    @Override
    public Optional<String> getCurrentAuditor() {
		return null;
        // your custom logic
    }

}
