package com.anz.rpncalc;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Stack;
import java.util.function.BiFunction;
import java.util.function.UnaryOperator;

import com.anz.rpncalc.RPNCalcConstants.OperationType;
import com.anz.rpncalc.validation.RPNCalcException;
import com.anz.rpncalc.validation.Validation;

public class RPNCalc {
	
	private Stack<BigDecimal> numbers;
	private Stack<TrackingElement> trackingStack;
	
	public RPNCalc() {
		numbers = new Stack<BigDecimal>();
		trackingStack = new Stack<TrackingElement>();
	}

	public void doCalc(String input) throws RPNCalcException {
    
		final String token[] = input.split(" ");

		int tokenPos = 1;
		boolean hasEnoughOperands = true;
		out:
		for (int i = 0; i < token.length; i++) {
			if (token[i].isEmpty() || RPNCalcConstants.SPACE.equals(token[i])) {
				tokenPos++;
				continue;
			}
			switch(token[i]) {
	            case "+":
	            	hasEnoughOperands = evalBinaryOperator(numbers, (n1, n2) -> n2.add(n1));
	            	if (hasEnoughOperands == false) {
	            		Validation.insufficientParams(token[i], tokenPos);
	            		break out;
	            	}
	                    break;
	            case "-":
	            	hasEnoughOperands = evalBinaryOperator(numbers, (n1, n2) -> n2.subtract(n1));
	            	if (hasEnoughOperands == false) {
	            		Validation.insufficientParams(token[i], tokenPos);
	            		break out;
	            	}
	                    break;
	            case "*":
	            	hasEnoughOperands = evalBinaryOperator(numbers, (n1, n2) -> n2.multiply(n1));
	            	if (hasEnoughOperands == false) {
	            		Validation.insufficientParams(token[i], tokenPos);
	            		break out;
	            	}
	                    break;
	            case "/":
	            	hasEnoughOperands = evalBinaryOperator(numbers, (n1, n2) -> n2.divide(n1, RPNCalcConstants.NUMBER_PRECISION,
	                    		RoundingMode.HALF_UP).stripTrailingZeros());
	            	if (hasEnoughOperands == false) {
	            		Validation.insufficientParams(token[i], tokenPos);
	            		break out;
	            	}
	                    break;
	            case "sqrt":
	            	hasEnoughOperands = evalUnaryOperator(numbers, (n1) -> BigDecimalUtils.sqrt(n1,
	            			RPNCalcConstants.NUMBER_PRECISION));
	            	if (hasEnoughOperands == false) {
	            		Validation.insufficientParams(token[i], tokenPos);
	            		break out;
	            	}
	                	break;
	            case "clear":
	                	numbers.clear();
	                	break;
	            case "undo":
	                	undo();
	                	break;
	            default:
	            		Validation.validateInput(token[i], tokenPos);
	                	BigDecimal number1 = new BigDecimal(token[i]);
	                    numbers.push(number1);
	                    trackOperation(OperationType.PUSH_POP, number1);
				}
			tokenPos = (token[i] != null) ? tokenPos + token[i].length() 
					+ RPNCalcConstants.SPACE.length() : tokenPos;
		}
        
    }
	
	protected boolean evalBinaryOperator(Stack<BigDecimal> numbers, 
    		BiFunction<BigDecimal, BigDecimal, BigDecimal> operator) {
		if (numbers.size() < 2) {
			return false;
		}
    	BigDecimal number1 = numbers.pop();
    	BigDecimal number2 = numbers.pop();
    	BigDecimal number3 = operator.apply(number1, number2);
    	trackOperation(OperationType.TWO_OPERAND_OPERATOR, number2, number1, number3);
        numbers.push(number3);
        return true;
    }
    
    protected boolean evalUnaryOperator(Stack<BigDecimal> numbers, 
    		UnaryOperator<BigDecimal> operator) throws RPNCalcException {
    	if (numbers.size() < 1) {
			return false;
		}
    	BigDecimal number1 = numbers.pop();
    	if (number1.compareTo(BigDecimal.ZERO) < 0) {
    		throw new RPNCalcException("x < 0 for sqrt(x) exception");
    	}
    	BigDecimal number2 = operator.apply(number1);
    	number2 = number2.setScale(RPNCalcConstants.NUMBER_PRECISION, 
    			RoundingMode.HALF_UP);
    	number2 = number2.stripTrailingZeros();
    	trackOperation(OperationType.ONE_OPERAND_OPERATOR, number1, number2);
        numbers.push(number2);
        return true;
    }
    
	
	protected void undo() {
		TrackingElement firstTrackedElement = trackingStack.pop();
		if (firstTrackedElement != null ) {
			switch(firstTrackedElement.getOperationType()) {
		        case PUSH_POP:
		        	numbers.pop();
		            break;
		        case ONE_OPERAND_OPERATOR:
		        	numbers.pop();
					numbers.push(trackingStack.pop().getNumber());
		            break;
		        case TWO_OPERAND_OPERATOR:
		        	numbers.pop();
					trackingStack.pop();
					numbers.push(trackingStack.pop().getNumber());
		            break;
		        default:
		        	// :TODO throw an exception
			}
		}
		
	}
 
    protected void trackOperation(OperationType operationType, BigDecimal... trackingNumbers) {
    	switch(operationType) {
	        case PUSH_POP:
	        	trackingStack.push(new TrackingElement(trackingNumbers[0], operationType));
	            break;
	        case ONE_OPERAND_OPERATOR:
	        	trackingStack.push(new TrackingElement(trackingNumbers[0], operationType));
	        	trackingStack.push(new TrackingElement(trackingNumbers[1], operationType));
	            break;
	        case TWO_OPERAND_OPERATOR:
	        	trackingStack.push(new TrackingElement(trackingNumbers[0], operationType));
	        	trackingStack.push(new TrackingElement(trackingNumbers[1], operationType));
	        	trackingStack.push(new TrackingElement(trackingNumbers[2], operationType));
	            break;
	        default:
	        	// :TODO throw an exception
    	}
    }
    
    /**
     * Numbers should be displayed to 10 decimal places (or less if it causes no loss of precision).
     * @param number -- input number
     * @return number with 10 decimal places
     */
    public BigDecimal formatNumber(BigDecimal number) {
    	number = (number != null) ? number.setScale(RPNCalcConstants.DISPLAY_SCALE, 
    			RoundingMode.HALF_DOWN).stripTrailingZeros() : number;
    	return number;
    }
    
    public Stack<BigDecimal> getNumberStack() {
    	return numbers;
    }

}
