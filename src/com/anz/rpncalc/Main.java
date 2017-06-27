package com.anz.rpncalc;

import java.math.BigDecimal;
import java.util.Scanner;

import com.anz.rpncalc.validation.RPNCalcException;

public class Main {
	
	public static void main(String[] args) {
	
		System.out.println("RPN Calculator Command: ");
		Scanner command = new Scanner(System.in);
		final RPNCalc calc = new RPNCalc();
	    boolean running = true;
	    String commandLine = new String("");

	    while(running){
	    	commandLine = command.nextLine();
	    	switch(commandLine){
		        case "exit":
		            System.out.println("Exiting the calculator...");
		            running = !running;
		            break;
		        default:
					try {
						calc.doCalc(commandLine);
					} catch (RPNCalcException rpnException) {
						System.out.println(rpnException.getMessage());
					} catch (ArithmeticException exception) {
						System.out.println("Divided by zero exception");
					}
		        	System.out.print("Stack: ");
		        	for (BigDecimal number : calc.getNumberStack()) {
	                    System.out.print(calc.formatNumber(number) + " ");
	                }
		        	System.out.println("");
		            break;
		        }
	    }
	    command.close();
	}

}
