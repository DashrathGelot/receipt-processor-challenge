package com.fetch.rewards.receiptprocessorchallenge.config;

import com.fetch.rewards.receiptprocessorchallenge.model.Receipt;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ConcurrentHashMap;

@Configuration
public class MemoryCache {
    private final ConcurrentHashMap<String, Receipt> data = new ConcurrentHashMap<>();

    @Bean
    public ConcurrentHashMap<String, Receipt> data() {
        return data;
    }
}
