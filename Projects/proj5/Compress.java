import java.util.*;
import java.io.*;
public class Compress
{
    public static void main(String[] args)
    {
        try{
            // String input_file = args[0];
            String input_file = "dict.txt";
            File in = new File(input_file);
            String output_file = input_file + ".zzz";
            File out = new File(output_file);
            PrintWriter pw = new PrintWriter(new FileOutputStream(out));
            BufferedReader br = new BufferedReader(new FileReader(in));
            HashTableChain map = new HashTableChain();
            int counter = 0;
            String inputLine;
            while((inputLine = br.readLine()) != null)
            {
                for (int i = 0; i<inputLine.length(); i++){
                    char c = inputLine.charAt(i);
                    String s = Character.toString(c);
                    if(map.get(s) == null)
                    {
                        map.put(s,counter);
                        counter++;
                        pw.println(s);
                        pw.println(counter);
                    }

                }
                
                
            }
            
            pw.close();
            br.close();

        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        
    }
}