package model;

import com.j256.ormlite.field.DatabaseField;

public class Supplement
{
    @DatabaseField(foreign = true, canBeNull = false)
    public Product product;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, canBeNull = false)
    Designation designation;
}
