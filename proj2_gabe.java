import java.io.*;
import java.util.Scanner;
public class proj2_gabe {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        boolean again = true;
            while (again){
                try{
                System.out.println("Please enter the name of an ASCII file that contains a sorted list of integer numbers, one per line: ");
                String input_file = input.nextLine();
                System.out.println("Please enter the name of the output file that saves the result from the program: ");
                String output_file = input.nextLine();
                File in = new File(input_file);
                File out = new File(output_file);
                Scanner reader = new Scanner(in);
                PrintWriter pw = new PrintWriter(new FileOutputStream(out));
                int total = 0;
                int count = 0;
                if (reader.hasNextLine()){
                    int next_num = reader.nextInt();
                    int last_num = next_num - 1;
                    while (reader.hasNextLine()){
                        if (last_num != next_num){
                            pw.println(next_num);
                            last_num = next_num;
                            total += next_num;
                            count += 1;
                        }else{
                            next_num = reader.nextInt();
                        }
                    }
                    if (last_num != next_num){
                        pw.println(next_num);
                        last_num = next_num;
                        total += next_num;
                        count += 1;
                    }
                    pw.println(total/count/1.0);
                }else{
                    pw.println(0);
                }
                reader.close();
                pw.close();
            }catch (IOException e){
                System.out.println(e.getMessage());
            }
            System.out.println("Do you want to run the program again? (y for yes, n for no): ");
            String answer = input.nextLine();
            if(answer.toLowerCase().equals("n"))
            {
                again = false;
            }
    }   
    input.close();
       
}
}

