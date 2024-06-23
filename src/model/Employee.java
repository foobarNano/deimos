package model;

class Employee extends Person
{
    static double min_wage = 10000d;

    Position position;
    double wage;

    enum Position
    {
        Worker,
        Admin,
        Manager
    }
}
