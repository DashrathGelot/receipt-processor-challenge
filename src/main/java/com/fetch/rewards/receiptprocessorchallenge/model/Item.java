package com.fetch.rewards.receiptprocessorchallenge.model;

public class Item {
    private final String shortDescription;
    private final String price;

    public Item(String shortDescription, String price) {
        this.shortDescription = shortDescription;
        this.price = price;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Item{" +
                "shortDescription='" + shortDescription + '\'' +
                ", price=" + price +
                '}';
    }
}
