package model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Person
{
    @DatabaseField(generatedId = true)
    public long id;
    @DatabaseField(canBeNull = true)
    public String first_name;
    @DatabaseField(canBeNull = false)
    public String last_name;
}
