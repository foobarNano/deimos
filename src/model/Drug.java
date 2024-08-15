package model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Drug extends Product
{
    @DatabaseField
    boolean is_prescription;
    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    Ingredient ingredient;
    @DatabaseField
    int milligrams;
    @DatabaseField
    int doses;
}
