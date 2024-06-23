package model;

import java.sql.Timestamp;
import java.util.List;

class Order
{
    long id;
    Client client;
    Timestamp placed;
    Timestamp confirmed;
    Timestamp completed;

    List<Product> products;

    double getValue()
    {
        double sum = 0d;

        for (Product p : products) { sum += p.value; }

        return sum;
    }
}
