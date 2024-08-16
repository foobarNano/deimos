package model;

import java.util.List;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
class Client extends Person
{
    @DatabaseField(canBeNull = false, unique = false)
    String cryptonym;
    @DatabaseField(canBeNull = false)
    String contact_address;
    @DatabaseField
    String delivery_address;

    
    List<Order> orders;

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
