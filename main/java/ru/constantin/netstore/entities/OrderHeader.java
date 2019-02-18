package ru.constantin.netstore.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(schema = "store", name = "order_headers")
public class OrderHeader {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orderHeaderNext")
    @SequenceGenerator(
            name = "orderHeaderNext",
            schema = "store",
            sequenceName = "order_headers_s",
            allocationSize = 1
    )
    @Column(name = "order_header_id")
    private Long orderHeaderID;

    @Column(name = "order_number")
    private Long orderNumber;

    @Column(name = "customer_id")
    private Long customerID;

    @Column(name = "ship_address_id")
    private Long shipAddressID;

    @ManyToOne
    @JoinColumn(name = "customer_id", insertable = false, updatable = false)
    private Customer customer;

    @OneToMany
    @JoinColumn(name = "order_header_id")
    private List<OrderLine> orderLineList;

    public OrderHeader() {

    }

    public Long getOrderHeaderID() {
        return orderHeaderID;
    }

    public Long getOrderNumber() {
        return orderNumber;
    }

    public Long getCustomerID() {
        return customerID;
    }

    public Long getShipAddressID() {
        return shipAddressID;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<OrderLine> getOrderLineList() {
        return orderLineList;
    }

    public void setOrderNumber(Long orderNumber) {
        this.orderNumber = orderNumber;
    }

    public void setShipAddressID(Long shipAddressID) {
        this.shipAddressID = shipAddressID;
    }

    public void setCustomerID(Long customerID) {
        this.customerID = customerID;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
