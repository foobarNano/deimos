package model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Equipment extends Product
{
    @DatabaseField(canBeNull = false)
    Type type;

    public enum Type
    {
        Dressing,
        Accessory,
        Software
    }
}
