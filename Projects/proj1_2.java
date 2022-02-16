/*A Caesar cipher program that takes a sequence of individual key values and a line of string to be
encoded by changing each letter in the line of string in the order of the individual key values
using the ASCII character number system.
Eric Pan & Gabe Seidl
February 10,2022 */

import java.util.Scanner;
public class proj1_2
{   
    public static void main(String[] args) 
    {
        boolean keep_going = true;
        Scanner input = new Scanner(System.in);
        while(keep_going)
        {
            System.out.println("Enter the individual key values positive or negative integers, one after another in the same line with a blank between two values): ");
            String keys = input.nextLine();
            System.out.println("");
            String[] keys_str = keys.split("\\s+");
            System.out.println("Enter a string to be encoded: ");
            String orig_message = input.nextLine();
            System.out.println("");

            int[] keys_int = new int[keys_str.length]; 
            for(int key = 0; key < keys_str.length; key++)
            {
                keys_int[key] = Integer.parseInt(keys_str[key]);
            }
            String encoded_message = "";
            String decoded_message = "";
            for(int ch = 0; ch < orig_message.length(); ch++)
            {
                char new_char = orig_message.charAt(ch);
                new_char = (char)(new_char + (keys_int[ch % (keys_int.length)] % 95)); 
                encoded_message += new_char; 
            }

            for(int ch = 0; ch < encoded_message.length(); ch++)
            {
                char new_char = encoded_message.charAt(ch);
                new_char = (char)(new_char - (keys_int[ch % (keys_int.length)] % 95));
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