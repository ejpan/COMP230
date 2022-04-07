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
                for (int j=i; j <= k; j++)  // runs through indeces i - k
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
        int[] maxLeftBoundSum = maxRightBoundSum(first);
        // finds the maximal subsequence sum in the second half of array starting from the left digit
        int[] maxRightBoundSum = maxLeftBoundSum(second);
        System.out.println(Arrays.toString(maxLeftSum));
        System.out.println(Arrays.toString(maxRightSum));
        System.out.println(Arrays.toString(maxLeftBoundSum));
        System.out.println(Arrays.toString(maxRightBoundSum));
        // finds the maximal subsequence sum entirely starting from the left, right, and middle
        int maxSum = Math.max(Math.max(maxLeftSum[0],maxRightSum[1]), maxLeftBoundSum[1] + maxRightBoundSum[0]);
        return maxSum; 
    }

    private static int[] maxLeftBoundSum (int[] nums)
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
            int[] left = maxLeftBoundSum(first);
            int[] right = maxLeftBoundSum(second);
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

    private static int[] maxRightBoundSum (int[] nums)
    //includes the right most digit
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
            int[] right = maxRightBoundSum(first);
            int[] left = maxRightBoundSum(second);
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
            }
            // Case 2: if sum from both arrays are greater than the greatest left digit
            if (sum > left[0])
            {
                left[0] = left[1] + right[1];
            }
            // Case 3: if total in left array is greater than the left most digit
            if (left[1] > left[0]){
                left[1] = left[0];
            }
            // Cause 4: if left most digit in left array is greater than the total and right most digit of the right array
            if (right[0] > left[0] && right[0] > left[1])
            {
                sum = left[0];
                left[0] = right[0];
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
            }
            if (sum > right[0])
            {
                right[1] = left[0] + right[0];
            }
            // Case 3: if right total is greater than right most digit
            if (right[0] > right[1]){
                right[1] = right[0];
            }
            // Case 4: if right most digit in left array is greater than the total and right most digit of the right array
            if (left[1] > right[0] && left[1] > right[1] ) {
                sum = left[1];
                right[1] = left[1];
            }
            // greatest right digit is in the second index and total sum from both arrays are in the first index
            int[] sums = {sum, right[1]};
            System.out.println(Arrays.toString(sums));
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

    private static void runFirst(int[] nums)
    {
        double start = System.nanoTime();
        System.out.println("First Algorithm: " + firstAlg(nums));
        double end = System.nanoTime();
        System.out.println("The 1st Algorithm took " + (end - start) + " nanoseconds!");
    }

    private static void runSecond(int[] nums)
    {
        double start = System.nanoTime();
        System.out.println("Second Algorithm: " + secondAlg(nums));
        double end = System.nanoTime();
        System.out.println("The 2nd Algorithm took " + (end - start) + " nanoseconds!");
    }

    private static void runThird(int[] nums)
    {
        double start = System.nanoTime();
        System.out.println("Third Alg: " + thirdAlg(nums));
        double end = System.nanoTime();
        System.out.println("The 3rd Algorithm took " + (end - start) + " nanoseconds!");
    }

    private static void runFourth(int[] nums)
    {
        double start = System.nanoTime();
        System.out.println("Fourth Alg: " + fourthAlg(nums));
        double end = System.nanoTime();
        System.out.println("The 4th Algorithm took " + (end - start) + " nanoseconds!");
    }

    private static void runAll(int[] nums)
    {
        runFirst(nums);
        runSecond(nums);
        runThird(nums);
        runFourth(nums);
    }


    public static void main(String[] args) 
    {
        boolean running = true;
        while(running)
        {
            try
            {
                boolean keep_going = true;
                String ans = "";
                Scanner input = new Scanner(System.in);
                while (keep_going)
                {
                    System.out.println("Enter file name with numbers seperated by a single comma:");
                    String filename = input.nextLine();
                    if (filename.equals("exit"))
                    {
                        running = false;
                    }
                    BufferedReader reader = new BufferedReader(new FileReader(filename));
                    //BufferedReader reader = new BufferedReader(new FileReader("numbers.txt"));
                    String line = reader.readLine();
                    System.out.println("\n");
                    String[] numbers_line = line.split(",");
                    int[] numbers = new int[numbers_line.length];
                    for (int i = 0; i < numbers_line.length; i++)
                    {
                        int temp = Integer.parseInt(numbers_line[i]);
                        numbers[i] = temp;
                    }
                    System.out.print("Which algorithm would you like to run?\n1:First Alogorithm (3 Loops)\n2: Second Algorithm (2 Loops)\n3: Third Algorithm (Recursive)\n4: Fourth Algoritm (1 Loop)\n5: Run all\n");
                    String choice = input.nextLine();
                    System.out.println("\n");
                    if (choice.equals("1")) {runFirst(numbers);}
                    else if(choice.equals("2")) {runSecond(numbers);}
                    else if(choice.equals("3")) {runThird(numbers);}
                    else if(choice.equals("4")) {runFourth(numbers);}
                    else {runAll(numbers);}
                    System.out.print("Would you like to run the program again? (Y for yes, N for no): ");
                    ans = input.nextLine();
                    if(ans.toLowerCase().equals("n"))
                    {
                        keep_going = false;
                        running = false;
                    }
                    reader.close();
                }
                input.close();

            }
            catch (FileNotFoundException e)
            {
                if(running)
                {
                    System.out.println(e.getMessage());
                    System.out.println("File not found, please try again or \"exit\" to exit: ");
                }
            } catch (IOException e)
            {
                System.out.println(e.getMessage());
            }
        }
        
    }
        
}

