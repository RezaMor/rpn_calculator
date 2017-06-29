package com.anz.rpncalc;

import java.math.BigDecimal;
import java.util.Scanner;

import com.anz.rpncalc.calculator.RPNCalculator;
import com.anz.rpncalc.exception.RPNCalcException;

/**
 * Main class to run Reverse Polish Notation (RPN) calculator,
 * the class waits for the input from the user. It evaluates each line and prints the RPN stack to the user
 * after calculation. If an Exception or Error happens during calculation, a message is displayed on the console.
 * 
 * @author moravejir
 *
 */
public class Main {
	
	public static void main(String[] args) {
	
		System.out.println("RPN Calculator Command: (type 'bye' to exit)");
		Scanner command = new Scanner(System.in);
		final RPNCalculator calc = new RPNCalculator();
	    boolean running = true;
	    String commandLine = new String("");

	    while(running){
	    	commandLine = command.nextLine();
	    	switch(commandLine){
		        case "bye":
		            running = !running;
		            break;
		        default:
					try {
						calc.doCalc(commandLine);
					} catch (RPNCalcException rpnException) {
						System.out.println(rpnException.getMessage());
					}
		        	System.out.print("Stack: ");
		        	for (BigDecimal number : calc.getNumbers()) {
	                    System.out.print(calc.formatNumber(number) + " ");
	                }
		        	System.out.println("");
		            break;
		        }
	    }
	    command.close();
	}

}
