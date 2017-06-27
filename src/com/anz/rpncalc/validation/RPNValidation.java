package com.anz.rpncalc.validation;

public class RPNValidation {

	public static boolean isNumber(String input) {
		try {
			Double.parseDouble(input);
			return true;
		} catch (NumberFormatException nfe) {
			return false;
		}
	}

	public static void throwRPNException(String token, int tokenPos) throws RPNCalcException {
		throw new RPNCalcException(String.format("operator %s (position: %d): is invalid", token, tokenPos));
	}

	public static void insufficientParams(boolean insufficientParams, String token, int tokenPos) throws RPNCalcException {
		if (insufficientParams == true)
		throw new RPNCalcException(String.format("operator %s (position: %d): insufficient parameters", token, tokenPos));
	}

}
