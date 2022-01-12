package ru.botaniqtlt.phonebook.rep;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.botaniqtlt.phonebook.models.Product;

public interface ProductRepository extends PagingAndSortingRepository<Product, Integer> {


//  Page<Product> findAll(Pageable pageable);
}
