public class Person 
{
    private String Name;
    private int Age;
    private double Income;

    public Person() 
    {
        String Name = "";
        int Age = 0;
        double Income = 0;

    }
    public Person(String n, int a, double i)
    {
        String Name = n;
        int Age = a;
        double Income = i;
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
        return (Name = p.getName()) && (Age = p.getName()) && (Income = p.getIncome()) );
    }
}   


