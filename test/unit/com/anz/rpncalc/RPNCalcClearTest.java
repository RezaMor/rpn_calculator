package com.anz.rpncalc;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;


public class RPNCalcClearTest {
	private RPNCalc calc;

	@Before
	public void setUp() throws Exception {
		calc = new RPNCalc();
	}

	@Test
    public void testClearOperator() {
		assertEquals(calc.doCalc("1 sqrt clear 2 3 +"), new BigDecimal("5"));
		assertEquals(calc.doCalc("102 98 * 12 + clear 100 sqrt"), new BigDecimal("1E+1"));
		assertEquals(calc.doCalc("10212 9898 * 12 + clear 8 9 *"), new BigDecimal("72"));
    }


}
