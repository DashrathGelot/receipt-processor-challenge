package com.fetch.rewards.receiptprocessorchallenge.services;

import com.fetch.rewards.receiptprocessorchallenge.model.Receipt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ReceiptService {
    @Autowired
    private ConcurrentHashMap<String, Receipt> data;
    private final Logger logger = LoggerFactory.getLogger(ReceiptService.class);
    private String generateUniqueId() {
        String uniqueId = UUID.randomUUID().toString();
        while (data.containsKey(uniqueId)) {
            uniqueId = UUID.randomUUID().toString();
        }
        logger.info("Generated Unique ID: {}", uniqueId);
        return uniqueId;
    }
    public String processReceipt(Receipt receipt) {
        String uniqueId = generateUniqueId();
        data.put(uniqueId, receipt);
        return uniqueId;
    }

    public Receipt getReceipt(String id) {
        return data.getOrDefault(id, null);
    }
}
