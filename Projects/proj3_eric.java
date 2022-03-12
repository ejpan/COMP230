import java.io.*;
import java.util.*;

public class proj3_eric {
    static int firstAlg (List<Integer> nums) 
    {
        int largest_sum = 0;
        for (int num : nums)
        {
            if (num < 0)
            {
                continue;
            }else
            {
                largest_sum += num;
            }
        }
        return largest_sum;
    }

    static int secondAlg (List<Integer> nums)
    {
        int largest_sum = 0;
        for (int num : nums)
        {
            int check = largest_sum + num;
            if (check > largest_sum)
            {
                largest_sum += num;
            }
        }
        return largest_sum;
    }

    static int thirdAlg (List<Integer> nums)
    {
        
    }
    public static void main(String[] args) 
    {
        try
        {
            Scanner input = new Scanner(System.in);
            System.out.println("Enter file name with numbers seperated by a single comma");
            String filename = input.nextLine();
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line = reader.readLine();
            String[] numbers_line = line.split(",",2);
            List<Integer> numbers = new ArrayList<>();
            for (String i : numbers_line){
                int temp = Integer.parseInt(i);
                numbers.add(temp);
            System.out.println(firstAlg(numbers));
            System.out.println(secondAlg(numbers));

        }
        input.close();
        reader.close();

        }catch (FileNotFoundException e)
        {
            System.out.println(e.getMessage());
        } catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
        
        }
        
    }

