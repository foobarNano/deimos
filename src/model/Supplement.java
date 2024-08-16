package model;

import com.j256.ormlite.field.DatabaseField;

public class Supplement extends Product
{
    @DatabaseField(canBeNull = false)
    Designation designations;
}
