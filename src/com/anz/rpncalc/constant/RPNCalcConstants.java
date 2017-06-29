package com.anz.rpncalc.constant;

/**
 * Constants
 * @author moravejir
 *
 */
public class RPNCalcConstants {
	
	/** Numbers are displayed to 10 decimal places (or less if it causes no loss of precision),
	 *  therefore, setting Scale threshold for display to 10 */
	public static final int DISPLAY_SCALE = 10;
	
	/** Numbers should be stored on the stack to at least 15 decimal places of precision,
	 *  therefore, setting max precision of numbers in the Stack to 16. */
	public static final int NUMBER_PRECISION = 16;
	
	public static final String SPACE = " ";	
	
	public static enum OperationType {
	    BINARY_OPERATOR,
	    UNARY_OPERATOR,
	    PUSH
	}
	
	public static class MESSAGES {
		public static String NEGATIVE_SQRT_MSG = "x < 0 for sqrt(x) exception";
		public static String DIVIDED_BY_ZERO_MSG = "Divided by zero exception";
	}

}
