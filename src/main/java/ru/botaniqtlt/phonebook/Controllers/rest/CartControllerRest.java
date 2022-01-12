package ru.botaniqtlt.phonebook.Controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.botaniqtlt.phonebook.Controllers.dto.CartRequest;
import ru.botaniqtlt.phonebook.Controllers.dto.CartResponse;
import ru.botaniqtlt.phonebook.DAO.CartDAO;
import ru.botaniqtlt.phonebook.DAO.ProductDAO;

@RestController
@RequestMapping("/cart")
public class CartControllerRest {

    @Autowired
    private CartDAO cartDAO;

    @Autowired
    private ProductDAO productDAO;

    @GetMapping("/{id}")
    public CartResponse index(@PathVariable("id") int id) {
        return new CartResponse(cartDAO.getCart(id));
    }

    @PostMapping("/{id}")
    public CartResponse updateCart(@RequestBody CartRequest cartRequest, @PathVariable("id") int id) {

        cartDAO.cartUpdate(id, cartRequest.getProductId(), cartRequest.getCount());
        return new CartResponse(cartDAO.getCart(id));
    }

}
