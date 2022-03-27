import java.util.Scanner;

public class lab71 {
    static int iterationAlg(int num)
    {
        if (num == 0)
        {
            return 0;
        } else if (num == 1)
        {
            return 1;
        }else
        {
            int total = 1;
            int t_0 = 0;
            int t_1 = 1;
            for (int i = 0; i <= num-2; i++)
            {
                total = t_0 + t_1; 
                t_0 = t_1;
                t_1 = total;
            }
            return total;
        }
        
    }

    static int recursiveAlg(int num)
    {
        if (num == 0)
        {
            return 0;
        }
        else if (num == 1)
        {
            return 1;
        }
        else
        {
            return recursiveAlg(num - 1) + recursiveAlg(num - 2);
        }
    }
    public static void main(String[] args)
    {
        boolean again = true;
        Scanner input = new Scanner(System.in);
        while (again)
        {
            System.out.println("Enter the Fibonacci degree of n: ");
            int fib = input.nextInt();
            String xtra_space = input.nextLine();
            System.out.println(iterationAlg(fib));
            System.out.println(recursiveAlg(fib));
            System.out.println("Do you want to run this program again? (y for yes, n for no) ");
            String answer = input.nextLine();
            if (answer.toLowerCase().equals("n"))
            {
                again = false;
            }
            
        }
        input.close();
        
    }
    
}
