package model;

import java.util.List;

class Warehouse
{
    long id;
    String location;
    double sales_tax;

    List<ProductInWarehouse> productsStored;
}
