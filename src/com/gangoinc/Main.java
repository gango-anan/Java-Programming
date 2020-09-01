package com.gangoinc;

import java.text.NumberFormat;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        final byte MONTHS_IN_YEAR = 12;
        final byte PERCENTAGE = 100;
        int principal;
        float annualInterestRate;
        float monthlyInterestRate;
        byte period;
        int numberOfPayments;

                Scanner getInput = new Scanner(System.in);
        while(true) {
            System.out.print("Principal ($1k - $1M): ");
            principal = getInput.nextInt();
            if (principal >= 1_000 && principal <= 1_000_000)
                break;
            System.out.println("Enter a value between $1,000 and $1,000,000.");
        }
        while(true) {
            System.out.print("Annual Interest Rate(0 - 30): ");
            annualInterestRate = getInput.nextFloat();
            if(annualInterestRate > 0 && annualInterestRate <= 30)
                break;
            System.out.println("Enter a value that is greater than 0 and less or equal to 30.");
        }

        while(true) {
            System.out.print("Period (1 - 30 Years): ");
            period = getInput.nextByte();
            if(period >=1 && period <= 30)
                break;
            System.out.println("Enter a value between 1 and 30.");
        }
        System.out.println();

        monthlyInterestRate = annualInterestRate / PERCENTAGE / MONTHS_IN_YEAR;
        numberOfPayments = period * MONTHS_IN_YEAR;

        double mortgage = principal*(monthlyInterestRate*Math.pow(1+monthlyInterestRate,numberOfPayments))/
                                    (Math.pow(1+monthlyInterestRate,numberOfPayments) - 1);

        String formattedMortgage = NumberFormat.getCurrencyInstance().format(mortgage);
        System.out.println("Mortgage: " + formattedMortgage);

    }
}
