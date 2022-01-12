package ru.botaniqtlt.phonebook.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.botaniqtlt.phonebook.models.Cart;
import ru.botaniqtlt.phonebook.models.CartId;
import ru.botaniqtlt.phonebook.models.Product;
import ru.botaniqtlt.phonebook.rep.CartRepository;
import ru.botaniqtlt.phonebook.rep.ProductRepository;

import java.util.List;
import java.util.Optional;

@Component
public class CartDAO {


    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;


    //add items in basket
    public void cartUpdate(Integer id, Integer productId, Integer count) {
//        updateCart.setCount(updateCart.getCount());
        Optional<Product> product = productRepository.findById(productId);
        if (product.isEmpty()) {
            throw new IllegalArgumentException();

        }
        Cart cart = new Cart();
        cart.setCartId(new CartId(id, productId));
        cart.setCount(count);
        cart.setProduct(product.get());
        cartRepository.save(cart);
    }


    public Iterable<Cart> getCart(int id) {
        return cartRepository.findByCartIdCartId(id);
    }


    public Iterable<Cart> showCart(int id) {

    return cartRepository.findAllByCartIdCartId(id);
    }


}

