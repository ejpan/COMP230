import java.io.*;
import java.util.Scanner;
public class proj2_gabe 
{
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        boolean again = true;
        while (again)
        {
            try
            {
                System.out.println("Please enter the name of an ASCII file that contains a sorted list of integer numbers, one per line: ");
                String input_file = input.nextLine();  // gets input file name
                System.out.println("Please enter the name of the output file that saves the result from the program: ");
                String output_file = input.nextLine();  // gets output file name
                File in = new File(input_file);
                File out = new File(output_file);
                Scanner reader = new Scanner(in);  // scanner to read from input file
                PrintWriter pw = new PrintWriter(new FileOutputStream(out));  // printwriter to print to output file
                int total = 0;
                int count = 0;
                if (reader.hasNextLine())  // checks for empty file
                {
                    int next_num = reader.nextInt();  // gets first number from file
                    pw.println(next_num); // first number will always be printed to output file
                    int last_num = next_num;
                    total += next_num;  // adds to total for average
                    count += 1;
                    while (reader.hasNextLine())  // loops through whole file
                    {
                        if (last_num != next_num)  // checks if the last unique number is equal to the next number in file
                        {
                            pw.println(next_num);
                            last_num = next_num;
                            total += next_num;
                            count += 1;
                        }
                        else
                        {
                            next_num = reader.nextInt();  // loop skips non unique numbers and moves to the next line
                        }
                    }
                    if (last_num != next_num)  // final if statement to check if the last number in file is unique
                    {
                        pw.println(next_num);
                        last_num = next_num;
                        total += next_num;
                        count += 1;
                    }
                    pw.println(Math.round((total/1.0/count) * 100.0) / 100.0);  // converts average to double and rounds to 2 decimals
                }
                else
                {
                    pw.println("Empty File");  // if file empty
                }
                reader.close();
                pw.close();
            }
            catch (IOException e)
            {
                System.out.println(e.getMessage());
            }
            System.out.println("Do you want to run the program again? (y for yes, n for no): "); // asks user if they want to run again
            String answer = input.nextLine();
            if(answer.toLowerCase().equals("n"))
            {
                again = false;
            }
        }   
        input.close();
    }      
}


