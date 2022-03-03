package com.example.poshell.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
public class Cart {

    private List<Item> items = new ArrayList<>();

    public boolean addItem(Item item) {
        Optional<Item> optionalItem = items.stream().filter(i -> i.getProduct().equals(item.getProduct())).findAny();
        if (optionalItem.isPresent()) {
            item.setAmount(item.getAmount() + optionalItem.get().getAmount());
            return modifyItem(item);
        } else {
            if (item.getAmount() > 0) {
                items.add(item);
            }
            return true;
        }
    }

    public boolean modifyItem(Item item) {
        items.removeIf(i -> i.getProduct().equals(item.getProduct()));
        if (item.getAmount() > 0) {
            items.add(item);
        }
        return true;
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
