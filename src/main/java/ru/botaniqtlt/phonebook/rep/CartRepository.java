package ru.botaniqtlt.phonebook.rep;

import org.springframework.data.repository.CrudRepository;
import ru.botaniqtlt.phonebook.models.Cart;
import ru.botaniqtlt.phonebook.models.CartId;
import ru.botaniqtlt.phonebook.models.Product;

import java.util.List;

public interface CartRepository extends CrudRepository <Cart, CartId> {

    Iterable<Cart> findByCartIdCartId(Integer id);

    Iterable<Cart> findAllByCartIdCartId(int id);
}