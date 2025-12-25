package com.loanapp.emi.util;

import java.math.BigDecimal;

public class PenaltyCalculator {

    private static final BigDecimal DAILY_RATE =
            BigDecimal.valueOf(0.0005); // 0.05%

    private static final BigDecimal MAX_PENALTY =
            BigDecimal.valueOf(2000); // cap

    public static BigDecimal calculate(
            BigDecimal emiAmount,
            long daysLate) {

        BigDecimal penalty = emiAmount
                .multiply(DAILY_RATE)
                .multiply(BigDecimal.valueOf(daysLate));

        return penalty.min(MAX_PENALTY);
    }
}