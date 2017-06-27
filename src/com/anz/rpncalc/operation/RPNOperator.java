package com.anz.rpncalc.operation;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Stack;
import java.util.function.BiFunction;
import java.util.function.UnaryOperator;

import com.anz.rpncalc.constant.RPNCalcConstants;
import com.anz.rpncalc.exception.RPNCalcException;
import com.anz.rpncalc.validation.RPNValidation;

public class RPNOperator {

	// private Stack<TrackingElement> trackingStack;

	public RPNOperator() {

	}
	
	public void pushNumber(Stack<BigDecimal> numbers, String number, int position) throws RPNCalcException {
		if (!RPNValidation.isNumber(number)) {
			RPNValidation.throwRPNException(number, position);	
		}
        numbers.push(new BigDecimal(number));
        //trackOperation(OperationType.PUSH_POP, number1);
	}

	public boolean evalBinary(Stack<BigDecimal> numbers,
			BiFunction<BigDecimal, BigDecimal, BigDecimal> operator) {
		boolean insufficientParams = false;
		if (numbers.size() < 2) {
			return !insufficientParams;
		}
		BigDecimal number1 = numbers.pop();
		BigDecimal number2 = numbers.pop();
		BigDecimal number3 = operator.apply(number1, number2);
		// trackOperation(OperationType.TWO_OPERAND_OPERATOR, number2, number1,
		// number3);
		numbers.push(number3);
		return insufficientParams;
	}

	public boolean evalUnary(Stack<BigDecimal> numbers, UnaryOperator<BigDecimal> operator)
			throws RPNCalcException {
		boolean insufficientParams = false;
		if (numbers.size() < 1) {
			return !insufficientParams;
		}
		BigDecimal number1 = numbers.pop();
		if (number1.compareTo(BigDecimal.ZERO) < 0) {
			throw new RPNCalcException("x < 0 for sqrt(x) exception");
		}
		BigDecimal number2 = operator.apply(number1);
		number2 = number2.setScale(RPNCalcConstants.NUMBER_PRECISION, RoundingMode.HALF_UP);
		number2 = number2.stripTrailingZeros();
		// trackOperation(OperationType.ONE_OPERAND_OPERATOR, number1, number2);
		numbers.push(number2);
		return insufficientParams;
	}
	
	public void clear(Stack<BigDecimal> numbers) {
		if (numbers != null) {
			numbers.clear();
		}
	}

	// protected void undo() {
	// TrackingElement firstTrackedElement = trackingStack.pop();
	// if (firstTrackedElement != null ) {
	// switch(firstTrackedElement.getOperationType()) {
	// case PUSH_POP:
	// numbers.pop();
	// break;
	// case ONE_OPERAND_OPERATOR:
	// numbers.pop();
	// numbers.push(trackingStack.pop().getNumber());
	// break;
	// case TWO_OPERAND_OPERATOR:
	// numbers.pop();
	// trackingStack.pop();
	// numbers.push(trackingStack.pop().getNumber());
	// break;
	// default:
	// // :TODO throw an exception
	// }
	// }
	//
	// }
	//
	// protected void trackOperation(OperationType operationType, BigDecimal...
	// trackingNumbers) {
	// switch(operationType) {
	// case PUSH_POP:
	// trackingStack.push(new TrackingElement(trackingNumbers[0],
	// operationType));
	// break;
	// case ONE_OPERAND_OPERATOR:
	// trackingStack.push(new TrackingElement(trackingNumbers[0],
	// operationType));
	// trackingStack.push(new TrackingElement(trackingNumbers[1],
	// operationType));
	// break;
	// case TWO_OPERAND_OPERATOR:
	// trackingStack.push(new TrackingElement(trackingNumbers[0],
	// operationType));
	// trackingStack.push(new TrackingElement(trackingNumbers[1],
	// operationType));
	// trackingStack.push(new TrackingElement(trackingNumbers[2],
	// operationType));
	// break;
	// default:
	// // :TODO throw an exception
	// }
	// }

}
