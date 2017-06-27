package com.anz.rpncalc.validation;

public class Validation {
	public static String UNSUPPORTED_OPERATOR_MESSAGE = "operator";

	public static boolean isNumber(String input) {
		try {
			Double.parseDouble(input);
			return true;
		} catch (NumberFormatException numberFormatException) {
			return false;
		}
	}

	public static void validateInput(String token, int tokenPos) throws RPNCalcException {
		if (!Validation.isNumber(token)) {
			throw new RPNCalcException(String.format("operator %s (position: %d): is invalid", token, tokenPos));
		}
	}
	
//	public static void hasMixOfOperandsAndOperators(String token, int tokenPos) throws RPNCalcException {
//		if (token.contains(s))
//		throw new RPNCalcException(String.format("operator %s (position: %d): is invalid", token, tokenPos));
//	}

	public static void insufficientParams(String token, int tokenPos) throws RPNCalcException {
		throw new RPNCalcException(String.format("operator %s (position: %d): insufficient parameters", token, tokenPos));
	}

}
