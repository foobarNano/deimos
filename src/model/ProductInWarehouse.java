package model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class ProductInWarehouse
{
    @DatabaseField(foreign = true, foreignAutoRefresh = true, canBeNull = false)
    public Product product;
    @DatabaseField(foreign = true, foreignAutoRefresh = true, canBeNull = false)
    public Warehouse warehouse;
    
    @DatabaseField(canBeNull = false)
    public int count;
    @DatabaseField
    public int maximum;
}
