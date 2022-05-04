import java.util.*;
import java.io.*;
public class Compress
{
    public static void main(String[] args)
    {
        String input_file = args[0];
        File in = new File(input_file);
        String output_file = input_file + ".zzz";
        File out = new File(output_file);
        Printwriter pw = new Printwriter(new FileOutputStream(out));
        BufferedReader br = new BufferedReader(new FileReader(in));
    }
}