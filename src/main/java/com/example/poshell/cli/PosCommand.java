package com.example.poshell.cli;

import com.example.poshell.biz.PosService;
import com.example.poshell.model.Cart;
import com.example.poshell.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class PosCommand {

    private PosService posService;

    @Autowired
    public void setPosService(PosService posService) {
        this.posService = posService;
    }

    @ShellMethod(value = "List Products", key = "l")
    public String products() {
        StringBuilder stringBuilder = new StringBuilder();
        int i = 0;
        for (Product product : posService.products()) {
            stringBuilder.append("\t").append(++i).append("\t").append(product).append("\n");
        }
        return stringBuilder.toString();
    }

    @ShellMethod(value = "New Cart", key = "n")
    public String newCart() {
        return posService.newCart() + " OK";
    }

    @ShellMethod(value = "Add a Product to Cart", key = "a")
    public String addToCart(String productId, int amount) {
        if (posService.add(productId, amount)) {
            return posService.getCart().toString();
        }
        return "ERROR";
    }

    @ShellMethod(value = "Modify Item Amount in Cart", key = "m")
    public String modifyCartItem(String productId, int amount) {
        if (posService.modify(productId, amount)) {
            return posService.getCart().toString();
        }
        return "ERROR";
    }

    @ShellMethod(value = "Remove Item in Cart", key = "r")
    public String removeCartItem(String productId) {
        if (posService.remove(productId)) {
            return posService.getCart().toString();
        }
        return "ERROR";
    }

    @ShellMethod(value = "Print Cart", key = "p")
    public String printCart() {
        Cart cart = posService.getCart();
        if (cart == null) {
            return "No cart";
        } else {
            return cart.toString();
        }
    }

    @ShellMethod(value = "Checkout", key = "c")
    public String checkout() {
        double total = posService.checkout();
        if (total < 0.0f) {
            return "No cart";
        } else {
            return String.format("Total is %f\nCart cleaned", total);
        }
    }

}
