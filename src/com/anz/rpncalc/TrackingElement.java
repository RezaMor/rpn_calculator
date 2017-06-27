package com.anz.rpncalc;

import java.math.BigDecimal;

import com.anz.rpncalc.constant.RPNCalcConstants.OperationType;

/**
 * We only save (1) numbers and (2) the operation type applied to the numbers
 * in the tracking stack so that in case on undo we can feed the right numbers into 
 * actual stack containing numbers.
 * @author moravejir
 *
 */
public class TrackingElement {

	private BigDecimal number;
	private OperationType operationType;
	
	public TrackingElement(BigDecimal number, OperationType operationType) {
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
