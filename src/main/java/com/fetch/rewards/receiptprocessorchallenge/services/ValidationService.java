package com.fetch.rewards.receiptprocessorchallenge.services;

import com.fetch.rewards.receiptprocessorchallenge.model.Item;
import com.fetch.rewards.receiptprocessorchallenge.model.Receipt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

@Service
public class ValidationService {
    static final String DATE_PATTERN = "yyyy-MM-dd";
    static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_PATTERN);
    private final Logger logger = LoggerFactory.getLogger(ValidationService.class);
    public LocalDate getValidDate(String date) {
        try {
            return LocalDate.parse(date, DATE_FORMATTER);
        } catch (DateTimeException e) {
            throw new DateTimeException("Invalid Date! it should be yyyy-mm-dd format");
        }
    }

    public double getValidDoubleValue(String num, String ...key) {
        try {
            return Double.parseDouble(num);
        } catch (NumberFormatException e) {
            String msg = "Invalid Number! total should be valid number";
            if (key.length > 0) {
                msg = "Invalid Number! " + key[0] + " should be valid number";
            }
            throw new NumberFormatException(msg);
        }
    }

    public boolean validateTime(String time) {
        if (time.isBlank() || time.isEmpty()) return false;
        return Pattern.compile("([01]?[0-9]|2[0-3]):[0-5][0-9]").matcher(time).matches();
    }

    public void validateReceipt(Receipt receipt) {
        logger.info("Validating Receipt");
        if (receipt.getRetailer() == null
                || receipt.getPurchaseDate() == null
                || receipt.getPurchaseTime() == null
                || receipt.getItems() == null
                || receipt.getTotal() == null) {
            throw new RuntimeException("All Fields are required");
        }
        getValidDate(receipt.getPurchaseDate());
        if (!validateTime(receipt.getPurchaseTime())) {
            throw new DateTimeException("Invalid Time it should be hh:mm");
        }
        getValidDoubleValue(receipt.getTotal());
        for (Item item : receipt.getItems()) {
            getValidDoubleValue(item.getPrice(), "Item Price");
        }
    }

}
