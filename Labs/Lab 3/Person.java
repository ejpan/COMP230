/* Class for Person to store, change, and get user inputted name, age, and income.
Eric Pan and Gabe Seidl
February 16, 2022
*/

public class Person 
{
    private String Name;
    private int Age;
    private double Income;

    public Person() // default constructor
    {
        Name = "";
        Age = 0;
        Income = 0;

    }
    public Person(String n, int a, double i) // non-default constructor
    {
        Name = n;
        Age = a;
        Income = i;
    }
    public String getName() // gets name
    {
        return Name;
    }
    public int getAge() // gets age
    {
        return Age;
    }
    public double getIncome() // gets income
    {
        return Income;
    }
    public void setName(String n) // sets name
    {
        Name = n;
    }
    public void setAge(int a) // sets age
    {
        Age = a;
    } 
    public void setIncome(double i) // sets income
    {
        Income = i;
    }
    public boolean equals(Object p) // checks if 2 objects are equal
    {
        Person new_p = (Person)p;
        boolean income_check = false;
        double income_diff = Math.abs(this.Income - new_p.Income);
        if(income_diff < 0.01)
        {
            income_check = true;
        }
        return ((this.Name.equals(new_p.Name)) && (this.Age == new_p.Age) && income_check);
    }
}   


