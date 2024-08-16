package model;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Product
{
    @DatabaseField(generatedId = true)
    public long id;
    @DatabaseField(foreign = true, foreignAutoRefresh = true, canBeNull = false)
    public Brand brand;
    @DatabaseField(canBeNull = false)
    public String name;
    @DatabaseField(canBeNull = false)
    public double value;
    @DatabaseField
    public String description;

    @ForeignCollectionField(eager = false)
    public ForeignCollection<ProductInWarehouse> warehousesStoring;

    @ForeignCollectionField(eager = false)
    public ForeignCollection<ProductInOrder> ordersContaining;
}
