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
        int maxSum = 0;  //variable contains current max substring sum
        for (int i=0; i < nums.length; i++)  // 3 for loops that find every possible subsequence sum
        {                                    // by setting every index as the start point, i, and increasing the 
                                             // end point, k, through each iteration and adding indexes i-k by 
                                             // iterating through the j loop.
            for (int k=i; k < nums.length; k++) // starts at i and changes end point each iteration
            {
                int sum = 0;
                for (int j=i; j < k; j++)  // runs through indeces i - k
                {
                    sum += nums[j];
                }
                if (sum > maxSum)
                {
                    maxSum = sum;  // replaces maxSum if necessary
                }
            }
            
        }
        return maxSum;
    }

    private static int secondAlg (int[] nums)
    {
        int maxSum = 0;
        // loops through nums 
        for(int i = 0; i < nums.length; i++)
        {
            int sum = 0;
            for(int j = i; j < nums.length; j++) // inner loop starts at index i and goes to the end
                                                // of the array, adding each consectutive number
                                                // to check for max subsequence sum
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
        // splits array in parameter in half
        int[] first = Arrays.copyOfRange(nums, 0, nums.length/2);
        int[] second = Arrays.copyOfRange(nums, nums.length/2, nums.length);
        // finds the maximal subsequence sum in the first half of array starting from the left digit
        int[] maxLeftSum = maxLeftSum(first);
        // finds the maximal subsequence sum in the second half of array starting from the right digit
        int[] maxRightSum = maxRightSum(second);
        // finds the maximal subsequence sum in the first half of array starting from the right digit
        int[] maxLeftBoundSum = maxRightSum(first);
        // finds the maximal subsequence sum in the second half of array starting from the left digit
        int[] maxRightBoundSum = maxLeftSum(second);
        // finds the maximal subsequence sum entirely starting from the left, right, and middle
        int maxSum = Math.max(Math.max(maxLeftSum[0],maxRightSum[1]), maxLeftBoundSum[1] + maxRightBoundSum[0]);
        return maxSum; 
    }

    private static int[] maxLeftSum (int[] nums)
    {
        //base case
        if (nums.length == 1){
            int[] digit = {nums[0],nums[0]};
            return digit;
        }else{
            //splits array in parameter in half
            int[] first = Arrays.copyOfRange(nums, 0, nums.length/2);
            int[] second = Arrays.copyOfRange(nums, nums.length/2, nums.length);
            //recursion for both arrays. left array first because we are finding the maxsum for the left
            int[] left = maxLeftSum(first);
            int[] right = maxLeftSum(second);
            // The greatest left digit is preserved in the first index and the total sum for all digits is preserved in the second index
            int sum = left[1] + right[1];
            // Case 1: if left total + right greatest sum is greater than the greatest left digit
            if (left[1] + right[0] > left[0])
            {
                left[0] = left[1] + right[0];
            // Case 2: if sum from both arrays are greater than the greatest left digit
            }else if (sum > left[0])
            {
                left[0] = left[1] + right[1];
            }
            // greatest left digit is the first index and total sum from both arrays are in the second index
            int[] sums = {left[0], sum};
            return sums;
        }
    }

    private static int[] maxRightSum (int[] nums)
    {
        // base case
        if (nums.length == 1){
            int[] digit = {nums[0],nums[0]};
            return digit;
        }else{
            //splits array in parameter in half
            int[] first = Arrays.copyOfRange(nums, nums.length/2, nums.length);
            int[] second = Arrays.copyOfRange(nums, 0, nums.length/2);
            //recursion for both arrays. right array first because we are finding the max sum for the right
            int[] right = maxRightSum(first);
            int[] left = maxRightSum(second);
            // The greatest right digit is preserved in right[1] and the total sum for all digits is preserved in the first index
            int sum = left[0] + right[0];
            // Case 1: if right total and left greatest sum is greater than the greatest right digit
            if (right[0] + left[1] > right[1])
            {
                right[1] = right[0] + left[1];
            // Case 2: if sum from both arrays are greater than the greatest right digit
            }else if (sum > right[0])
            {
                right[1] = left[0] + right[0];
            }
            // greatest right digit is in the second index and total sum from both arrays are in the first index
            int[] sums = {sum, right[1]};
            return sums;
        }
        

    }


    private static int fourthAlg (int[] nums)
    {
        int maxSum = 0;
        int sum = 0;
        // iterates through each element in nums
        for (int i = 0; i < nums.length; i++)
        {
            // adds each element to the variable sum
            sum += nums[i];
            // if sum is greater than 0, assign sum to maxSum
            if (sum > maxSum)
            {
                maxSum = sum;
            }
            else
            {
                // if sum is negative, reset sum to 0
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

