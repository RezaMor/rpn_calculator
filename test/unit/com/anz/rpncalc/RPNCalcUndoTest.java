//package com.anz.rpncalc;
//
//import static org.junit.Assert.assertEquals;
//
//import java.math.BigDecimal;
//import java.util.Stack;
//
//import org.junit.Test;
//
//
//public class RPNCalcUndoTest {
//	private RPNCalc calc;
//
//	@Test
//	public void testUndoForPopOperation () {
//		Stack<BigDecimal> undoStack = new Stack<BigDecimal>();
//		calc = new RPNCalc();
//		undoStack.clear();
//		undoStack.push(new BigDecimal("3"));
//		undoStack.push(new BigDecimal("4"));
//		undoStack.push(new BigDecimal("5"));
//		assertEquals(calc.doCalc("3 4 5 7 2 undo"), new BigDecimal("7"));
//		assertEquals(calc.getNumberStack(), undoStack);
//		
//		calc = new RPNCalc();
//		undoStack.clear();
//		undoStack.push(new BigDecimal("3"));
//		undoStack.push(new BigDecimal("4"));
//		assertEquals(calc.doCalc("3 4 5 7 2 undo undo"), new BigDecimal("5"));
//		assertEquals(calc.getNumberStack(), undoStack);
//		
//		calc = new RPNCalc();
//		assertEquals(calc.doCalc("3 4 5 7 2 undo undo undo undo"), new BigDecimal("3"));		
//	}
//	
//	@Test
//    public void testUndoForTwoOperandOperators() {
//    	assertEquals(new RPNCalc().doCalc("3 4 - 5 + undo"), new BigDecimal("-1"));
//		assertEquals(new RPNCalc().doCalc("3 4 5 7 2 - + - + undo"), new BigDecimal("3"));
//		assertEquals(new RPNCalc().doCalc("3 4 5 7 2 - + - + undo undo undo undo"), new BigDecimal("2"));
//		assertEquals(new RPNCalc().doCalc("3 4 5 7 2 - undo + - +"), new BigDecimal("-5"));
//    }
//	
//	@Test
//    public void testUndoForRandomOperators() {
//		assertEquals(new RPNCalc().doCalc("5 4 3 2 undo undo * 5 * undo"), new BigDecimal("20"));
//	//	assertEquals(new RPNCalc().doCalc("1 3 -  undo undo"), new BigDecimal(""));
//	//	assertEquals(new RPNCalc().doCalc("1 3 - 4 / undo undo"), new BigDecimal("1"));
//    }
//}
