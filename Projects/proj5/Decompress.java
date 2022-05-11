/* A decompressive algorithm that reads a user inputted binary file and ouputs the ASCII file using a hashmap for efficiency. 
Program also outputs a log file containing the time it took to decompress and the number of times the hashtable was doubled.
Eric Pan & Gabe Seidl
May 11,2022 */

import java.util.*;
import java.io.*;
import java.nio.file.*;
import java.time.Instant;

public class Decompress {
    public static void main(String[] args)
    {
        // keeps track of arguement array for input file
        int args_count = 0;
        boolean again = true;
        String in;
        Scanner input = new Scanner(System.in);
        // if user wants to run program again
        while(again)
        {
            try 
            {
                // file is the args[0] for first input file
                if (args_count == 0){
                    in = args[0];
                    args_count++;
                }else{
                    System.out.println("Enter binary filename to decompress or press ctrl c to end program: ");
                    in = input.nextLine();
                }
                long startTime = Instant.now().toEpochMilli(); //starts timer
                HashTableChain map = new HashTableChain(); // constructs hashmap table
                int counter = 32; //starts at 32 because ASCII value of 32 is space and ASCII values after 32 contains the  most used english characters
                String inputLine;
                String prefix = "";
                int nextChar;
                // adds ASCCi number to key and the ASCCI characters to value from 32 to 126
                for (int c = 32; c <= 126; c++)
                {
                    map.put(c,Character.toString((char)c));
                    counter++;
                }
                // adds tabbing, spacing, and new line methods that could be in an ASCII file to the hashtable 
                map.put(1,"\n");
                map.put(2,"\r");
                map.put(3,"\t");  
                // Stream to read from a binary file
                ObjectInputStream input_file = new ObjectInputStream(new FileInputStream(in));
                in = in.substring(0,in.length() - 4); // gets rid of .zzz from user inputted binary file
                PrintWriter pw = new PrintWriter(new File(in)); // stream to write to ASCII file
                // Decompression Algorithm
                try{
                    String second;
                    int first_int = input_file.readInt();
                    String first = map.get(first_int).toString();
                    pw.write(first); // writes first character 
                    while (true)
                    {
                        first = map.get(first_int).toString();
                        int second_int = input_file.readInt();
                        if(map.get(second_int) != null) // if the second hashcode is not in the hashmap
                        {
                            
                            second = map.get(second_int).toString();
                            pw.write(second);
                            // adds the first hashcode and the first character in the second hashcode into the hashmap
                            map.put(counter,first + second.charAt(0)); 
                            first_int = second_int;
                            counter++;
                        }else
                        {
                            pw.write(first + first.charAt(0)); // writes first hashcode and the first hashcode character to the ASCII file
                            map.put(counter, first + first.charAt(0)); // adds the first hashcode and the first hashcode character to the hashmap
                            first_int = second_int;
                            counter++;
                        }
                        
                    }
                // End of file exception
                }catch (EOFException e){
                    System.out.println("End of reading from file"); 
                    
                }
                long endTime = Instant.now().toEpochMilli(); // ends timer
                long timeElapsed = endTime - startTime;
                //Creates log file
                PrintWriter logPW = new PrintWriter(new File(in + ".log"));
                logPW.println("Decompression for file " + in);
                logPW.println("Decompression took " + (timeElapsed / 1000.0000) + " seconds");
                logPW.println("The table was doubled " + map.rehash_count + " times");
                // Asks user if they want to run program again
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
