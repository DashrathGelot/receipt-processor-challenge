package com.fetch.rewards.receiptprocessorchallenge.services;

import com.fetch.rewards.receiptprocessorchallenge.model.Item;
import com.fetch.rewards.receiptprocessorchallenge.model.Receipt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PointService {
    private final Logger logger = LoggerFactory.getLogger(PointService.class);
    @Autowired
    ReceiptService receiptService;
    @Autowired
    ValidationService validationService;
    @Value("${point.rules.total.roundDollar}")
    private int roundDollar;
    @Value("${point.rules.total.multiple}")
    private double multiple;
    @Value("${point.rules.total.multipleP}")
    private int multiplyPoint;
    @Value("${point.rules.twoItem}")
    private int twoItem;
    @Value("${point.rules.descLenMultiply}")
    private int descLenMultiply;
    @Value("${point.rules.priceBy}")
    private double priceBy;
    @Value("${point.rules.purchase.oddDate}")
    private int oddDate;
    @Value("${point.rules.purchase.time2to4}")
    private int time2to4;
    @Value("${point.rules.purchase.timeStart}")
    private int timeStart;
    @Value("${point.rules.purchase.timeEnd}")
    private int timeEnd;

    private int getPointsByTotal(double total) {
        int Points = 0;
        if (Math.round(total) == total) {
            Points += roundDollar;
        }
        if (total % multiple == 0) {
            Points += multiplyPoint;
        }
        return Points;
    }

    private long getPointsByStr(String str) {
        String regex = "[^a-zA-Z0-9]";
        return str.replaceAll(regex, "").length();
    }

    private long getPointsByItem(Item item) {
        long descLen = item.getShortDescription().trim().length();
        if (descLen % descLenMultiply == 0) {
            return (long) Math.ceil(validationService.getValidDoubleValue(item.getPrice()) * priceBy);
        }
        return 0;
    }

    private long getPointsByItems(List<Item> items) {
        long Points = (items.size()/2L) * twoItem;
        for (Item item : items) {
            Points += getPointsByItem(item);
        }
        return Points;
    }

    private int getPointsByDate(LocalDate localDate) {
        if (localDate.getDayOfMonth() % 2 == 0) {
            return 0;
        }
        return oddDate;
    }

    private int getPointsByTime(String time) {
        int hour = Integer.parseInt(time.split(":")[0]);
        if (hour >= timeStart && hour <= timeEnd) {
            return time2to4;
        }
        return 0;
    }

    private long getPointsByReceipt(Receipt receipt) {
        double total = validationService.getValidDoubleValue(receipt.getTotal());
        LocalDate purchaseDate = validationService.getValidDate(receipt.getPurchaseDate());

        return getPointsByTotal(total) +
                getPointsByStr(receipt.getRetailer()) +
                getPointsByDate(purchaseDate) +
                getPointsByTime(receipt.getPurchaseTime()) +
                getPointsByItems(receipt.getItems());
    }

    public long getPoints(String id) {
        Receipt receipt = receiptService.getReceipt(id);
        if (receipt == null) {
            throw new RuntimeException("No receipt found for that id");
        }
        long points = getPointsByReceipt(receipt);
        logger.info("Calculated point for id: {} is points: {}", id, points);
        return points;
    }
}
