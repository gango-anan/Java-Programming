package com.gangoinc;

import java.text.NumberFormat;
import java.util.Scanner;

public class Main {
    final static byte MONTHS_IN_YEAR = 12;
    final static byte PERCENTAGE = 100;
    public static void main(String[] args) {

        int principal = (int)getNumber("Principal ($1k - $1M)", 1_000, 1_000_000);
        float annualInterestRate = (float)getNumber("Annual Interest Rate(0 - 30)", 1, 30);
        byte period = (byte)getNumber("Period (1 - 30 Years)", 1, 30);

        printMonthlyPayments(principal, annualInterestRate, period);

        printBalance(principal, annualInterestRate, period);

    }

    public static void printBalance(int principal, float annualInterestRate, byte period) {
        System.out.println("PAYMENT SCHEDULE");
        System.out.println("-----------------");
        for (short month = 1; month <= period *MONTHS_IN_YEAR ; month++) {
            double balance = calculateBalance(principal, annualInterestRate, period,month);
            System.out.println(NumberFormat.getCurrencyInstance().format(balance));
        }
    }

    public static void printMonthlyPayments(int principal, float annualInterestRate, byte period) {
        double mortgage = calculateMortgage(principal, annualInterestRate, period);
        String formattedMortgage = NumberFormat.getCurrencyInstance().format(mortgage);
        System.out.println("");
        System.out.println("MORTGAGE");
        System.out.println("-----------");
        System.out.println("Monthly Payments: " + formattedMortgage);
        System.out.println("");
    }

    public static double getNumber(String prompt, double min, double max){
        Scanner getInput = new Scanner(System.in);
        double value;
        while(true) {
            System.out.print(prompt + ": ");
            value = getInput.nextDouble();
            if (value >= min && value <= max)
                break;
            System.out.println("Enter a value between " + min + " and " + max);
        }
        return value;
    }

    public static double calculateMortgage(int principal, float annualInterestRate, byte period){
        float monthlyInterestRate = annualInterestRate / PERCENTAGE / MONTHS_IN_YEAR;
        short numberOfPayments = (short)(period * MONTHS_IN_YEAR);

        double mortgage = principal*(monthlyInterestRate*Math.pow(1+monthlyInterestRate,numberOfPayments))/
                                    (Math.pow(1+monthlyInterestRate,numberOfPayments) - 1);

        return mortgage;
    }

    public static double calculateBalance(int principal,
                                          float annualInterestRate,
                                          byte period,
                                          short numberOfPaymentsMade){

        float monthlyInterestRate = annualInterestRate / PERCENTAGE / MONTHS_IN_YEAR;
        short numberOfPayments = (short)(period * MONTHS_IN_YEAR);

        double balance = principal *(Math.pow(1+monthlyInterestRate,numberOfPayments)-(Math.pow(1+monthlyInterestRate,numberOfPaymentsMade)))/
                                    (Math.pow(1+monthlyInterestRate,numberOfPayments) - 1);

        return balance;
    }
}
