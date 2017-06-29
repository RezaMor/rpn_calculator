package com.anz.rpncalc.calculator;

import org.junit.Test;

import com.anz.rpncalc.exception.RPNCalcException;


public class RPNCalcUndoTest extends RPNCalcTest {

	@Test
	public void testUndoForPushOperation () throws RPNCalcException {
		compareActualAndExpectedStack("3 4 5 7 2 undo", "3 4 5 7");
		compareActualAndExpectedStack("3 4 5 7 2 undo undo undo undo", "3");		
	}
	
	@Test
    public void testUndoForTwoOperandOperators() throws RPNCalcException {
		compareActualAndExpectedStack("3 4 - 5 + undo", "-1 5");
		compareActualAndExpectedStack("3 4 5 7 2 - + - + undo", "3 -6");
		compareActualAndExpectedStack("3 4 5 7 2 - + - + undo undo undo undo", "3 4 5 7 2");
		compareActualAndExpectedStack("3 4 5 7 2 - undo + - +", "3 0");
    }
	
	@Test
    public void testUndoForRandomOperators() throws RPNCalcException {
		compareActualAndExpectedStack("5 4 3 2 undo undo * 5 * undo", "20 5");
		compareActualAndExpectedStack("1 3 -  undo", "1 3");
		compareActualAndExpectedStack("1 3 - 4 / undo undo", "-2");
		compareActualAndExpectedStack("6 2 3 4 + - * undo undo", "6 2 7");
		compareActualAndExpectedStack("5 3 * 5 8 - + undo undo", "15 5 8");
		compareActualAndExpectedStack("3 8 + 2 8 - 3 8 * undo undo", "11 -6 3");
    }
}
