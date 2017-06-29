package com.anz.rpncalc.calculator;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Stack;

import com.anz.rpncalc.calculator.RPNCalculator;
import com.anz.rpncalc.exception.RPNCalcException;


public abstract class RPNCalcTest {
	private RPNCalculator calc;
	private Stack<BigDecimal> expectedStack;
	private Stack<BigDecimal> actualStack;


	protected void compareActualAndExpectedStack(String actual, String expected) throws RPNCalcException {
		
		calc = new RPNCalculator();
		calc.doCalc(actual);
		actualStack = calc.getNumbers();
		
		expectedStack = new Stack<BigDecimal>();
		final String expectedArr[] = expected.split(" ");
		for (int i = 0; i < expectedArr.length; i++) {
			expectedStack.push(new BigDecimal(expectedArr[i]));
		}
		
		assertEquals(actualStack, expectedStack);
	}
}

