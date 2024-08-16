package model;

import java.util.List;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Grade
{
    @ForeignCollectionField(eager = false)
    static List<Grade> grades;

    @DatabaseField(generatedId = true)
    long id;
    @DatabaseField(canBeNull = false)
    String common_name;
    @DatabaseField(canBeNull = false)
    int min_score;
    @DatabaseField(canBeNull = false)
    int max_score;
}
