package ru.botaniqtlt.phonebook.models;


import javax.persistence.*;

@Entity()
public class Cart {

    @EmbeddedId
    private CartId cartId;

    @MapsId("productId")
    @ManyToOne(optional = true)
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    private Product product;

    @Column(name = "COUNT")
    private Integer count;

    public CartId getCartId() {
        return cartId;
    }

    public void setCartId(CartId cartId) {
        this.cartId = cartId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
