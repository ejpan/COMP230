import java.util.*;
import java.io.*;
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
                }
            }
        
            if(map.get(prefix) != null)
            {
                os.writeChar((int)map.get(prefix));
            }

            os.close();
            br.close();

        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        
    }
}