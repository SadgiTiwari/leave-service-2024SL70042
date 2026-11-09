// Maintained by BITS ID: 2024SL70042
package com.bits.leave;

public class LeaveCalculator {

    // Remaining leave balance = entitled days - days taken (never below 0)
    public int calculateLeaveBalance(int entitledDays, int takenDays) {
        if (entitledDays < 0 || takenDays < 0) {
            throw new IllegalArgumentException("Leave days cannot be negative");
        }
        return Math.max(entitledDays - takenDays, 0);
    }

    // Encashment = balance days x (monthly basic salary / 30)
    public double calculateEncashment(int balanceDays, double monthlyBasicSalary) {
        if (balanceDays < 0 || monthlyBasicSalary < 0) {
            throw new IllegalArgumentException("Values cannot be negative");
        }
        double perDay = monthlyBasicSalary / 30.0;
        return balanceDays * perDay;
    }
}
