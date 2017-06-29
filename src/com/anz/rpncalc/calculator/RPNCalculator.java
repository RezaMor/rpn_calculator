package com.anz.rpncalc.calculator;

import static com.anz.rpncalc.constant.RPNCalcConstants.SPACE;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Stack;

import com.anz.rpncalc.constant.RPNCalcConstants;
import com.anz.rpncalc.exception.RPNCalcException;
import com.anz.rpncalc.operator.RPNOperator;
import com.anz.rpncalc.util.BigDecimalUtils;
import com.anz.rpncalc.validation.RPNValidation;

/**
 * Reverse Polish Notation (RPN) calculator
 * @author moravejir
 *
 */
public class RPNCalculator {
	
	private Stack<BigDecimal> numbers;
	private RPNOperator operator;
	
	public RPNCalculator() {
		numbers = new Stack<BigDecimal>();
		operator = new RPNOperator();
	}

	/**
	 * This function determines which operation (+ - * / sqrt undo, or clear) has been requested by the user
	 * and runs the related operator on the entered operands.
	 * If none of the operations, by default is assumes it is number and pushes the number into a stack.
	 * One an operation found, it pops the operands (numbers of pops depends on the operation type), calculates the result
	 * and pushes the result back to the stack.
	 * 
	 * @param input -- line of operands and operators entered by the user
	 * @throws RPNCalcException
	 */
	public void doCalc(String input) throws RPNCalcException {
    
		/** strings containing whitespace separated lists of numbers and operators. **/
		final String token[] = input.split(" ");

		int tokenPos = 1;
		for (int i = 0; i < token.length; i++) {
			
			/** if token is empty or space increase the position and return **/
			if (token[i].isEmpty() || SPACE.equals(token[i])) {
				tokenPos++;
				continue;
			}
			
			/** Numbers are pushed on to the stack. Operators operate on numbers that are on the stack. **/
			switch(token[i]) {
	            case "+":
	            	RPNValidation.insufficientParams(operator.evalBinary(numbers,
	            			(n1, n2) -> n2.add(n1)), token[i], tokenPos);
	                break;
	            case "-":
	            	RPNValidation.insufficientParams(operator.evalBinary(numbers,
	            			(n1, n2) -> n2.subtract(n1)), token[i], tokenPos);
	                break;
	            case "*":
	            	RPNValidation.insufficientParams(operator.evalBinary(numbers,
	            			(n1, n2) -> n2.multiply(n1)), token[i], tokenPos);
	                break;
	            case "/":
	            	RPNValidation.insufficientParams(operator.evalBinary(numbers,
	            			(n1, n2) -> n2.divide(n1, RPNCalcConstants.NUMBER_PRECISION,
	            					RoundingMode.HALF_UP).stripTrailingZeros()), token[i], tokenPos);
	                break;
	            case "sqrt":
	            	RPNValidation.insufficientParams(operator.evalUnary(numbers,
	            			(n1) -> BigDecimalUtils.sqrt(n1, RPNCalcConstants.NUMBER_PRECISION)),
	            					token[i], tokenPos);
	                break;
	            case "undo":
	            	RPNValidation.insufficientParams(operator.undo(numbers), token[i], tokenPos);
	                break;
	            case "clear":
	            	operator.clear(numbers);
	                break;
	            default:
	            	operator.pushNumber(numbers, token[i], tokenPos);
	            	
				}
			tokenPos = calcTokenPosition(token[i], tokenPos);
		}
        
    }
	
	/** 
	 * Calculates the position of the token to notify the user to the exact position of
	 * invalid operator.
	 */
	protected int calcTokenPosition (String token, int tokenPos) {
		return (token != null) ? tokenPos + token.length() + SPACE.length() : tokenPos;
	}
    
    /**
     * Formats the numbers to be displayed on the console.
     * Numbers should be displayed to 10 decimal places (or less if it causes no loss of precision).
     */
    public BigDecimal formatNumber(BigDecimal number) {
    	number = (number != null) ? number.setScale(RPNCalcConstants.DISPLAY_SCALE, 
    			RoundingMode.HALF_UP).stripTrailingZeros() : number;
    	return number;
    }
    
    /** getter **/
    public Stack<BigDecimal> getNumbers() {
    	return numbers;
    }

}
