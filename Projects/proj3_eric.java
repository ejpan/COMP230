/*A program computing the maximal subsequence sum from a sequence of integers
in a file with numbers separated by a single comma inputed by user. 
The program contains four different algorithms for the purpose of complexity analysis.
Eric Pan & Gabe Seidl
April 1, 2022*/

import java.io.*;
import java.util.*;


public class proj3_eric 
{
    private static int firstAlg (int[] nums) 
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

    private static int secondAlg (int[] nums)
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

    private static int thirdAlg (int[] nums)
    {
        int[] first = Arrays.copyOfRange(nums, 0, nums.length/2);
        int[] second = Arrays.copyOfRange(nums, nums.length/2, nums.length);
        int[] maxLeftSum = maxLeftSum(first);
        int[] maxRightSum = maxRightSum(second);
        int[] maxLeftBoundSum = maxLeftBoundSum(first);
        int[] maxRightBoundSum = maxRightBoundSum(second);
        //System.out.println(maxLeftBoundSum[1]);
        //System.out.println(maxRightBoundSum[0]);
        int maxSum = Math.max(Math.max(maxLeftSum[0],maxRightSum[1]), maxLeftBoundSum[1] + maxRightBoundSum[0]);
        return maxSum; 
    }

    private static int[] maxLeftSum (int[] nums)
    {
        if (nums.length == 1){
            int[] digit = {nums[0],nums[0]};
            return digit;
        }else{
            int[] first = Arrays.copyOfRange(nums, 0, nums.length/2);
            //System.out.println(Arrays.toString(first));
            int[] second = Arrays.copyOfRange(nums, nums.length/2, nums.length);
            //System.out.println(Arrays.toString(second));
            int[] left = maxLeftSum(first);
            int[] right = maxLeftSum(second);
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

    private static int[] maxRightSum (int[] nums)
    {
        if (nums.length == 1){
            int[] digit = {nums[0],nums[0]};
            return digit;
        }else{
            int[] first = Arrays.copyOfRange(nums, nums.length/2, nums.length);
            int[] second = Arrays.copyOfRange(nums, 0, nums.length/2);
            //System.out.println(Arrays.toString(first));
            
            //System.out.println(Arrays.toString(second));
            int[] right = maxRightSum(first);
            int[] left = maxRightSum(second);
            int sum = left[0] + right[0];
            // Case 1: If right recursion sum and left dights of left recursion sum is greater than right most digit
            if (right[0] + left[1] > right[1])
            {
                right[1] = right[0] + left[1];
            // Case 2: If total sum from right and left recursion is greater than right most digit
            }else if (sum > right[0])
            {
                right[1] = left[0] + right[0];
            }
            // right[1] keeps track of the highest subsequence sum on the right
            // variable sum keeps track of total sum from both left and right recursions
            int[] sums = {sum, right[1]};
            //System.out.println(Arrays.toString(sums));
            return sums;
        }
        

    }

    private static int[] maxLeftBoundSum(int[] nums)
    {
        if (nums.length == 1)
        {
            int[] digit = {nums[0],nums[0]};
            return digit;
        }else{
            int[] first = Arrays.copyOfRange(nums, 0, nums.length/2);
            int[] second = Arrays.copyOfRange(nums, nums.length/2, nums.length);
            int[] right = maxLeftBoundSum(second);
            int[] left = maxLeftBoundSum(first);
            
            int sum = left[0] + right[0];
            if (right[0] + left [0] > right[0])
            {
                right[1] = right[0] + left[0];
            }else if(right[0] + left[1] > right[0])
            {
                right[1] = right[0] + left[1];
            }
            int[] sums = {sum,right[1]};
            return sums;
        }
    }

    private static int[] maxRightBoundSum(int[] nums){
        if (nums.length == 1)
        {
            int[] digit = {nums[0],nums[0]};
            return digit;
        }else{
            int[] first = Arrays.copyOfRange(nums, 0, nums.length/2);
            int[] second = Arrays.copyOfRange(nums, nums.length/2, nums.length);
            int[] left = maxRightBoundSum(first);
            int[] right = maxRightBoundSum(second);
            int sum = left[1] + right[1];
            if (left[1] + right[1] > left[0])
            {
                left[0] = left[1] + right[1];
            }else if(left[1] + right[0] > left[0])
            {
                left[0] = left[1] + right[0];
            }
            int[] sums = {left[0], sum};
            return sums;
        }
    }



    private static int fourthAlg (int[] nums)
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
            System.out.println("First Algorithm: " + firstAlg(numbers));
            System.out.println("Second Algorithm: " + secondAlg(numbers));
            System.out.println("Third Alg: " + thirdAlg(numbers));
            System.out.println("Fourth Alg: " + fourthAlg(numbers));
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

