package com.anz.rpncalc;

public class RPNCalcConstants {
	
	/** Numbers are displayed to 10 decimal places (or less if it causes no loss of precision),
	 *  therefore, setting Scale threshold for display to 10 */
	public static int DISPLAY_SCALE = 10;
	
	/** Numbers should be stored on the stack to at least 15 decimal places of precision,
	 *  therefore, setting max precision of numbers in the Stack to 16. */
	public static int NUMBER_PRECISION = 16;
	
	public static String SPACE = " ";
	
	
	
	public static enum OperationType {
	    TWO_OPERAND_OPERATOR,
	    ONE_OPERAND_OPERATOR,
	    PUSH_POP
	}

}
