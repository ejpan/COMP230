/* Tester program that tests the methods in PhoneDiretory and the 
oject creation of DirectoryEntry
Eric Pan & Gabe Seidl
April 20, 2022
*/

import java.util.*;
import java.io.*;
public class DirectoryTester 
{
    public static void main(String[] args) 
    {
        //variables for method
        String name;
        String number;
        String fileName;
        String response = "";
        Scanner kb = new Scanner(System.in);

        PhoneDirectory phoneBook = new PhoneDirectory(); //creates directory
        while(!response.equals("7")) //runs class until user wants to quit
        {
            System.out.println("What would you like to do?");
            System.out.println("1. Load a previously saved phone directory from file");
            System.out.println("2. Add or change a directory");
            System.out.println("3. Remove an entry");
            System.out.println("4. Search for an entry");
            System.out.println("5. Display all entries");
            System.out.println("6. Save the current phone directory to a file");
            System.out.println("7. Quit the program");
            System.out.print("Select option: ");
            response = kb.nextLine();
            System.out.println("");
            switch (response) //switch statement for user to pick option
            {
                case "1": //load from file
                    System.out.print("Enter the name of the file: ");
                    fileName = kb.nextLine();
                    phoneBook.fromFile(fileName);
                    System.out.println("");
                    break;
                case "2": //add or change name
                    System.out.println("READ: Enter intended name and number of entry. If an entry already exists you will be asked if you want to update it.");
                    System.out.print("Enter the name of the entry: ");
                    name = kb.nextLine();
                    System.out.print("Enter the number of the entry: ");
                    number = kb.nextLine();
                    String old_num = phoneBook.addOrChangeEntry(name, number); //addOrChangeEntry() will return the old number or null
                    System.out.println("");
                    break;
                case "3": //delete entry
                    System.out.print("Enter name of the entry: ");
                    name = kb.nextLine();
                    DirectoryEntry de = phoneBook.removeEntry(name); //removeEntry() will return the deleted entry
                    if(de != null) //checks if the entry existed and was removed
                    {
                        System.out.println("The entry " + de.getName() + ": " + de.getNumber() + " has been removed.");
                    }
                    System.out.println("");
                    break;
                case "4": //search for entry
                    System.out.print("Enter name of the entry: ");
                    name = kb.nextLine();
                    DirectoryEntry found = phoneBook.searchEntry(name);
                    if(found != null) //checks if a matching entry was found
                    {
                        System.out.println("The entry matching " + name + " is " + found.getName() + ": " + found.getNumber());
                    }
                    System.out.println("");
                    break;
                case "5": //displays all entries
                    phoneBook.displayAllEntries();
                    System.out.println("");
                    break;
                case "6": //export to file
                    System.out.print("Enter the name of the file: ");
                    fileName = kb.nextLine();
                    phoneBook.toFile(fileName);
                    System.out.println("");
                    break;
                case "7": //exit program
                    break;
            }
    
        }
        kb.close();
    }
}
