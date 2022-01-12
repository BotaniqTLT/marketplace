package ru.botaniqtlt.phonebook.DAO;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import ru.botaniqtlt.phonebook.IndexRequest;
import ru.botaniqtlt.phonebook.models.Product;
import ru.botaniqtlt.phonebook.rep.ProductRepository;

import java.util.List;

@Component
public class ProductDAO   {

    @Autowired
    private ProductRepository productRepository;

    private List<Product> products;

    public Page<Product> index(IndexRequest select) {
        return productRepository.findAll(PageRequest.of(select.getPage()-1,select.getSize()));

    }

    public Product show(int id) {
        return productRepository.findById(id).orElseThrow();
    }

    public Product save(Product product) {

        return productRepository.save(product);

    }

    public void update(int id, Product updateProduct) {
        Product personToBeUpdated = show(id);
        personToBeUpdated.setName(updateProduct.getName());
        personToBeUpdated.setDescription(updateProduct.getDescription());
        personToBeUpdated.setPrice(updateProduct.getPrice());
        personToBeUpdated.setImage(updateProduct.getImage());
        productRepository.save(personToBeUpdated);

    }

    public void delete(int id) {
        Product product = show(id);
        productRepository.delete(product);
    }


}

