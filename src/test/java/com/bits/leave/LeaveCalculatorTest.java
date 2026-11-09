package com.bits.leave;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class LeaveCalculatorTest {

    private final LeaveCalculator calc = new LeaveCalculator();

    @Test
    public void testLeaveBalance() {
        assertEquals(14, calc.calculateLeaveBalance(24, 10));
    }

    @Test
    public void testEncashmentAmount() {
        assertEquals(10000.0, calc.calculateEncashment(10, 30000.0), 0.001);
    }

    @Test
    public void testBalanceNeverNegative() {
        assertEquals(0, calc.calculateLeaveBalance(10, 15));
    }
}
