import java.util.*;
import java.io.*;

public class binarytoascii {
    public static void main(String[] args)
    {
        try {
            HashTableChain map = new HashTableChain();
            int counter = 32;
            String inputLine;
            String prefix = "";
            int nextChar;
            
            for (int c = 32; c <= 126; c++)
            {
                map.put(c,Character.toString((char)c));
                counter++;
            } 
            map.put(1,"\n");
            map.put(2,"\r");
            map.put(3,"\t");  
            Scanner input = new Scanner(System.in);
            System.out.println("Enter binary filename: ");
            String in = input.nextLine();
            ObjectInputStream input_file = new ObjectInputStream(new FileInputStream(in));
            PrintWriter pw = new PrintWriter(new File(in + "ASCII.txt"));
            try{
                String second;
                int first_int = input_file.readInt();
                String first = map.get(first_int).toString();
                pw.write(first);
                //System.out.println(first);
                while (true)
                {
                    first = map.get(first_int).toString();
                    int second_int = input_file.readInt();
                    if(map.get(second_int) != null)
                    {
                        
                        second = map.get(second_int).toString();
                        System.out.println(second);
                        pw.write(second);
                        map.put(counter,first + second.charAt(0));
                        //System.out.println(first + second.charAt(0));
                        first_int = second_int;
                    }else
                    {
                        pw.write(first + first.charAt(0));
                        map.put(counter, first + first.charAt(0));
                        first_int = second_int;
                    }
                }

            }catch (EOFException e){
                System.out.println("End of reading from file"); //change this to "" 
            }
        
            input.close();
            input_file.close();
            pw.close();
            
        }catch(FileNotFoundException e)
        {
            System.out.println(e.getMessage());
        }catch(IOException e)
        {
            System.out.println(e.getMessage());
        }
        

        
        
    }
}
