import java.util.Scanner;
import java.util.ArrayList;

public class project1 {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the individual key values (positive or negative"  
                    + "integers, one after another in the same line with a blank" 
                    + "between two values): ");
        String key = scan.nextLine();
        System.out.print("Enter a string to be encoded: ");
        String message = scan.nextLine();
        System.out.println(key);
        System.out.println(message);

        String[] key_split = key.split(" ");
        //System.out.println(key_split[2]);
        String[] message_split = message.split("");
        //System.out.println(message_split);
        int key_length = key_split.length;
        int key_counter = 0;
        char[] message_char = message.toCharArray();
        for (int message_counter = 0; message_counter <= message_split.length; message_counter++){ //Why can't I do char letter??
            //System.out.println(letter);
            char ch = message_char[message_counter];
            int ascii_ch = ch;
            ascii_ch += key_counter;
            char converted_ch = (char) ascii_ch;
            message_char[message_counter] = converted_ch;
            //System.out.print("Ascii value: " + message_char[message_counter]);
        
        }
        
        System.out.println(toString(message_char));    
            
        
        
        
        //System.out.println("Ascii Value:" + asciivalue1);


        scan.close();
        
    }
}
