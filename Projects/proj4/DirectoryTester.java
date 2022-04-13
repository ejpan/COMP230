import java.util.*;
import java.io.*;
public class DirectoryTester 
{
    public static void main(String[] args) 
    {
        String name;
        String number;
        String fileName;
        Scanner kb = new Scanner(System.in);
        System.out.println("What would you like to do?");
        System.out.println("1. Load a previously saved phone directory from file");
        System.out.println("2. Add or change a directory");
        System.out.println("3. Remove an entry");
        System.out.println("4. Search for an entry");
        System.out.println("5. Display all entries");
        System.out.println("6. Save the current phone directory to a file");
        System.out.println("7. Quit the program");
        String response = kb.nextLine();
        switch (response)
        {
            case "1":
                System.out.print("Enter the name of the file: ");
                fileName = kb.nextLine();
                PhoneDirectory.fromFile(fileName);
            case "2":
                System.out.print("Enter name for the entry: ");
                name = kb.nextLine();
                System.out.print("Enter the number for the entry: ");
                number = kb.nextLine();
                PhoneDirectory.addOrChangeEntry(name, number);
            case "3":


        }
    }
}
