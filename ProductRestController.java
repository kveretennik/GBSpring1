package ru.constantin.lesson5.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.constantin.lesson5.entities.Product;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductRestController {

    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/products{id}")
    public Product getProducts(@PathVariable (name = "id") Long id) {
        return productService.getProductById(id);
    }

    @PostMapping("/products")
    public Product addProduct(@RequestParam Product product) {
        return productService.saveProduct(product);
    }

    @PutMapping("/products")
    public Product updateProduct(@RequestBody Product product) {
        return productService.updateProduct(product);
    }

    @DeleteMapping("/products")
    public int deleteProduct(@RequestBody Product product) {
        return productService.deleteProduct(product);
    }
}

@Service
public class ProductService {

    private Float minCost;
    private Float maxCost;

    private ProductRestRepository productRestRepository;

    ...

    @Autowired
    public void setProductRestRepository(ProductRestRepository productRestRepository) {
        this.productRestRepository = productRestRepository;
    }

    public List<Product> getAllProducts() {
        return (List<Product>) productRestRepository.findAll();
    }

    public List<Product> findCostBetween() {
        return productRestRepository.findAllByEnabledFlagIsTrueAndProductCostBetween(minCost, maxCost);
    }

    public Product saveOrUpdate(Product product) {
        return productRepository.save(product);
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public Product saveProduct(Product product) {
        product.setProductID(0L);
        return productRestRepository.save(product);
    }

    public Product updateProduct(Product product) {
        return productRestRepository.save(product);
    }

    public int deleteProduct(Product product) {
        productRestRepository.deleteById(product.getProductID());
        return 200;
    }
}

@Repository
public interface ProductRestRepository extends PagingAndSortingRepository<Product, Long> {

    List<Product> findAllByEnabledFlagIsTrueAndProductCostBetween(float minCost, float maxCost);
}
