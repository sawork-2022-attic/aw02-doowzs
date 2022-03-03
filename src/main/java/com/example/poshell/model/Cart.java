package com.example.poshell.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Cart {

    private List<Item> items = new ArrayList<>();

    public boolean addItem(Item item) {
        return items.add(item);
    }

    public boolean modifyItem(Item item) {
        return items.removeIf(i -> i.getProduct().equals(item.getProduct())) && (item.getAmount() <= 0 || addItem(item));
    }

    public boolean removeItem(Item item) {
        return items.removeIf(i -> i.getProduct().equals(item.getProduct()));
    }

    public double getTotal() {
        return items.stream().mapToDouble(i -> i.getAmount() * i.getProduct().getPrice()).sum();
    }

    @Override
    public String toString() {
        if (items.size() == 0) {
            return "Empty Cart";
        }
        double total = 0;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Cart -----------------\n");

        for (int i = 0; i < items.size(); i++) {
            stringBuilder.append(items.get(i).toString()).append("\n");
        }
        stringBuilder.append("----------------------\n");

        stringBuilder.append("Total...\t\t\t").append(getTotal());

        return stringBuilder.toString();
    }
}
