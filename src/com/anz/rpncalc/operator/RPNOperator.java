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

public class RPNOperator {

	private Stack<TrackOperation> trackOperationStack;

	public RPNOperator() {
		trackOperationStack = new Stack<TrackOperation>();
	}

	public void pushNumber(Stack<BigDecimal> numbers, String _number, int position) throws RPNCalcException {
		if (!RPNValidation.isNumber(_number)) {
			RPNValidation.throwRPNException(_number, position);
		}
		final BigDecimal number = new BigDecimal(_number);
		numbers.push(number);
		trackOperation(OperationType.PUSH, number);
	}

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
		
		trackOperation(OperationType.TWO_OPERAND_OPERATOR, number1, number2, number3);
		numbers.push(number3);
		return insufficientParams;
	}

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
		trackOperation(OperationType.ONE_OPERAND_OPERATOR, number1, number2);
		numbers.push(number2);
		return insufficientParams;
	}

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
			case ONE_OPERAND_OPERATOR:
				numbers.pop();
				numbers.push(trackOperationStack.pop().getNumber());
				break;
			case TWO_OPERAND_OPERATOR:
				numbers.pop();
				numbers.push(trackOperationStack.pop().getNumber());
				numbers.push(trackOperationStack.pop().getNumber());
				break;
			}
		}
		return insufficientParams;
	}

	public void clear(Stack<BigDecimal> numbers) {
		if (numbers != null) {
			numbers.clear();
		}
	}

	protected void trackOperation(OperationType operationType, BigDecimal... numbers) {
		switch (operationType) {
		case PUSH:
			trackOperationStack.push(new TrackOperation(numbers[0], operationType));
			break;
		case ONE_OPERAND_OPERATOR:
			trackOperationStack.push(new TrackOperation(numbers[0], operationType));
			trackOperationStack.push(new TrackOperation(numbers[1], operationType));
			break;
		case TWO_OPERAND_OPERATOR:
			trackOperationStack.push(new TrackOperation(numbers[0], operationType));
			trackOperationStack.push(new TrackOperation(numbers[1], operationType));
			trackOperationStack.push(new TrackOperation(numbers[2], operationType));
			break;
		}
	}

}
