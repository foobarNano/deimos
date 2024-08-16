package model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Employee extends Person
{
    static double min_wage = 10000d;

    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    Position position;
    @DatabaseField
    double wage;

    enum Position
    {
        Worker,
        Admin,
        Manager
    }
}
