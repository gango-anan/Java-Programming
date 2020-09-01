package com.gangoinc;

import java.text.NumberFormat;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        int principal = (int)getNumber("Principal ($1k - $1M): ", 1_000, 1_000_000);
        float annualInterestRate = (float)getNumber("Annual Interest Rate(0 - 30): ", 1, 30);
        byte period = (byte)getNumber("Period (1 - 30 Years): ", 1, 30);

        double mortgage = calculateMortgage(principal, annualInterestRate, period);
        String formattedMortgage = NumberFormat.getCurrencyInstance().format(mortgage);
        System.out.println("Mortgage: " + formattedMortgage);

    }

    public static double getNumber(String prompt, double min, double max){
        Scanner getInput = new Scanner(System.in);
        double value;
        while(true) {
            System.out.print(prompt);
            value = getInput.nextDouble();
            if (value >= min && value <= max)
                break;
            System.out.println("Enter a value between " + min + " and " + max);
        }
        return value;
    }

    public static double calculateMortgage(int principal, float annualInterestRate, byte period){
        final byte MONTHS_IN_YEAR = 12;
        final byte PERCENTAGE = 100;

        float monthlyInterestRate = annualInterestRate / PERCENTAGE / MONTHS_IN_YEAR;
        short numberOfPayments = (short)(period * MONTHS_IN_YEAR);

        double mortgage = principal*(monthlyInterestRate*Math.pow(1+monthlyInterestRate,numberOfPayments))/
                                    (Math.pow(1+monthlyInterestRate,numberOfPayments) - 1);

        return mortgage;
    }
}
