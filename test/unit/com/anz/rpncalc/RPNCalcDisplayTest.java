package com.anz.rpncalc;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;


public class RPNCalcDisplayTest {
	private RPNCalc calc;

	@Before
	public void setUp() throws Exception {
		calc = new RPNCalc();
	}
	
	@Test
    public void testDisplayForZero() {
		assertEquals(calc.displayNumber(new BigDecimal("0")), new BigDecimal("0"));
		assertEquals(calc.displayNumber(new BigDecimal("0.000")), new BigDecimal("0"));
		assertEquals(calc.displayNumber(new BigDecimal("00.000")), new BigDecimal("0"));
    }

	@Test
    public void testDisplayNumberWithScaleLessThanOrEqualTo10() {
		assertEquals(calc.displayNumber(new BigDecimal("1.2")), new BigDecimal("1.2"));
		assertEquals(calc.displayNumber(new BigDecimal("1.248")), new BigDecimal("1.248"));
		assertEquals(calc.displayNumber(new BigDecimal("1.24918")), new BigDecimal("1.24918"));
		assertEquals(calc.displayNumber(new BigDecimal("2.3546189")), new BigDecimal("2.3546189"));
		assertEquals(calc.displayNumber(new BigDecimal("123.827161539")), new BigDecimal("123.827161539"));
		assertEquals(calc.displayNumber(new BigDecimal("982.8172635489")), new BigDecimal("982.8172635489"));
    }
	
	@Test
    public void testDisplayNumberWithScaleGreaterThan10() {
		assertEquals(calc.displayNumber(new BigDecimal("82.18273647845")), new BigDecimal("82.1827364785"));
		assertEquals(calc.displayNumber(new BigDecimal("82.182736478423")), new BigDecimal("82.1827364784"));
		assertEquals(calc.displayNumber(new BigDecimal("82.999999999955555")), new BigDecimal("83"));
		assertEquals(calc.displayNumber(new BigDecimal("82.999999999945555")), new BigDecimal("82.9999999999"));
		assertEquals(calc.displayNumber(new BigDecimal("82.9999999999000")), new BigDecimal("82.9999999999"));
		assertEquals(calc.displayNumber(new BigDecimal("82.0000000000555")), new BigDecimal("82.0000000001"));
		assertEquals(calc.displayNumber(new BigDecimal("123.82716153900")), new BigDecimal("123.827161539"));
    }
	
	@Test
    public void testDisplayNumberWithScaleLessThanOrEqualTo10AndTrailingZeros() {
		assertEquals(calc.displayNumber(new BigDecimal("1.200")), new BigDecimal("1.2"));
		assertEquals(calc.displayNumber(new BigDecimal("1.248000")), new BigDecimal("1.248"));
		assertEquals(calc.displayNumber(new BigDecimal("1.24918000")), new BigDecimal("1.24918"));
		assertEquals(calc.displayNumber(new BigDecimal("2.35461890")), new BigDecimal("2.3546189"));
		assertEquals(calc.displayNumber(new BigDecimal("123.8271615390")), new BigDecimal("123.827161539"));
    }
	
	@Test
    public void testDisplayForNegativeNumbers() {
		assertEquals(calc.displayNumber(new BigDecimal("-2.3546189")), new BigDecimal("-2.3546189"));
		assertEquals(calc.displayNumber(new BigDecimal("-123.827161539")), new BigDecimal("-123.827161539"));
		assertEquals(calc.displayNumber(new BigDecimal("-82.9999999999000")), new BigDecimal("-82.9999999999"));
		assertEquals(calc.displayNumber(new BigDecimal("-82.0000000000555")), new BigDecimal("-82.0000000001"));
		assertEquals(calc.displayNumber(new BigDecimal("-123.82716153900")), new BigDecimal("-123.827161539"));
		assertEquals(calc.displayNumber(new BigDecimal("-1.24918000")), new BigDecimal("-1.24918"));
		assertEquals(calc.displayNumber(new BigDecimal("-2.35461890")), new BigDecimal("-2.3546189"));
    }

}
