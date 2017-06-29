package com.anz.rpncalc.operator;

import static com.anz.rpncalc.constant.RPNCalcConstants.MESSAGES.DIVIDED_BY_ZERO_MSG;
import static com.anz.rpncalc.constant.RPNCalcConstants.MESSAGES.NEGATIVE_SQRT_MSG;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Stack;
import java.util.function.BiFunction;
import java.util.function.UnaryOperator;

import com.anz.rpncalc.constant.RPNCalcConstants;
import com.anz.rpncalc.constant.RPNCalcConstants.OperationType;
import com.anz.rpncalc.exception.RPNCalcException;
import com.anz.rpncalc.validation.RPNValidation;

/**
 * This class contains all the Operators: push + - / * sqrt undo, and clear. The mathematical operators are divided into
 * two groups of Unary Operators and Binary Operator where Unary has one operand and Binary has two operands.
 * 
 * 
 * @author moravejir
 *
 */
public class RPNOperator {

	private Stack<TrackOperation> trackOperationStack;

	public RPNOperator() {
		trackOperationStack = new Stack<TrackOperation>();
	}

	/**
	 * Converts the numberStr into BigDecimal and pushes the BigDecimal into the Stack.
	 * It also pushes the BigDecimal into a tracking stack for "undo" action if later requested by user.
	 * 
	 * @param numbers -- stack of numbers
	 * @param numberStr -- number in String format
	 * @param position -- position of the number in the line
	 * @throws RPNCalcException -- throws an exception in case the number String is not a number
	 */
	public void pushNumber(Stack<BigDecimal> numbers, String numberStr, int position) throws RPNCalcException {
		if (!RPNValidation.isNumber(numberStr)) {
			RPNValidation.throwRPNException(numberStr, position);
		}
		final BigDecimal number = new BigDecimal(numberStr);
		numbers.push(number);
		trackOperation(OperationType.PUSH, number);
	}
	
	/**
	 * Evaluates the supported binary operations (operations that need two operands) by RPN calculator.
	 * It also pushes the operands and the result into a tracking stack for "undo" action if later requested by user.
	 * 
	 * @param numbers -- stack of numbers
	 * @param operator -- operator
	 * @return true if sufficient parameters passed to the function, otherwise return false.
	 * @throws RPNCalcException -- throws an exception in case the number of operands is less than 2.
	 */
	public boolean evalBinary(Stack<BigDecimal> numbers, BiFunction<BigDecimal, BigDecimal, BigDecimal> operator) throws RPNCalcException {
		boolean insufficientParams = false;
		if (numbers.size() < 2) {
			return !insufficientParams;
		}
		BigDecimal number1 = numbers.pop();
		BigDecimal number2 = numbers.pop();
		BigDecimal number3 = null;
		try {
			number3 = operator.apply(number1, number2);
		} catch (ArithmeticException exception) {
			throw new RPNCalcException(DIVIDED_BY_ZERO_MSG);
		}
		
		trackOperation(OperationType.BINARY_OPERATOR, number1, number2, number3);
		numbers.push(number3);
		return insufficientParams;
	}

	/**
	 * Evaluates the supported unary operations (operations that need one operand) by RPN calculator.
	 * It also pushes the operand and the result into a tracking stack for "undo" action if later requested by user.
	 * 
	 * @param numbers -- stack of numbers
	 * @param operator -- operator
	 * @return true if sufficient parameters passed to the function, otherwise return false.
	 * @throws RPNCalcException -- throws an exception in case the number of operands is less than 1.
	 */
	public boolean evalUnary(Stack<BigDecimal> numbers, UnaryOperator<BigDecimal> operator) throws RPNCalcException {
		boolean insufficientParams = false;
		if (numbers.size() < 1) {
			return !insufficientParams;
		}
		BigDecimal number1 = numbers.pop();
		if (number1.compareTo(BigDecimal.ZERO) < 0) {
			throw new RPNCalcException(NEGATIVE_SQRT_MSG);
		}
		BigDecimal number2 = operator.apply(number1);
		number2 = number2.setScale(RPNCalcConstants.NUMBER_PRECISION, RoundingMode.HALF_UP);
		number2 = number2.stripTrailingZeros();
		trackOperation(OperationType.UNARY_OPERATOR, number1, number2);
		numbers.push(number2);
		return insufficientParams;
	}

	/**
	 * The Undo operator undoes the previous operation. 'undo undo' will undo the previous two operations.
	 * 
	 * @param numbers -- stack of numbers
	 * @return  true if sufficient parameters passed to the function, otherwise return false.
	 * @throws RPNCalcException -- throws an exception where stack is empty
	 */
	public boolean undo(Stack<BigDecimal> numbers) throws RPNCalcException {
		boolean insufficientParams = false;
		if (numbers.size() < 1) {
			return !insufficientParams;
		}
		TrackOperation trackOperation = trackOperationStack.pop();
		if (trackOperation != null) {
			switch (trackOperation.getOperationType()) {
			case PUSH:
				numbers.pop();
				break;
			case UNARY_OPERATOR:
				numbers.pop();
				numbers.push(trackOperationStack.pop().getNumber());
				break;
			case BINARY_OPERATOR:
				numbers.pop();
				numbers.push(trackOperationStack.pop().getNumber());
				numbers.push(trackOperationStack.pop().getNumber());
				break;
			}
		}
		return insufficientParams;
	}

	/**
	 * Clears the stack.
	 * @param numbers -- stack of numbers
	 */
	public void clear(Stack<BigDecimal> numbers) {
		if (numbers != null) {
			numbers.clear();
		}
	}

	/**
	 * Pushes the operands, result, and operation type occurred on the operands into a tracking stack
	 * for "undo" action if later requested by user.
	 * 
	 * 1. If operation type is Push, it pushes: the only operand into the tracking stack.
	 * 2. If operation type is Unary, it pushes: the only operand and the result plus the operation type into the tracking stack.
	 * 3. If operation type is Binary, it pushes: two operands and the result plus the operation type into the tracking stack.
	 * 
	 * @param operationType
	 * @param numbers
	 */
	protected void trackOperation(OperationType operationType, BigDecimal... numbers) {
		switch (operationType) {
		case PUSH:
			trackOperationStack.push(new TrackOperation(numbers[0], operationType));
			break;
		case UNARY_OPERATOR:
			trackOperationStack.push(new TrackOperation(numbers[0], operationType));
			trackOperationStack.push(new TrackOperation(numbers[1], operationType));
			break;
		case BINARY_OPERATOR:
			trackOperationStack.push(new TrackOperation(numbers[0], operationType));
			trackOperationStack.push(new TrackOperation(numbers[1], operationType));
			trackOperationStack.push(new TrackOperation(numbers[2], operationType));
			break;
		}
	}

}
