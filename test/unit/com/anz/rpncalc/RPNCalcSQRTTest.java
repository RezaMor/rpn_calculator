package com.anz.rpncalc;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;


public class RPNCalcSQRTTest {
	private RPNCalc calc;

	@Before
	public void setUp() throws Exception {
		calc = new RPNCalc();
	}

	@Test
    public void testSQRTOperator() {
		assertEquals(calc.doCalc("1 sqrt"), new BigDecimal("1"));
		assertEquals(calc.doCalc("100 sqrt"), new BigDecimal("1E+1"));
		assertEquals(calc.doCalc("24 sqrt"), new BigDecimal("4.8989794855663561"));
		assertEquals(calc.doCalc("72 sqrt"), new BigDecimal("8.4852813742385702"));
		assertEquals(calc.doCalc("1 sqrt"), new BigDecimal("1"));
		assertEquals(calc.doCalc("9487123998797987 sqrt"), new BigDecimal("97401868.559068141729288"));
    }
	
	
	@Test(expected = IllegalArgumentException.class)
    public void testSQRTOperatorNegativeNumbers() {
		assertEquals(calc.doCalc("-1 sqrt"), new BigDecimal("1"));
		assertEquals(calc.doCalc("-100 sqrt"), new BigDecimal("1E+1"));
    }

}
