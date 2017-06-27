package com.anz.rpncalc;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;


public class RPNCalcTest {
	private RPNCalc calc;

	@Before
	public void setUp() throws Exception {
		calc = new RPNCalc();
	}

	@Test
    public void testOneOperator() {
		assertEquals(calc.doCalc("1 2 +"), new BigDecimal("3"));
		assertEquals(calc.doCalc("11 29 +"), new BigDecimal("40"));
		assertEquals(calc.doCalc("11 24 -"), new BigDecimal("-13"));
		assertEquals(calc.doCalc("091 23 -"), new BigDecimal("68"));
		assertEquals(calc.doCalc("19 22 *"), new BigDecimal("418"));
		assertEquals(calc.doCalc("123 765 *"), new BigDecimal("94095"));
		assertEquals(calc.doCalc("10 92 /"), new BigDecimal("0.108695652173913"));
		assertEquals(calc.doCalc("44 21 /"), new BigDecimal("2.0952380952380952"));
		assertEquals(calc.doCalc("10 1 /"), new BigDecimal("1E1"));
		assertEquals(calc.doCalc("419287498 92874.29809 /"), new BigDecimal("4514.5697638940831752"));
    }
	
	@Test
    public void testOneOperatorNegativeNumbers() {
		assertEquals(calc.doCalc("-1 2 +"), new BigDecimal("1"));
		assertEquals(calc.doCalc("11 -29 +"), new BigDecimal("-18"));
		assertEquals(calc.doCalc("19 -22 *"), new BigDecimal("-418"));
		assertEquals(calc.doCalc("-123 765 *"), new BigDecimal("-94095"));
		assertEquals(calc.doCalc("10 -92 /"), new BigDecimal("-0.108695652173913"));
    }
	
	@Test
    public void testTwoOperators() {
		assertEquals(calc.doCalc("29 13 * 23 /"), new BigDecimal("16.391304347826087"));
		assertEquals(calc.doCalc("12 54 - 2 /"), new BigDecimal("-21"));
		assertEquals(calc.doCalc("74 13 + 12 -"), new BigDecimal("75"));
		assertEquals(calc.doCalc("4123423.23423 4214213.9878787877878 * 12.28987987 /"),
				new BigDecimal("1413926584754.8481494784621468"));
    }
	
	@Test
    public void testTwoOperatorsInARow() {
		assertEquals(calc.doCalc("1 2 32 + *"), new BigDecimal("34"));
		assertEquals(calc.doCalc("11 16 29 + /"), new BigDecimal("0.2444444444444444"));
		assertEquals(calc.doCalc("11 24 2 / -"), new BigDecimal("-1"));
		assertEquals(calc.doCalc("19 12 65 * -"), new BigDecimal("-761"));
    }
	
	@Test
    public void testTwoOperatorsInARowNegativeNumbers() {
		assertEquals(calc.doCalc("1 2 -32 + *"), new BigDecimal("-30"));
		assertEquals(calc.doCalc("-11 16 29 + /"), new BigDecimal("-0.2444444444444444"));
		assertEquals(calc.doCalc("11 -24 2 / -"), new BigDecimal("23"));
		assertEquals(calc.doCalc("19 12 -65 * -"), new BigDecimal("799"));
    }
	
	@Test
    public void testThreeOperators() {
		assertEquals(calc.doCalc("1 2 3 + * 4 +"), new BigDecimal("9"));
		assertEquals(calc.doCalc("1 2 + 3 * 4 +"), new BigDecimal("13"));
		assertEquals(calc.doCalc("56 2 + 10 4 * /"), new BigDecimal("1.45"));
		assertEquals(calc.doCalc("56 2 + 10 * 4 /"), new BigDecimal("145"));
    }
	
	@Test
    public void testThreeOperatorsInARow() {
		assertEquals(calc.doCalc("1 2 3 4 + * +"), new BigDecimal("15"));
		assertEquals(calc.doCalc("56 2 10 4 + * /"), new BigDecimal("2"));
		assertEquals(calc.doCalc("100 19 41 10 - + /"), new BigDecimal("2"));
    }
	
	@Test
    public void testFourOperatorsOrMore() {
		assertEquals(calc.doCalc("498712398473.49 98723.298 * 10.12341234123 * 124987.41243 / 124 +")
				, new BigDecimal("3987773381229.4562151380788053"));
    }

}
