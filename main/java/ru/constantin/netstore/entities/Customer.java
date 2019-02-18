package ru.constantin.netstore.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(schema = "store", name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long customerID;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "full_name")
    private String fullName;

    @OneToMany(mappedBy = "customer")
    private List<OrderHeader> orderHeaderList;

    public Customer() {

    }

    public Long getCustomerID() {
        return customerID;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getFullName() {
        return fullName;
    }

    public List<OrderHeader> getOrderHeadersList() {
        return orderHeaderList;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    @Override
    public String toString() {
        return "fullName = '" + fullName + '.';
    }
}
