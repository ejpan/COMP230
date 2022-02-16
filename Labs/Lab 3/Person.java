public class Person 
{
    private String Name;
    private int Age;
    private double Income;

    public Person() 
    {
        Name = "";
        Age = 0;
        Income = 0;

    }
    public Person(String n, int a, double i)
    {
        Name = n;
        Age = a;
        Income = i;
    }
    public String getName()
    {
        return Name;
    }
    public int getAge()
    {
        return Age;
    }
    public double getIncome()
    {
        return Income;
    }
    public void setName(String n)
    {
        Name = n;
    }
    public void setAge(int a)
    {
        Age = a;
    } 
    public void setIncome(double i)
    {
        Income = i;
    }
    public boolean equals(Object p)
    {
        Person new_p = (Person)p;
        double income_diff = Math.abs(this.Income - new_p.Income);
        boolean income = false;
        if (income_diff < 0.01)
        {
            return income;
        }
        return ((this.Name.equals(new_p.Name)) && (this.Age == new_p.Age) && income);
    }
}   


