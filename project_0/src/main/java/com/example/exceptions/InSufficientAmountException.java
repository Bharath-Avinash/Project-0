package com.example.exceptions;

public class InSufficientAmountException extends RuntimeException {
	
	public InSufficientAmountException(String msg) {
		super(msg);
	}

}
