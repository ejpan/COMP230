/*A Caesar cipher program that takes a sequence of individual key values and a line of string to be
encoded by changing each letter in the line of string in the order of the individual key values
using the ASCII character number system.
Eric Pan & Gabe Seidl
February 17,2022 */

// Imports Scanner class to get user inputs.
import java.util.Scanner;
public class proj1_2
{   
    public static void main(String[] args) 
    {
        boolean keep_going = true;
        // Creates scanner object.
        Scanner input = new Scanner(System.in);
        while(keep_going)
        {
            System.out.println("Enter the individual key values positive or negative integers, one after another in the same line with a blank between two values): ");
            // Gets user input for individual key values.
            String keys = input.nextLine();
            System.out.println("");
            // Splits the string key values to individual elements in a list.
            String[] keys_str = keys.split("\\s+");
            System.out.println("Enter a string to be encoded: ");
            // Gets user input for string to be encoded.
            String orig_message = input.nextLine();
            System.out.println("");

            // Creates new list to store the key values as integers.
            int[] keys_int = new int[keys_str.length];
            // Converts the key values to integers. 
            for(int key = 0; key < keys_str.length; key++)
            {
                keys_int[key] = Integer.parseInt(keys_str[key]);
            }
            String encoded_message = "";
            String decoded_message = "";
            // Encodes user inputted string.
            for(int c = 0; c < orig_message.length(); c++)
            {
                char new_char = orig_message.charAt(c);
                // Adds key value to character. Uses (c mod key length) to repeat key list once it reaches to the end.
                // Uses mod 95 to get key value staying between 32 and 126. 
                int int_char = (new_char + (keys_int[c % (keys_int.length)] % 95));
                // Key value being added to char will be less than 95, so we will only need to find the remainder of 127 and add 32 to it
                // as ASCII charaacter numbers go from 32 to 126 based on program guidelines. 
                if (int_char > 126)
                {
                    int_char = (int_char % 127) + 32;
                }
                new_char = (char) int_char;
                // Adds new character to String for output.
                encoded_message += new_char; 
            }
            // Decodes encoded message to be used for decoded message output.
            for(int c = 0; c < encoded_message.length(); c++)
            {
                char new_char = encoded_message.charAt(c);
                int int_char = (new_char - (keys_int[c % (keys_int.length)] % 95));
                // Key value may be greater than 32 so integer of char after the subtraction may be less than 0.
                if (int_char < 0)
                {
                    // If int_char is less than 0, we find the absolute value and add 31 to account for the digits between 0 and 31.
                    // We add 95 as there are 95 digits between 32 (inclusive) to 126 (inclusive).
                    int_char += 95 + (Math.abs(int_char) + 31);
                } else if (int_char < 32) {
                    // If character is greater than 0 and less than 32, we add 95. 
                    int_char += 95;
                }
                new_char = (char) int_char;
                // Adds character to String for output.
                decoded_message += new_char;
            }
            System.out.println("The encoded message: \n" + encoded_message);
            System.out.println("");
            System.out.println("The decoded message: \n" + decoded_message + "\n");
            // Asks if user wants to run program again.
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
// Test Case:
// 5 12 -3 8 -9 4 10
// All programmers are playwrights and all computers are lousy actors.