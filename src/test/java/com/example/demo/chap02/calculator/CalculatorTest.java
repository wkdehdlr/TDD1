package com.example.demo.chap02.calculator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CalculatorTest {

    @Test
    void sum() {
        assertEquals(3, Calculator.sum(1, 2));
        assertEquals(5, Calculator.sum(2, 3));
    }
}
