package com.example.poshell.biz;

import com.example.poshell.model.Cart;
import com.example.poshell.model.Product;

import java.util.List;

public interface PosService {
    public Cart getCart();

    public Cart newCart();

    public double checkout();

    public double total();

    public boolean add(Product product, int amount);

    public boolean add(String productId, int amount);

    public boolean modify(Product product, int amount);

    public boolean modify(String productId, int amount);

    public boolean remove(Product product);

    public boolean remove(String productId);


    public List<Product> products();
}
