package ru.constantin.netstore.entities;

import javax.persistence.*;

@Entity
@Table(schema = "store", name = "items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long itemID;

    @Column(name = "item_number")
    private String itemNumber;

    @Column(name = "item_name")
    private String itemName;

    @Column(name = "item_description")
    private String itemDescription;

    @Column(name = "primary_uom_code")
    private String primaryUomCode;

//    @Column(name = "manufacturer_id")
//    private Long manufacturerID;

    @Column(name = "onhand_quantity")
    private float onhandQuantity;

    @Column(name = "item_price")
    private float itemPrice;

    @ManyToOne
    @JoinColumn(name = "manufacturer_id")
    private Manufacturer manufacturer;

    public Item() {
    }

    public Long getItemID() {
        return itemID;
    }

    public String getItemNumber() {
        return itemNumber;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public String getPrimaryUomCode() {
        return primaryUomCode;
    }

//    public Long getManufacturerID() {
//        return manufacturerID;
//    }

    public float getOnhandQuantity() {
        return onhandQuantity;
    }

    public float getItemPrice() {
        return itemPrice;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setItemNumber(String itemNumber) {
        this.itemNumber = itemNumber;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public void setPrimaryUomCode(String primaryUomCode) {
        this.primaryUomCode = primaryUomCode;
    }

//    public void setManufacturerID(Long manufacturerID) {
//        this.manufacturerID = manufacturerID;
//    }

    public void setOnhandQuantity(float onhandQuantity) {
        this.onhandQuantity = onhandQuantity;
    }

    public void setItemPrice(float itemPrice) {
        this.itemPrice = itemPrice;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }
}
