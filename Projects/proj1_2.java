import java.util.Scanner;
public class proj1_2
{   
    public static void main(String[] args) 
    {
        boolean keep_going = true;
        Scanner kb = new Scanner(System.in);
        while(keep_going)
        {
            System.out.println("Enter the individual key values positive or negative integers, one after another in the same line with a blank between two values): ");
            String keys = kb.nextLine();
            System.out.println("");
            String[] keys_str = keys.split("\\s+");
            System.out.println("Enter a string to be encoded: ");
            String orig_message = kb.nextLine();
            System.out.println("");

            int[] keys_int = new int[keys_str.length]; // Do I need to declare a new array each time when switching from String to int array? What if we don't know the length?
            for(int i = 0; i < keys_str.length; i++)
            {
                keys_int[i] = Integer.parseInt(keys_str[i]);
            }
            String encoded_message = "";
            String decoded_message = "";
            for(int j = 0; j < orig_message.length(); j++)
            {
                char new_char = orig_message.charAt(j);
                new_char = (char)(new_char + keys_int[j % (keys_int.length)]); // (char) converts the int to unicode
                encoded_message += new_char; 
            }

            for(int k = 0; k < encoded_message.length(); k++)
            {
                char new_char = encoded_message.charAt(k);
                new_char = (char)(new_char - keys_int[k % (keys_int.length)]);
                decoded_message += new_char;
            }
            System.out.println("The encoded message: \n" + encoded_message);
            System.out.println("");
            System.out.println("The decoded message: \n" + decoded_message + "\n");

            System.out.println("Keep going? (y for yes, n for no): ");
            String answer = kb.nextLine();
            if(answer.toLowerCase().equals("n"))
            {
                keep_going = false;
            }
        }
        kb.close();
        
    }
}