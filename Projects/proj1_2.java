/*A Caesar cipher program that takes a sequence of individual key values and a line of string to be
encoded by changing each letter in the line of string in the order of the individual key values
using the ASCII character number system.
Eric Pan & Gabe Seidl
February 10,2022 */

// imports Scanner class to get user inputs
import java.util.Scanner;
public class proj1_2
{   
    public static void main(String[] args) 
    {
        boolean keep_going = true;
        // creates scanner object
        Scanner input = new Scanner(System.in);
        while(keep_going)
        {
            System.out.println("Enter the individual key values positive or negative integers, one after another in the same line with a blank between two values): ");
            // gets user input for individual key values
            String keys = input.nextLine();
            System.out.println("");
            // splits the string key values to individual elements in a list
            String[] keys_str = keys.split("\\s+");
            System.out.println("Enter a string to be encoded: ");
            // gets user input for string to be encoded
            String orig_message = input.nextLine();
            System.out.println("");

            // creates new list to store the key values as integers
            int[] keys_int = new int[keys_str.length];
            // converts the key values to integers 
            for(int key = 0; key < keys_str.length; key++)
            {
                keys_int[key] = Integer.parseInt(keys_str[key]);
            }
            String encoded_message = "";
            String decoded_message = "";
            // encodes user inputted string
            for(int c = 0; c < orig_message.length(); c++)
            {
                char new_char = orig_message.charAt(c);
                // adds key value to character. uses (c mod key length) to repeat key list once it reaches to the end.
                // uses mod 95 to get key value staying between 32 and 126 
                int int_char = (new_char + (keys_int[c % (keys_int.length)] % 95));
                // key value being added to char will be < 95, so we will only need to find the remainder of 127 and add 32 to it 
                // as ASCII charaacter numbers go from 32 to 126
                if (int_char > 126)
                {
                    int_char = (int_char % 127) + 32;
                }
                new_char = (char) int_char;
                // adds new character to String for output
                encoded_message += new_char; 
            }

            for(int c = 0; c < encoded_message.length(); c++)
            {
                char new_char = encoded_message.charAt(c);
                int int_char = (new_char - (keys_int[c % (keys_int.length)] % 95));
                if (int_char < 32 && int_char < 0)
                {
                    int_char = 127 - (Math.abs(int_char) + 32);
                } else{
                    int_char = 127 - int_char;
                }
                new_char = (char) int_char;
                decoded_message += new_char;
            }
            System.out.println("The encoded message: \n" + encoded_message);
            System.out.println("");
            System.out.println("The decoded message: \n" + decoded_message + "\n");

            System.out.println("Do you want to run the program again? (y for yes, n for no): ");
            String answer = input.nextLine();
            if(answer.toLowerCase().equals("n"))
            {
                keep_going = false;
            }
        }
        input.close();
        
    }
}