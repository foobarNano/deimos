package model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class ProductInOrder
{
    @DatabaseField(foreign = true, foreignAutoRefresh = true, canBeNull = false)
    public Product product;
    @DatabaseField(foreign = true, foreignAutoRefresh = true, canBeNull = false)
    public Order order;
    
    @DatabaseField(canBeNull = false)
    public int count;

    @Override
    public boolean equals(Object obj)
    {
        ProductInOrder pio = (ProductInOrder) obj;
        return this.order == pio.order && this.product == pio.product;
    }
}
