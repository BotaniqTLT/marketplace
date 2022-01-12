package ru.botaniqtlt.phonebook.models;

import java.io.Serializable;
import java.util.Objects;

public class CartId implements Serializable {

    private Integer cartId;

    private Integer productId;


    public CartId(Integer cartId, Integer product) {
        this.cartId = cartId;
        this.productId = product;
    }

    public CartId(){
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartId cartId1 = (CartId) o;
        return cartId.equals(cartId1.cartId) && productId.equals(cartId1.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cartId, productId);
    }

    public Integer getCartId() {
        return cartId;
    }

    public Integer getProductId() {
        return productId;
    }
}
