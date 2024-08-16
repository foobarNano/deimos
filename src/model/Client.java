package model;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Client extends Person
{
    @DatabaseField(canBeNull = false, unique = false)
    String cryptonym;
    @DatabaseField(canBeNull = false)
    String contact_address;
    @DatabaseField
    String delivery_address;

    @ForeignCollectionField(eager = false)
    ForeignCollection<Order> orders;

    int getScore()
    {
        double sum = 0;

        for (Order o : orders) { sum += o.getValue(); }

        return (int) sum;
    }

    Grade getGrade()    // Returns null if no suitable found
    {
        int score = getScore();
        Grade grade = null;

        for (Grade g : Grade.grades)
        {
            if (!(score >= g.min_score && score <= g.max_score)) continue;
            grade = g;
        }

        return grade;
    }
}
