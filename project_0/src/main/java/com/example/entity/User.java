package com.example.entity;

public class User {

	
	private int accNumber ;
	private double balance ;
	
	
	
	public User(int accNumber, double balance) {
		super();
		this.accNumber = accNumber;
		this.balance = balance;
	}
	
	
	
	public int getAccNumber() {
		return accNumber;
	}
	@Override
	public String toString() {
		return "user [accNumber=" + accNumber + ", balance=" + balance + "]";
	}
	public void setAccNumber(int accNumber) {
		this.accNumber = accNumber;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	
	
}
