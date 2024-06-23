package model;

import java.util.List;

abstract class Product
{
    long id;
    Brand brand;
    String name;
    double value;
    String description;

    List<ProductInWarehouse> warehousesStoring;
}
