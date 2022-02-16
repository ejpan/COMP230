/* Application that defines the Class Person allowing user to add and get name, age, and income 
with this well designed and implemented program.
Gabe Seidl and Eric Pan
*/

public class PersonApp 
{
    public static void main(String[] args) 
    {
        Person per1 = new Person();
        Person per2 = new Person("Gabe", 19, 60000);
        System.out.println("Person 1's name is " + per1.getName() +"\nPerson 1's age is " + per1.getAge() + "\nPerson 1's income is " + per1.getIncome());
        per1.setName("Eric");
        per1.setAge(22);
        per1.setIncome(20000);
        System.out.println("Person 1's new name is " + per1.getName() +"\nPerson 1's new age is " + per1.getAge() + "\nPerson 1's new income is " + per1.getIncome());
        System.out.println("Person 2's name is " + per2.getName() +"\nPerson 2's age is " + per2.getAge() + "\nPerson 2's income is " + per2.getIncome());
        System.out.println(per1.equals(per2));
        per2.setName("Eric");
        per2.setAge(22);
        per2.setIncome(20000);
        System.out.println(per2.equals(per1));

    }
}

