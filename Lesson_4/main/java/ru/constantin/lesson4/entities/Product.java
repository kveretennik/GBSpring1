package ru.constantin.lesson4.entities;

import javax.persistence.*;

@Entity
@Table(schema = "test", name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "productNext")
    @SequenceGenerator(
            name = "productNext",
            schema = "test",
            sequenceName = "products_s"
    )
    @Column(name = "product_id")
    private Long productID;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_cost")
    private float productCost;

    public Long getProductID() {
        return productID;
    }

    public String getProductName() {
        return productName;
    }

    public float getProductCost() {
        return productCost;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductCost(float productCost) {
        this.productCost = productCost;
    }


}
