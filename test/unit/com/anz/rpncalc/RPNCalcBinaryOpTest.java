package com.anz.rpncalc;

import org.junit.Test;

import com.anz.rpncalc.exception.RPNCalcException;


public class RPNCalcBinaryOpTest extends RPNCalcTest {
	
	@Test
    public void testOneBinaryOperatorTwoOperands() throws RPNCalcException {
		compareActualAndExpectedStack("1 2 +", "3");
		compareActualAndExpectedStack("11 24 -", "-13");
		compareActualAndExpectedStack("091 23 -", "68");
		compareActualAndExpectedStack("19 22 *", "418");
		compareActualAndExpectedStack("123 765 *", "94095");
		compareActualAndExpectedStack("10 92 /", "0.108695652173913");
		compareActualAndExpectedStack("44 21 /", "2.0952380952380952");
		compareActualAndExpectedStack("10 1 /", "1E1");
		compareActualAndExpectedStack("419287498 92874.29809 /", "4514.5697638940831752");
    }
	
	@Test
    public void testOneBinaryOperatorNegativeNumbers() throws RPNCalcException {
		compareActualAndExpectedStack("-1 2 +", "1");
		compareActualAndExpectedStack("11 -29 +", "-18");
		compareActualAndExpectedStack("19 -22 *", "-418");
		compareActualAndExpectedStack("-123 765 *", "-94095");
		compareActualAndExpectedStack("10 -92 /", "-0.108695652173913");
    }
	
	@Test
    public void testTwoBinaryOperators() throws RPNCalcException {
		compareActualAndExpectedStack("29 13 * 23 /", "16.391304347826087");
		compareActualAndExpectedStack("12 54 - 2 /", "-21");
		compareActualAndExpectedStack("74 13 + 12 -", "75");
		compareActualAndExpectedStack("4123423.23423 4214213.9878787877878 * 12.28987987 /", "1413926584754.8481494784621468");
    }
	
	@Test
    public void testTwoBinaryOperatorsInARow() throws RPNCalcException {
		compareActualAndExpectedStack("1 2 32 + *", "34");
		compareActualAndExpectedStack("11 16 29 + /", "0.2444444444444444");
		compareActualAndExpectedStack("11 24 2 / -", "-1");
		compareActualAndExpectedStack("19 12 65 * -", "-761");
    }
	
	@Test
    public void testTwoBinaryOperatorsInARowNegativeNumbers() throws RPNCalcException {
		compareActualAndExpectedStack("1 2 -32 + *", "-30");
		compareActualAndExpectedStack("-11 16 29 + /", "-0.2444444444444444");
		compareActualAndExpectedStack("11 -24 2 / -", "23");
		compareActualAndExpectedStack("19 12 -65 * -", "799");
    }
	
	@Test
    public void testThreeBinaryOperators() throws RPNCalcException {
		compareActualAndExpectedStack("1 2 3 + * 4 +", "9");
		compareActualAndExpectedStack("1 2 + 3 * 4 +", "13");
		compareActualAndExpectedStack("56 2 + 10 4 * /", "1.45");
		compareActualAndExpectedStack("56 2 + 10 * 4 /", "145");
    }
	
	@Test
    public void testThreeBinaryOperatorsInARow() throws RPNCalcException {
		compareActualAndExpectedStack("1 2 3 4 + * +", "15");
		compareActualAndExpectedStack("56 2 10 4 + * /", "2");
		compareActualAndExpectedStack("100 19 41 10 - + /", "2");
    }
	
	@Test
    public void testFourOperatorsOrMore() throws RPNCalcException {
		compareActualAndExpectedStack("498712398473.49 98723.298 * 10.12341234123 * 124987.41243 / 124 +",
				"3987773381229.4562151380788053");
    }

}

