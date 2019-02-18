package ru.constantin.netstore.entities;

import javax.persistence.*;

@Entity
@Table(schema = "store", name = "order_lines")
public class OrderLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_line_id")
    private Long orderLineID;

    @Column(name = "order_header_id")
    private Long orderHeaderID;

    @Column(name = "line_number")
    private int lineNumber;

    @Column(name = "item_id", insertable = false, updatable = false)
    private Long itemID;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @Column(name = "line_quantity")
    private float lineQuantity;

    @Column(name = "line_price")
    private float linePrice;

    public OrderLine() {

    }

    public Long getOrderLineID() {
        return orderLineID;
    }

    public Long getOrderHeaderID() {
        return orderHeaderID;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public Item getItem() {
        return item;
    }

    public Long getItemID() {
        return itemID;
    }

    public float getLineQuantity() {
        return lineQuantity;
    }

    public float getLinePrice() {
        return linePrice;
    }

    public void setOrderHeaderID(Long orderHeaderID) {
        this.orderHeaderID = orderHeaderID;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public void setLineQuantity(float lineQuantity) {
        this.lineQuantity = lineQuantity;
    }

    public void setLinePrice(float linePrice) {
        this.linePrice = linePrice;
    }
}
