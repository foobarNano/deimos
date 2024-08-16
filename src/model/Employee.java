package model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Employee extends Person
{
    static double min_wage = 10000d;

    @DatabaseField(foreign = true, canBeNull = false)
    Product product;

    @DatabaseField(canBeNull = false)
    Position position;
    @DatabaseField
    double wage;

    public enum Position
    {
        Worker,
        Admin,
        Manager
    }
}
