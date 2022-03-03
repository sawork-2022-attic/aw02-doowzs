package com.example.poshell.biz;

import com.example.poshell.db.PosDB;
import com.example.poshell.model.Cart;
import com.example.poshell.model.Item;
import com.example.poshell.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PosServiceImp implements PosService {

    private PosDB posDB;

    @Autowired
    public void setPosDB(PosDB posDB) {
        this.posDB = posDB;
    }

    @Override
    public Cart getCart() {
        return posDB.getCart();
    }

    @Override
    public Cart newCart() {
        return posDB.saveCart(new Cart());
    }

    @Override
    public double checkout() {
        double price = total();
        if (!(price < 0.0f)) {
            this.newCart();
        }
        return price;
    }

    @Override
    public double total() {
        Cart cart = this.getCart();
        return cart == null ? -1.0f : cart.getTotal();
    }

    @Override
    public boolean add(Product product, int amount) {
        return false;
    }

    @Override
    public boolean add(String productId, int amount) {
        Product product = posDB.getProduct(productId);
        if (product == null) return false;

        Cart cart = this.getCart();
        if (cart == null) return false;

        return cart.addItem(new Item(product, amount));
    }

    @Override
    public boolean modify(Product product, int amount) {
        return false;
    }

    @Override
    public boolean modify(String productId, int amount) {
        Product product = posDB.getProduct(productId);
        if (product == null) return false;

        Cart cart = this.getCart();
        if (cart == null) return false;

        return cart.modifyItem(new Item(product, amount));
    }

    @Override
    public boolean remove(Product product) {
        return false;
    }

    @Override
    public boolean remove(String productId) {
        Product product = posDB.getProduct(productId);
        if (product == null) return false;

        Cart cart = this.getCart();
        if (cart == null) return false;

        return cart.removeItem(new Item(product, 0));
    }

    @Override
    public List<Product> products() {
        return posDB.getProducts();
    }
}
