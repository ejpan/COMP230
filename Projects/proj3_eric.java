/*A program computing the maximal subsequence sum from a sequence of integers
in a file with numbers separated by a single comma inputed by user. 
The program contains four different algorithms for the purpose of complexity analysis.
Eric Pan & Gabe Seidl
April 1, 2022*/

import java.io.*;
import java.util.*;


public class proj3_eric {
    static int firstAlg (int[] nums) 
    {
        int maxSum = 0;
        for (int i=0; i < nums.length; i++)
        {
            for (int k=i; k < nums.length; k++)
            {
                int sum = 0;
                for (int j=i; j < k; j++)
                {
                    sum += nums[j];
                }
                if (sum > maxSum)
                {
                    maxSum = sum;
                }
            }
            
        }
        return maxSum;
    }

    static int secondAlg (int[] nums)
    {
        int maxSum = 0;
        for(int i = 0; i < nums.length; i++)
        {
            int sum = 0;
            for(int j = i; j < nums.length; j++)
            {
                sum += nums[j];
                if (sum > maxSum)
                {
                    maxSum = sum;
                }
            }
        }
        return maxSum;
    }

    static void thirdAlg (int[] nums)
    {
        int[] first = Arrays.copyOfRange(nums, 0, nums.length/2);
        int[] second = Arrays.copyOfRange(nums, nums.length/2, nums.length);
        int[] maxLeftSum = leftSum(first);
        int[] maxRightSum = rightSum(second);
        System.out.println(maxLeftSum[0]);
        System.out.println(maxRightSum[1]);
        // maxSum = 0
        // sum = left + right  : return maxSum
        // else (left) 
        
    }

    static int[] leftSum (int[] nums)
    {
        if (nums.length == 1){
            int[] digit = {nums[0],nums[0]};
            return digit;
        }else{
            int[] first = Arrays.copyOfRange(nums, 0, nums.length/2);
            //System.out.println(Arrays.toString(first));
            int[] second = Arrays.copyOfRange(nums, nums.length/2, nums.length);
            //System.out.println(Arrays.toString(second));
            int[] left = leftSum(first);
            int[] right = leftSum(second);
            int sum = left[1] + right[1];
            if (left[1] + right[0] > left[0])
            {
                left[0] = left[1] + right[0];
            }else if (sum > left[0])
            {
                left[0] = left[1] + right[1];
            }
            int[] sums = {left[0], sum};
            //System.out.println(Arrays.toString(sums));
            return sums;
        }
        

    }

    static int[] rightSum (int[] nums)
    {
        if (nums.length == 1){
            int[] digit = {nums[0],nums[0]};
            return digit;
        }else{
            int[] first = Arrays.copyOfRange(nums, nums.length/2, nums.length);
            int[] second = Arrays.copyOfRange(nums, 0, nums.length/2);
            //System.out.println(Arrays.toString(first));
            
            //System.out.println(Arrays.toString(second));
            int[] right = rightSum(first);
            int[] left = rightSum(second);
            int sum = left[0] + right[0];
            if (right[0] + left[1] > right[1])
            {
                right[1] = right[0] + left[1];
            }else if (sum > right[0])
            {
                right[1] = left[0] + right[0];
            }
            int[] sums = {sum, right[1]};
            System.out.println(Arrays.toString(sums));
            return sums;
        }
        

    }

    static int fourthAlg (int[] nums)
    {
        int maxSum = 0;
        int sum = 0;
        for (int i = 0; i < nums.length; i++)
        {
            sum += nums[i];
            if (sum > maxSum)
            {
                maxSum = sum;
            }
            else
            {
                if (sum < 0)
                {
                    sum = 0;
                }
            }

        }
        return maxSum;
    }


    


    public static void main(String[] args) 
    {
        try
        {
            Scanner input = new Scanner(System.in);
            //System.out.println("Enter file name with numbers seperated by a single comma");
            //String filename = input.nextLine();
            //BufferedReader reader = new BufferedReader(new FileReader(filename));
            BufferedReader reader = new BufferedReader(new FileReader("numbers.txt"));
            String line = reader.readLine();
            System.out.println(line);
            String[] numbers_line = line.split(",");
            System.out.println(Arrays.toString(numbers_line));
            int[] numbers = new int[numbers_line.length];
            for (int i = 0; i < numbers_line.length; i++)
            {
                int temp = Integer.parseInt(numbers_line[i]);
                numbers[i] = temp;
            }
            thirdAlg(numbers);
            //System.out.println(fourthAlg(numbers));

            input.close();
            reader.close();

        }
        catch (FileNotFoundException e)
        {
            System.out.println(e.getMessage());
        } catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
        
    }
        
    }

