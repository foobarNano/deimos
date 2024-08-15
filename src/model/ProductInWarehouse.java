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

    @Override
    public boolean equals(Object obj)
    {
        ProductInWarehouse piw = (ProductInWarehouse) obj;
        return this.warehouse == piw.warehouse && this.product == piw.product;
    }
}
