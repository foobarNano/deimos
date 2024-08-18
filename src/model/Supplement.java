package model;

import com.j256.ormlite.field.DatabaseField;

public class Supplement
{
    @DatabaseField(foreign = true, canBeNull = false)
    public Product product;

    @DatabaseField(generatedId = true)
    public long id;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, foreignAutoCreate = true)
    public Designation designation;

    public Supplement() {}
    public Supplement(Product product)
    {
        this.product = product;
    }
}
