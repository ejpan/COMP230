import java.util.*;
import java.io.*;
import java.nio.file.*;

public class Compress
{
    public static void main(String[] args)
    {
        try{
            // String input_file = args[0];
            String input_file = "speech.txt";
            File in = new File(input_file);
            String output_file = input_file + ".zzz";
            File out = new File(output_file);
            //PrintWriter pw = new PrintWriter(new FileOutputStream(out));
            BufferedReader br = new BufferedReader(new FileReader(in));
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(out));
            HashTableChain map = new HashTableChain();
            int counter = 32;
            String inputLine;
            String prefix = "";
            int nextChar;
            
            for (int c = 32; c <= 126; c++)
            {
                map.put(Character.toString((char)c),c);
                counter++;
            } 
            map.put("\n",1);
            map.put("\r",2);
            map.put("\t",3); 
            
            while((nextChar = br.read()) != -1)
            {
                prefix += (char)nextChar;
                //System.out.println(prefix);
                if(map.get(prefix) == null)
                {
                    map.put(prefix, counter);
                    counter++;
                    int num = (int)map.get(prefix.substring(0, prefix.length() - 1));
                    os.writeInt(num);
                    prefix = prefix.substring(prefix.length() - 1);
                    //System.out.println(prefix);
                }
            }
        
            if(map.get(prefix) != null)
            {
                os.writeInt((int)map.get(prefix));
            }
            
            PrintWriter pw = new PrintWriter(new File(output_file + ".log"));
            pw.println("Compression of " + input_file);
            Path original = Paths.get(input_file);
            Path compressed = Paths.get(output_file);
            long original_size = Files.size(original);
            long compressed_size = Files.size(compressed);;
            pw.println("Compressed from " + String.format("%,d kilobytes", original_size / 1024) + " to "+ String.format("%,d kilobytes", compressed_size / 1024));
            pw.println("Compression took: ");
            pw.println("The dictionary contains "+ counter + " total entries");
            pw.println("The table was rehashed ");

            os.close();
            br.close();
            pw.close();


        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        
    }
}