package com.fetch.rewards.receiptprocessorchallenge.model;

import org.springframework.lang.NonNull;

import java.util.List;

public class Receipt {
    @NonNull
    private final String retailer;
    @NonNull
    private final String purchaseDate;
    @NonNull
    private final String purchaseTime;
    @NonNull
    private final String total;
    @NonNull
    private final List<Item> items;

    public Receipt(String retailer, String purchaseDate, String purchaseTime, String total, List<Item> items) {
        this.retailer = retailer;
        this.purchaseDate = purchaseDate;
        this.purchaseTime = purchaseTime;
        this.total = total;
        this.items = items;
    }

    public String getRetailer() {
        return retailer;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public String getPurchaseTime() {
        return purchaseTime;
    }

    public String getTotal() {
        return total;
    }

    public List<Item> getItems() {
        return items;
    }

    @Override
    public String toString() {
        return "Receipt{" +
                "retailer='" + retailer + '\'' +
                ", purchaseDate='" + purchaseDate + '\'' +
                ", purchaseTime='" + purchaseTime + '\'' +
                ", total=" + total +
                ", items=" + items +
                '}';
    }
}
