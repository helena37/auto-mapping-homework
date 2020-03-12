package com.automappinghomework.config;

import com.automappinghomework.utils.ValidationUtil;
import com.automappinghomework.utils.ValidationUtilImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationBeanConfiguration {
    @Bean
    public ValidationUtil validationUtil() {
        return new ValidationUtilImpl();
    }
}
