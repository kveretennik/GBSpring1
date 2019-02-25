package ru.constantin.netstore.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(schema = "store", name = "manufacturers")
public class Manufacturer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "manufacturer_id")
    private Long manufacturerID;

    @Column(name = "manufacturer_name")
    private String manufacturerName;

    @Column(name = "manufacturer_desc")
    private String manufacturerDesc;

    @OneToMany(mappedBy = "manufacturer")
    private List<Item> items;

    public Manufacturer() {
    }

    public Long getManufacturerID() {
        return manufacturerID;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public String getManufacturer_desc() {
        return manufacturerDesc;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    public void setManufacturerDesc(String manufacturerDesc) {
        this.manufacturerDesc = manufacturerDesc;
    }
}
