package model;

import java.util.List;

class Client extends Person
{
    String cryptonym;
    String contact_address;
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
