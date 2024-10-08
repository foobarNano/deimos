package model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Equipment
{
    @DatabaseField(foreign = true, canBeNull = false)
    public Product product;

    @DatabaseField(generatedId = true)
    public long id;

    @DatabaseField(canBeNull = false)
    public Type type;

    public enum Type
    {
        Dressing,
        Accessory,
        Software
    }

    public Equipment() {}
    public Equipment(Product product)
    {
        this.product = product;
    }
}
