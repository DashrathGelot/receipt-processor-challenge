package com.fetch.rewards.receiptprocessorchallenge.rest;

import com.fetch.rewards.receiptprocessorchallenge.model.Receipt;
import com.fetch.rewards.receiptprocessorchallenge.services.PointService;
import com.fetch.rewards.receiptprocessorchallenge.services.ReceiptService;
import com.fetch.rewards.receiptprocessorchallenge.services.ValidationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/receipts")
public class ReceiptController {
    @Autowired
    private ReceiptService receiptService;
    @Autowired
    private ValidationService validationService;
    @Autowired
    private PointService pointService;

    private final Logger logger = LoggerFactory.getLogger(ReceiptController.class);

    @GetMapping("/{id}/points")
    public Map<String, Long> fetchPoints(@PathVariable String id) {
        try {
            Map<String, Long> points = new HashMap<>();
            points.put("points", pointService.getPoints(id));
            return points;
        } catch (Exception e) {
            logger.error("Getting points make error for id : {} , message: {}", id, e.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @PostMapping("/process")
    public Map<String, String> processReceipts(@RequestBody Receipt receipt) {
        try {
            validationService.validateReceipt(receipt);
            Map<String, String> receiptId = new HashMap<>();
            receiptId.put("id", receiptService.processReceipt(receipt));
            return receiptId;
        } catch (Exception e) {
            logger.error("Failed to process receipts, message: {}", e.getMessage());
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    String.format("The receipt is invalid, Message: %s", e.getMessage()),
                    e
            );
        }
    }

}
