package ru.botaniqtlt.phonebook.Controllers;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.botaniqtlt.phonebook.Controllers.dto.CartRequest;
import ru.botaniqtlt.phonebook.DAO.CartDAO;
import ru.botaniqtlt.phonebook.DAO.ProductDAO;
import ru.botaniqtlt.phonebook.IndexRequest;
import ru.botaniqtlt.phonebook.models.Cart;
import ru.botaniqtlt.phonebook.models.Product;
import ru.botaniqtlt.phonebook.rep.ProductRepository;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/product")
public class ProductController {

    private ProductDAO productDAO;

    private CartDAO cartDao;

    public ProductController(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @GetMapping()
    public String index(Model model, IndexRequest select) {

        Page<Product> page = productDAO.index(select);
        //  получим всех людей из DAO
        model.addAttribute("product", productDAO.index(select));
        model.addAttribute("select", select);
        model.addAttribute("list", page);
        int totalPages = page.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "index";
    }


    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("product", productDAO.show(id));
        return "/show";
    }


    @GetMapping("/new")
    public String newProduct(Model model) {
        model.addAttribute("product", new Product());
        return "/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("product") @Valid Product product, BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            return "/new";
        productDAO.save(product);
        return "redirect:/product/";

    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("product", productDAO.show(id));
        return "/edit";

    }


    @PatchMapping("/{id}")
    public String update(@ModelAttribute("product") @Valid Product product, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "/edit";
        productDAO.update(id, product);
        return "redirect:/product/";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        productDAO.delete(id);
        return "redirect:/product/";
    }



}
