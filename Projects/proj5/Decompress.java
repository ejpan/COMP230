import java.util.*;
import java.io.*;
import java.nio.file.*;
import java.time.Instant;

public class Decompress {
    public static void main(String[] args)
    {
        int args_count = 0;
        boolean again = true;
        String in;
        Scanner input = new Scanner(System.in);
        while(again)
        {
            try 
            {
                if (args_count == 0){
                    in = args[0];
                    args_count++;
                }else{
                    System.out.println("Enter binary filename to decompress: ");
                    in = input.nextLine();
                }
                long startTime = Instant.now().toEpochMilli();
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
                ObjectInputStream input_file = new ObjectInputStream(new FileInputStream(in));
                PrintWriter pw = new PrintWriter(new File(in + "ASCII.txt"));
                try{
                    String second;
                    int first_int = input_file.readInt();
                    String first = map.get(first_int).toString();
                    pw.write(first);
                    
                    while (true)
                    {
                        first = map.get(first_int).toString();
                        int second_int = input_file.readInt();
                        if(map.get(second_int) != null)
                        {
                            
                            second = map.get(second_int).toString();
                            
                            pw.write(second);
                            map.put(counter,first + second.charAt(0));
                            //System.out.println(first + second.charAt(0));
                            first_int = second_int;
                            counter++;
                        }else
                        {
                            pw.write(first + first.charAt(0));
                            map.put(counter, first + first.charAt(0));
                            first_int = second_int;
                            counter++;
                        }
                        
                    }
                    
                }catch (EOFException e){
                    System.out.println("End of reading from file"); //change this to "" 
                    
                }
                long endTime = Instant.now().toEpochMilli();
                long timeElapsed = endTime - startTime;

                PrintWriter logPW = new PrintWriter(new File(in + "ASCII.txt" + ".log"));
                logPW.println("Decompression for file " + in);
                logPW.println("Decompression took " + (timeElapsed / 1000.0000) + " seconds");
                logPW.println("The table was doubled " + map.rehash_count + " times");

                System.out.println("Do you want to compress another file? (y for yes, n for no): ");
                String keep_going = input.nextLine();
                if (keep_going.toLowerCase().equals("n")){
                    again = false;
                }else{
                    again = true;
                }
                input_file.close();
                pw.close();
                logPW.close();
            }catch(FileNotFoundException e)
            {
                System.out.println(e.getMessage());
            }catch(IOException e)
            {
                System.out.println(e.getMessage());
            }
        }
        input.close();
        
        
    }
}
