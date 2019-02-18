package ru.constantin.netstore;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.constantin.netstore.entities.*;

import java.util.List;

/*
Сделать удаление не успел.
Вопрос по цене товара.
Цена товара у меня лежит в строке заказа(класс OrderLine).
При поиске товаров по заказчику функция возвращает только список товаров, как туда добавить цену из строки заказа, я не понял.
Для этого нужно создавать специальный класс-сущность и привязать к нему, например, view из базы данных?
Вообще составные запросы, которые возвращают поля нескольких сущностей нужно в отдельные сущности выносить?
Или можно создать хранимую процедуру в БД, которая возвращает курсор?
Как работать с такой процедуройв Hibernate?

Создал два класса OrderHeader и OrderLIne c отношением один ко многим соответственно.
Правильно ли это? Можно ли обойтись одним классом OrderHeader, а строки реализовать как-то внутри этого класса или так не стоит делать?

Можно ли в дальнейшем использовать в качестве БД Postgres вместо MySql?
*/

public class NetStoreApp {

    public static List<Item> getItemsByCustomer(Session session, long customerID) {
        session.beginTransaction();
        List<Item> items = session.createQuery("FROM Item i WHERE i.itemID IN (SELECT ol.itemID FROM OrderHeader oh " +
                                                                                 "JOIN OrderLine ol ON oh.orderHeaderID = ol.orderHeaderID WHERE oh.customerID = :customerID)")
                .setParameter("customerID", customerID)
                .getResultList();
        session.getTransaction().commit();
        return  items;
    }

    public static List<Customer> getCustomerByItems(Session session, long itemID) {
        session.beginTransaction();
        List<Customer> customers = session.createQuery("FROM Customer c WHERE c.customerID IN (SELECT oh.customerID FROM OrderHeader oh " +
                "JOIN OrderLine ol ON oh.orderHeaderID = ol.orderHeaderID WHERE ol.itemID = :itemID)")
                .setParameter("itemID", itemID)
                .getResultList();
        session.getTransaction().commit();
        return  customers;
    }

    public static void main(String[] args) {
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Manufacturer.class)
                .addAnnotatedClass(Item.class)
                .addAnnotatedClass(Customer.class)
                .addAnnotatedClass(OrderHeader.class)
                .addAnnotatedClass(OrderLine.class)
                .buildSessionFactory();
        Session session = null;
        try {
            getItemsByCustomer(session, 2L);
        }
        finally {
            session.close();
            factory.close();
        }
    }
}