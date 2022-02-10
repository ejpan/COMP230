import java.util.Scanner;

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
        System.out.println(key_split);
        String[] message_split = message.split("");
        System.out.println(message_split);



        scan.close();
        
    }
}
