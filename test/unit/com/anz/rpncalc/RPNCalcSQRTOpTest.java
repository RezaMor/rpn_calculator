package com.anz.rpncalc;

import org.junit.Test;

import com.anz.rpncalc.exception.RPNCalcException;


public class RPNCalcSQRTOpTest extends RPNCalcTest {

	@Test
    public void testSQRTOperator() throws RPNCalcException {
		compareActualAndExpectedStack("1 sqrt", "1");
		compareActualAndExpectedStack("100 sqrt", "1E+1");
		compareActualAndExpectedStack("24 sqrt", "4.8989794855663561");
		compareActualAndExpectedStack("72 sqrt", "8.4852813742385702");
		compareActualAndExpectedStack("1 sqrt", "1");
		compareActualAndExpectedStack("9487123998797987 sqrt", "97401868.559068141729288");
    }
	
	
	@Test(expected = RPNCalcException.class)
    public void testSQRTOperatorNegativeNumbers() throws RPNCalcException {
		compareActualAndExpectedStack("-1 sqrt", "1");
		compareActualAndExpectedStack("-100 sqrt", "1E+1");
    }

}
