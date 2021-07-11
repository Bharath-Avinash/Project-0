package com.example.entity;

import java.sql.Date;

public class Transaction {

	private int id;
	private Date date;
	private double amount;
	private String transcationType;

	public Transaction(int id, Date date, double amount, String transcationType) {
		super();
		this.id = id;
		this.date = date;
		this.amount = amount;
		this.transcationType = transcationType;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getTranscationType() {
		return transcationType;
	}

	public void setTranscationType(String transcationType) {
		this.transcationType = transcationType;
	}

	@Override
	public String toString() {
		return "Transaction [id=" + id + ", date=" + date + ", amount=" + amount + ", transcationType="
				+ transcationType + "]";
	}

}
