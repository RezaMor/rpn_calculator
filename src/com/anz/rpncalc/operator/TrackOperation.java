package com.anz.rpncalc.operator;

import java.math.BigDecimal;

import com.anz.rpncalc.constant.RPNCalcConstants.OperationType;

/**
 * The class stores operands, result of the operation, and the operation type in the
 * for each operation in RPN calculator. This is required by 'undo" later if requested by user.
 * @author moravejir
 *
 */
public class TrackOperation {

	private BigDecimal number;
	private OperationType operationType;
	
	public TrackOperation(BigDecimal number, OperationType operationType) {
		this.setNumber(number);
		this.setOperationType(operationType);
	}

	public BigDecimal getNumber() {
		return number;
	}

	public void setNumber(BigDecimal number) {
		this.number = number;
	}

	public OperationType getOperationType() {
		return operationType;
	}

	public void setOperationType(OperationType operationType) {
		this.operationType = operationType;
	}


}
