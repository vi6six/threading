package com.fibonacci;

import java.math.BigInteger;

public class RecursiveFibCalcImpl implements FibCalc {

    /**
     * Algorithm:
     * F(n+2) = F(n+1) + F(n)
     *
     * Pros: Very simple
     * Cons: Exponential calc time.
     *
     * To get better performance
     *   F(2n) = F(n) * (2*F(n+1) - F(n)).
     *   F(2n+1) = F(n+1)^2 + F(n)^2.
     * should be used.
     */
    @Override
    public BigInteger fib(int n) {
        BigInteger a = BigInteger.ZERO;
        BigInteger b = BigInteger.ONE;
        for (int i = 0; i < n; i++) {
            BigInteger c = a.add(b);
            a = b;
            b = c;
        }
        return a;
    }
}
