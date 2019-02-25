package ru.constantin.lesson4.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.constantin.lesson4.entities.Product;
import ru.constantin.lesson4.repositories.ProductRepository;

import java.util.List;

@Service
public class ProductService {

    private ProductRepository productRepository;
    private int pageNumber = 0;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProductsByMinCost() {
        pageNumber = 0;
        return (List<Product>) productRepository.findAllByProductCost(productRepository.findFirstByProductCostIsNotNullOrderByProductCostAsc().getProductCost());
    }

    public List<Product> getAllProductsByMaxCost() {
        pageNumber = 0;
        return (List<Product>) productRepository.findAllByProductCost(productRepository.findFirstByProductCostIsNotNullOrderByProductCostDesc().getProductCost());
    }

    public Page<Product> getPage(String direction) {

        Page<Product> page;

        if (direction.equals("previous")) {
            if (pageNumber > 0) pageNumber--;
        } else if (direction.equals("next")) {
            pageNumber++;
        } else {
            pageNumber = 0;
        }

        page = productRepository.findAll(PageRequest.of(pageNumber, 5));
        if (page.getTotalPages() <= pageNumber) {
            page = productRepository.findAll(PageRequest.of(--pageNumber, 5));
        }

        return page;
    }
}

