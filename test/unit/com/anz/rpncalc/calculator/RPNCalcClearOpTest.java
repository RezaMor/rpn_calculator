package com.anz.rpncalc.calculator;

import org.junit.Test;

import com.anz.rpncalc.exception.RPNCalcException;


public class RPNCalcClearOpTest extends RPNCalcTest {

	@Test
    public void testClearOperator() throws RPNCalcException {
		compareActualAndExpectedStack("1 sqrt clear 2 3 +", "5");
		compareActualAndExpectedStack("102 98 * 12 + clear 100 sqrt", "1E+1");
		compareActualAndExpectedStack("10212 9898 * 12 + clear 8 9 *", "72");
    }


}
