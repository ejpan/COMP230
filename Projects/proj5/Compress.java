/* Class Compress takes in an ascii fiel and compresses it into 
a binary file.  Compress uses a hashtable to increase efficiency of 
the conversion.
Eric Pan & Gabe Seidl
May 11 ,2022
*/
import java.util.*;
import java.io.*;
import java.nio.file.*;
import java.time.Instant;

public class Compress
{
    public static void main(String[] args)
    {
        int args_count = 0;
        boolean again = true;
        String input_file;
        Scanner input = new Scanner(System.in);
        while (again)
        {
            try
            {
                if (args_count == 0){ // looks at the args array taken from the command line
                    input_file = args[0]; // takes file from command line
                    args_count++;
                }else{ // if no file in command line or not first run, ask user for a file name
                    System.out.println("Enter filename to compress: ");
                    input_file = input.nextLine();
                }
                long startTime = Instant.now().toEpochMilli();
                File in = new File(input_file);
                String output_file = input_file + ".zzz";
                File out = new File(output_file);
                BufferedReader br = new BufferedReader(new FileReader(in));
                ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(out));
                HashTableChain map = new HashTableChain();
                int counter = 32; // starting ascii value
                String inputLine;
                String prefix = "";  //variable that holds the largest prefix
                int nextChar;
                
                for (int c = 32; c <= 126; c++) //initializes all ascii values from 32->126 in hashtable with the char as key and ascii code as the value
                {
                    map.put(Character.toString((char)c),c);
                    counter++;
                } 
                map.put("\n",1); // additional characters to be initialized
                map.put("\r",2);
                map.put("\t",3); 
                
                while((nextChar = br.read()) != -1) //starts reading file
                {
                    prefix += (char)nextChar; //adds next char to prefix
                    if(map.get(prefix) == null) //each iteration checks if prefix is in the hashtable. if not in hashtable,
                    {
                        map.put(prefix, counter); //adds prefix to the table
                        counter++;
                        int num = (int)map.get(prefix.substring(0, prefix.length() - 1)); //gets value of last prefix in the table
                        os.writeInt(num); //writes the int value to binary file
                        prefix = prefix.substring(prefix.length() - 1); //new prefix is last character looked at
                    }
                }
            
                if(map.get(prefix) != null) //checks if the prefix is at the end of the file
                {
                    os.writeInt((int)map.get(prefix));
                }

                //code for the log file
                long endTime = Instant.now().toEpochMilli(); 
                long timeElapsed = endTime - startTime; //gets total compression time
                PrintWriter pw = new PrintWriter(new File(output_file + ".log"));
                pw.println("Compression of " + input_file);
                Path original = Paths.get(input_file); 
                Path compressed = Paths.get(output_file); 
                long original_size = Files.size(original); //gets input file size
                long compressed_size = Files.size(compressed); //output file size
                pw.println("Compressed from " + String.format("%,d kilobytes", original_size / 1024) + " to "+ String.format("%,d kilobytes", compressed_size / 1024));
                pw.println("Compression took: " + (timeElapsed / 1000.0000) + " seconds");
                pw.println("The dictionary contains "+ counter + " total entries");
                pw.println("The table was rehashed " + map.rehash_count + " times");
                
                
                System.out.println("Do you want to compress another file? (y for yes, n for no): ");
                String keep_going = input.nextLine();
                if (keep_going.toLowerCase().equals("n"))
                {
                    again = false;
                }
                else
                {
                    again = true;
                }
                os.close();
                br.close();
                pw.close();
            }
            catch (IOException e)
            {
                System.out.println(e.getMessage());
            }
            
        }
        input.close();
        
        
        
        
    }
}