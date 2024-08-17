package model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Drug
{
    @DatabaseField(foreign = true, canBeNull = false)
    public Product product;

    @DatabaseField
    public boolean is_prescription;
    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    public Ingredient ingredient;
    @DatabaseField
    public int milligrams;
    @DatabaseField
    public int doses;
}
