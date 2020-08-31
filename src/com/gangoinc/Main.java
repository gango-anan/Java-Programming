package com.gangoinc;

import java.text.NumberFormat;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        final byte MONTHS_IN_YEAR = 12;
        final byte PERCENTAGE = 100;

        Scanner getInput = new Scanner(System.in);
        System.out.print("Principal: ");
        int principal = getInput.nextInt();

        System.out.print("Annual Interest Rate: ");
        float annualInterestRate = getInput.nextFloat();
        float monthlyInterestRate = annualInterestRate/PERCENTAGE/MONTHS_IN_YEAR;

        System.out.print("Period (Years): ");
        byte period = getInput.nextByte();
        int numberOfPayments = period*MONTHS_IN_YEAR;
        System.out.println();

        double mortgage = principal*(monthlyInterestRate*Math.pow(1+monthlyInterestRate,numberOfPayments))/
                                    (Math.pow(1+monthlyInterestRate,numberOfPayments) - 1);

        String formattedMortgage = NumberFormat.getCurrencyInstance().format(mortgage);
        System.out.println("Mortgage: " + formattedMortgage);

    }
}
