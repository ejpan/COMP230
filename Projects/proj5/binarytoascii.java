import util.*;
import java.io.*;

public class binarytoascii {
    public static void main(String[] args)
    {
        try {
            Scanner input = new Scanner(System.in);
            System.out.println("Enter binary filename");
            String in = input.nextLine();
            ObjectInputStream input_file = new ObjectInputStream(new FileInputStream(in));
            PrintWriter pw = new PrintWriter(in + ".txt");
            try{
                while (true)
                {
                    pw.write(input_file.read());
                }

            }
            
        }catch(FileNotFoundException e)
        {
            System.out.println(e.getMessage());
        }catch(IOException e)
        {
            System.out.println(e.getMessage());
        }
        pw.close();

        
        
    }
}
