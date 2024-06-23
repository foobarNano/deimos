package model;

import java.util.List;

public class Brand
{
    long id;
    String name;
    Type type;
    List<Product> products;

    enum Type
    {
        Federal,
        Union,
        Unaffiliated
    }
}
