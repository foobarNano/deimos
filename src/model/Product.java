package model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public abstract class Product
{
    @DatabaseField(generatedId = true)
    long id;
    @DatabaseField(foreign = true, foreignAutoRefresh = true, canBeNull = false)
    Brand brand;
    @DatabaseField(canBeNull = false)
    String name;
    @DatabaseField(canBeNull = false)
    double value;
    @DatabaseField
    String description;

    // List<ProductInWarehouse> warehousesStoring;
}
