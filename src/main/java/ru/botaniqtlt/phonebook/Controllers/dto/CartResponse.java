package ru.botaniqtlt.phonebook.Controllers.dto;

import ru.botaniqtlt.phonebook.models.Cart;

public class CartResponse {

    private Iterable<Cart> cart;
    private int total;
    private int summ;

    public CartResponse(Iterable<Cart> cart) {
        this.cart = cart;
        for (Cart el : cart) {
            ++total;
            summ += el.getCount()*el.getProduct().getPrice();
        }
    }

    public Iterable<Cart> getCart() {
        return cart;
    }

    public int getTotal() {
        return total;
    }

    public int getSumm() {
        return summ;
    }
}
