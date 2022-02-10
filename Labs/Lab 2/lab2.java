//Program that takes monthly payment and balance of account and prints out interest,
//principal payments, and outstanding balance.
// Gabe Seidl & Eric Pan
// February 09, 2022

import java.util.Scanner;

public class lab2{
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        System.out.print("Enter this month's payment: ");
        double monthly = kb.nextDouble();
        System.out.print("Enter the outstanding balance of the account: ");
        double balance = kb.nextDouble();
        kb.close();
        double annual = 0.0625;
        double m_rate = annual / 12; // gets monthly interest rate
        double interest = balance * m_rate;
        double principal = monthly - interest;
        double outstanding = balance - principal;
        System.out.println("Here is a breakdown of the payment: ");
        System.out.println("Interest paid:");
        System.out.println("$" + Math.round(interest * 100.0) / 100.0); // rounds to two decimal
        System.out.println("Amount applied to principal: ");
        System.out.println("$" + Math.round(principal * 100.0) / 100.0);
        System.out.println("After the payment, the account balance is: "); 
        System.out.println("$" + Math.round(outstanding * 100.0) / 100.0);       
    }
}