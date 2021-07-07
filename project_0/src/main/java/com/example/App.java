package com.example;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import com.example.entity.User;
import com.example.db.*;

public class App {
	
	
		public static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) throws Exception {
		
		Connection con = null;
		System.out.println("Enter the Amount");
		double amount = sc.nextDouble();
		
		
		con = ConnectionFactory.getConnection();
		String sql1 = "select * from Account where number = '2' ";
		Statement stm1 = con.createStatement();
		ResultSet rs1 = stm1.executeQuery(sql1);
		rs1.next();
		User fromAccount = new User(rs1.getInt(1), rs1.getDouble(2));
		System.out.println("fromaccount"+fromAccount.getBalance());
		
		
		
		String sql2 = "select * from Account where number = '1' ";
		Statement stm2 = con.createStatement();
		ResultSet rs2 = stm2.executeQuery(sql2);
		rs2.next();
		User toAccount = new User(rs2.getInt(1), rs2.getDouble(2));
		System.out.println("toaccount"+toAccount.getBalance());
		
		
		Transaction(fromAccount, toAccount,  amount,con);
		
		

	}
	
	
	public static void Transaction (User fromAccount, User toAccount,double amount,Connection con)  {
		try {
		if (fromAccount.getBalance() > amount) {

			fromAccount.setBalance(fromAccount.getBalance() - amount);
			toAccount.setBalance(toAccount.getBalance() + amount);

			// step-5 : update 'from' account

			String sql3 = "update Account set balance=? where number = ?";
			PreparedStatement ps = con.prepareStatement(sql3);
			ps.setDouble(1, fromAccount.getBalance());
			ps.setInt(2, fromAccount.getAccNumber());
			ps.executeUpdate();
			
			
			// step-6 : update 'to' account
			String sql4 = "update Account set balance=? where number = ? ";
			PreparedStatement ps2 = con.prepareStatement(sql4);
			ps2.setDouble(1, toAccount.getBalance());
			ps2.setInt(2, toAccount.getAccNumber());
			ps2.executeUpdate();

			//con.commit();

			System.out.println("success");
			
			updateTransactionDb(fromAccount, toAccount, con, amount);
		} else {
			System.out.println("No Balance");
		}}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		
		
		
	}
	  public static void updateTransactionDb(User fromAccount,User toAccount,Connection con,double amount)  {
		  
		  Date dNow = new Date();
		  SimpleDateFormat ft = new SimpleDateFormat("dd.MM.yyy");
		  String date = ft.format(dNow);
		try {  
		  String query = "INSERT INTO transaction (number,dateAndTime,amount,debitOrCredit) values (?,?,?,?)";
		  PreparedStatement ps2 = con.prepareStatement(query);
		  ps2.setInt(1, fromAccount.getAccNumber());
		  ps2.setString(2, date);
		  ps2.setDouble(3,amount);
		  ps2.setString(4,"Debit" );
		 // ps2.executeUpdate();
		  int fromRowCount = ps2.executeUpdate();
			if (fromRowCount == 1) {
				System.out.println("transaction saved.");
			}
		  
		  
		 String query1 = "INSERT INTO transaction (number,dateAndTime,amount,debitOrCredit) values (?,?,?,?)";
		PreparedStatement  ps3 = con.prepareStatement(query1);
		  ps3.setInt(1, toAccount.getAccNumber());
		  ps3.setString(2, date);
		  ps3.setDouble(3,amount);
		  ps3.setString(4,"credit" );
		 // ps3.executeUpdate();
		  int toRowCount = ps3.executeUpdate();
			if (toRowCount == 1) {
				System.out.println("transaction saved.");
			}
		  
		  
		  System.out.print("transaction updated");
		  
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		  
		  
		  
		  
		  
		  
		
	}
	

}
