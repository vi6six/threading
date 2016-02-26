package com.fibonacci;

import org.junit.Test;

import java.math.BigInteger;

import static org.junit.Assert.assertEquals;

public class RecursiveFibCalcImplTest {

    @Test
    public void testFibCalc() throws Exception {
        FibCalc fibCalc = new RecursiveFibCalcImpl();
        assertEquals("Fail! Fibonacci number 20 = 6765", new BigInteger("6765"), fibCalc.fib(20));
    }

    @Test
    public void testFibFirstNums() throws Exception {
        FibCalc fibCalc = new RecursiveFibCalcImpl();
        assertEquals("Fail! Fibonacci number 0 = 0", new BigInteger("0"), fibCalc.fib(0));
        assertEquals("Fail! Fibonacci number 1 = 1", new BigInteger("1"), fibCalc.fib(1));
        assertEquals("Fail! Fibonacci number 2 = 1", new BigInteger("1"), fibCalc.fib(2));
        assertEquals("Fail! Fibonacci number 3 = 2", new BigInteger("2"), fibCalc.fib(3));
    }
}