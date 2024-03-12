/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.em.motorph;

/**
 *
 * @author EM
 */

import java.text.DecimalFormat;
import java.util.Scanner;

public class MotorPh {
    public static void main(String[] args) {
        // Display employee information
        System.out.println("Employee ID: 1");
        System.out.println("Full Name: Garcia\tManuel III");
        System.out.println("Birthday: 10/11/1983");
        System.out.println("Employment Status: Regular");
        System.out.println("------------------------------");
        System.out.println("SSS: 44-4506057-3");
        System.out.println("Philhealth #: 820126853951");
        System.out.println("TIN #: 442-605-657-000");
        System.out.println("Pag-ibig #: 691295330870");

        // Take input for total hours worked
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter total hours worked for the week: ");
        double totalHoursWorked = scanner.nextDouble();

        // Constants
        double hourlyRate = 535.71;
        double riceSubsidy = 1500;
        double phoneAllowance = 2000;
        double clothingAllowance = 1000;

        // Compute earnings
        double grossWeeklySalary = totalHoursWorked * hourlyRate;
        double netWeeklySalary = grossWeeklySalary; // For now, assuming no deductions

        // Compute allowances
        double totalAllowances = riceSubsidy + phoneAllowance + clothingAllowance;

        // Compute deductions
        double sssDeduction = computeSSSDeduction(grossWeeklySalary);
        double philhealthDeduction = computePhilhealthDeduction(grossWeeklySalary);
        double pagibigDeduction = computePagibigDeduction(grossWeeklySalary);
        double withholdingTax = computeWithholdingTax(grossWeeklySalary);
        double totalDeductions = sssDeduction + philhealthDeduction + pagibigDeduction + withholdingTax;

        // Summary
        double grossIncome = grossWeeklySalary;
        double totalBenefits = totalAllowances;
        double takeHomePay = netWeeklySalary - totalDeductions;

        // Format to display amounts with only two decimal places
        DecimalFormat df = new DecimalFormat("#.##");

        // Output
        System.out.println("\nEARNINGS");
        System.out.println("Total Hours Worked: " + totalHoursWorked);
        System.out.println("Hourly Rate: " + df.format(hourlyRate));
        System.out.println("Gross Weekly Salary: " + df.format(grossWeeklySalary));
        System.out.println("Net Weekly Salary: " + df.format(netWeeklySalary));

        System.out.println("\nALLOWANCES");
        System.out.println("Rice Subsidy: " + df.format(riceSubsidy));
        System.out.println("Phone Allowance: " + df.format(phoneAllowance));
        System.out.println("Clothing Allowance: " + df.format(clothingAllowance));
        System.out.println("TOTAL: " + df.format(totalAllowances));

        System.out.println("\nDEDUCTIONS");
        System.out.println("SSS: " + df.format(sssDeduction));
        System.out.println("Philhealth: " + df.format(philhealthDeduction));
        System.out.println("Pag-ibig: " + df.format(pagibigDeduction));
        System.out.println("Withholding Tax: " + df.format(withholdingTax));
        System.out.println("TOTAL DEDUCTIONS: " + df.format(totalDeductions));

        System.out.println("\nSUMMARY");
        System.out.println("Gross Income: " + df.format(grossIncome));
        System.out.println("Total Benefits: " + df.format(totalBenefits));
        System.out.println("Total Deductions: " + df.format(totalDeductions));
        System.out.println("TAKE HOME PAY: " + df.format(takeHomePay));
    }

    // Function to compute SSS Deduction
    private static double computeSSSDeduction(double grossIncome) {
        double[] sssRates = {135.00, 157.5, 180, 202.5, 225, 247.5, 270, 292.5, 315, 337.5, 360, 382.5, 405, 427.5,
                450, 472.5, 495, 517.5, 540, 562.5, 585, 607.5, 630, 652.5, 675, 697.5, 720, 742.5, 765, 787.5,
                810, 832.5, 855, 877.5, 900, 922.5, 945, 967.5, 990, 1012.5, 1035, 1057.5, 1080, 1102.5, 1125.00};

        double[] sssRanges = {3250, 3750, 4250, 4750, 5250, 5750, 6250, 6750, 7250, 7750, 8250, 8750, 9250, 9750,
                10250, 10750, 11250, 11750, 12250, 12750, 13250, 13750, 14250, 14750, 15250, 15750, 16250, 16750,
                17250, 17750, 18250, 18750, 19250, 19750, 20250, 20750, 21250, 21750, 22250, 22750, 23250, 23750,
                24250, 24750, 25250, 25750, 26250, 26750, 27250};

        for (int i = 0; i < sssRanges.length; i++) {
            if (grossIncome <= sssRanges[i]) {
                return sssRates[i];
            }
        }
        return sssRates[sssRates.length - 1]; // If income exceeds all ranges
    }

    // Function to compute Philhealth Deduction
    private static double computePhilhealthDeduction(double grossIncome) {
        if (grossIncome <= 10000) {
            return 300;
        } else if (grossIncome <= 59999.99) {
            return 300 + ((grossIncome - 10000) * 0.03);
        } else {
            return 1800;
        }
    }

    // Function to compute Pag-ibig Deduction
    private static double computePagibigDeduction(double grossIncome) {
        if (grossIncome >= 1000 && grossIncome <= 1500) {
            return grossIncome * 0.01;
        } else {
            return grossIncome * 0.02;
        }
    }

    // Function to compute Withholding Tax
    private static double computeWithholdingTax(double grossIncome) {
        if (grossIncome <= 20832) {
            return 0;
        } else if (grossIncome <= 33332) {
            return (grossIncome - 20833) * 0.20;
        } else if (grossIncome <= 66667) {
            return 2500 + (grossIncome - 33333) * 0.25;
        } else if (grossIncome <= 166667) {
            return 10833.33 + (grossIncome - 66667) * 0.30;
        } else if (grossIncome <= 666667) {
            return 40833.33 + (grossIncome - 166667) * 0.32;
        } else {
            return 200833.33 + (grossIncome - 666667) * 0.35;
        }
    }
}
