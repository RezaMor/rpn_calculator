package com.anz.rpncalc.constant;

public class RPNCalcConstants {
	
	/** Numbers are displayed to 10 decimal places (or less if it causes no loss of precision),
	 *  therefore, setting Scale threshold for display to 10 */
	public static final int DISPLAY_SCALE = 10;
	
	/** Numbers should be stored on the stack to at least 15 decimal places of precision,
	 *  therefore, setting max precision of numbers in the Stack to 16. */
	public static final int NUMBER_PRECISION = 16;
	
	public static final String SPACE = " ";	
	
	public static enum OperationType {
	    TWO_OPERAND_OPERATOR,
	    ONE_OPERAND_OPERATOR,
	    PUSH
	}
	
	public static class MESSAGES {
		public static String NEGATIVE_SQRT = "x < 0 for sqrt(x) exception";
		
	}

}
