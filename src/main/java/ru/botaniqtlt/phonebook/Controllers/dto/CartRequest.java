package ru.botaniqtlt.phonebook.Controllers.dto;

public class CartRequest {
    private  Integer  productId;
    private  Integer count;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
