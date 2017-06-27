package com.anz.rpncalc;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;

import com.anz.rpncalc.calculator.RPNCalculator;


public class RPNCalcDisplayFormatTest {
	private RPNCalculator calc = new RPNCalculator();
	
	@Test
    public void testDisplayForZero() {
		assertEquals(calc.formatNumber(new BigDecimal("0")), new BigDecimal("0"));
		assertEquals(calc.formatNumber(new BigDecimal("0.000")), new BigDecimal("0"));
		assertEquals(calc.formatNumber(new BigDecimal("00.000")), new BigDecimal("0"));
    }

	@Test
    public void testformatNumberWithScaleLessThanOrEqualTo10() {
		assertEquals(calc.formatNumber(new BigDecimal("1.2")), new BigDecimal("1.2"));
		assertEquals(calc.formatNumber(new BigDecimal("1.248")), new BigDecimal("1.248"));
		assertEquals(calc.formatNumber(new BigDecimal("1.24918")), new BigDecimal("1.24918"));
		assertEquals(calc.formatNumber(new BigDecimal("2.3546189")), new BigDecimal("2.3546189"));
		assertEquals(calc.formatNumber(new BigDecimal("123.827161539")), new BigDecimal("123.827161539"));
		assertEquals(calc.formatNumber(new BigDecimal("982.8172635489")), new BigDecimal("982.8172635489"));
    }
	
	@Test
    public void testformatNumberWithScaleGreaterThan10() {
		assertEquals(calc.formatNumber(new BigDecimal("82.18273647845")), new BigDecimal("82.1827364785"));
		assertEquals(calc.formatNumber(new BigDecimal("82.182736478423")), new BigDecimal("82.1827364784"));
		assertEquals(calc.formatNumber(new BigDecimal("82.999999999955555")), new BigDecimal("83"));
		assertEquals(calc.formatNumber(new BigDecimal("82.999999999945555")), new BigDecimal("82.9999999999"));
		assertEquals(calc.formatNumber(new BigDecimal("82.9999999999000")), new BigDecimal("82.9999999999"));
		assertEquals(calc.formatNumber(new BigDecimal("82.0000000000555")), new BigDecimal("82.0000000001"));
		assertEquals(calc.formatNumber(new BigDecimal("123.82716153900")), new BigDecimal("123.827161539"));
    }
	
	@Test
    public void testformatNumberWithScaleLessThanOrEqualTo10AndTrailingZeros() {
		assertEquals(calc.formatNumber(new BigDecimal("1.200")), new BigDecimal("1.2"));
		assertEquals(calc.formatNumber(new BigDecimal("1.248000")), new BigDecimal("1.248"));
		assertEquals(calc.formatNumber(new BigDecimal("1.24918000")), new BigDecimal("1.24918"));
		assertEquals(calc.formatNumber(new BigDecimal("2.35461890")), new BigDecimal("2.3546189"));
		assertEquals(calc.formatNumber(new BigDecimal("123.8271615390")), new BigDecimal("123.827161539"));
    }
	
	@Test
    public void testDisplayForNegativeNumbers() {
		assertEquals(calc.formatNumber(new BigDecimal("-2.3546189")), new BigDecimal("-2.3546189"));
		assertEquals(calc.formatNumber(new BigDecimal("-123.827161539")), new BigDecimal("-123.827161539"));
		assertEquals(calc.formatNumber(new BigDecimal("-82.9999999999000")), new BigDecimal("-82.9999999999"));
		assertEquals(calc.formatNumber(new BigDecimal("-82.0000000000555")), new BigDecimal("-82.0000000001"));
		assertEquals(calc.formatNumber(new BigDecimal("-123.82716153900")), new BigDecimal("-123.827161539"));
		assertEquals(calc.formatNumber(new BigDecimal("-1.24918000")), new BigDecimal("-1.24918"));
		assertEquals(calc.formatNumber(new BigDecimal("-2.35461890")), new BigDecimal("-2.3546189"));
    }

}
