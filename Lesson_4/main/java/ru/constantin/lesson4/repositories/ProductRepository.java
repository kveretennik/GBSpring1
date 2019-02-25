package ru.constantin.lesson4.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.constantin.lesson4.entities.Product;
import java.util.List;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {

    Product findFirstByProductCostIsNotNullOrderByProductCostAsc();
    Product findFirstByProductCostIsNotNullOrderByProductCostDesc();

    List<Product> findAll();

    List<Product> findAllByProductCost(float productCost);

}
