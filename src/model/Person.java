package model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
abstract class Person
{
    @DatabaseField(generatedId = true)
    long id;
    @DatabaseField(canBeNull = true, uniqueCombo = true)
    String first_name;
    @DatabaseField(canBeNull = false, uniqueCombo = true)
    String last_name;
}
