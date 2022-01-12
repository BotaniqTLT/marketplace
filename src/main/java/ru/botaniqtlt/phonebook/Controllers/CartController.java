package ru.botaniqtlt.phonebook.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.botaniqtlt.phonebook.Controllers.dto.CartRequest;
import ru.botaniqtlt.phonebook.Controllers.dto.CartResponse;
import ru.botaniqtlt.phonebook.DAO.CartDAO;
import ru.botaniqtlt.phonebook.DAO.ProductDAO;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartDAO cartDAO;

    @Autowired
    private ProductDAO productDAO;

    @GetMapping("/cartproduct")
    public String indexCart(Model model) {
    model.addAttribute("cart",cartDAO.showCart(1));
        return "indexCart";
    }
}
