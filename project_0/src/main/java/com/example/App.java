package com.example;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.example.entity.User;
import com.example.exceptions.AccountNotFoundException;
import com.example.exceptions.InSufficientAmountException;
import com.example.repository.Features_Imple;
//import com.company.exception.InsufficientAmountException;
import com.example.db.*;

public class App {

	public static Scanner sc = new Scanner(System.in);
	static Features_Imple feature = new Features_Imple();
	 public static Logger logger = Logger.getLogger("app1");

	public static void main(String[] args) throws Exception {
		
		int option;
	
			
		
		System.out.println(
				"\n \n press 0 for transaction \n press 1 for top Transaction\n  press 2 for current Month Transaction \n press 3 for dateRange Transaction \n press 4 for last three month transaction \n press 5 to Stop the Process");
		 option = sc.nextInt();
		
		switch (option) {
		case 0:
			loadBankAccountsAndTransaction();
			//j='b';

			break;
		case 1:
			feature.topTransaction();
			//j='b';
			break;
		case 2:
			feature.currentMonthTransaction();
			//j='b';
			break;
			
		case 3:
			feature.dateRange();
			//j='b';
			break;
		case 4:
			feature.lastThreeMonthTransaction();
		//	j='b';
			break;

		}
		
		if(option== 5) {
			//temp = false;
			System.out.println("Process Completed");
		
			
		}}
		

		// feature.dateRange();

		// feature.dateRange( 30.06.2021,07.07.2021 );
		// feature.currentMonthTransaction();
		// feature.lastThreeMonthTransaction();
	

	public static void transaction(User fromAccount, User toAccount, Connection con) {

		System.out.println("Enter the Amount");
		double amount = sc.nextDouble();
		
		try {
			con.setAutoCommit(false);

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

				// con.commit();

				System.out.println("success");
				 logger.info("transaction Succesful");

				updateTransactionDb(fromAccount, toAccount, con, amount);
				con.setAutoCommit(true);

			} else {
				con.rollback();
				throw new InSufficientAmountException("Insufficient Amount to transfer\nyou have only "
						+ fromAccount.getBalance() + " in your account");
				// System.out.println(" Balance Insufficient");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	public static void updateTransactionDb(User fromAccount, User toAccount, Connection con, double amount) {

		try {
			String query = "INSERT INTO transaction (number,date,amount,debitOrCredit) values (?,?,?,?)";
			PreparedStatement ps2 = con.prepareStatement(query);
			ps2.setInt(1, fromAccount.getAccNumber());
			// java.sql.Date sqlDate = new java.sql.Date(new java.time.LocalDate.now());
			LocalDate fromDate = LocalDate.now();
			ps2.setDate(2, java.sql.Date.valueOf(fromDate));
			ps2.setDouble(3, amount);
			ps2.setString(4, "Debit");
			// ps2.executeUpdate();
			int fromRowCount = ps2.executeUpdate();
			if (fromRowCount == 1) {
				System.out.println("transaction saved.");
			}

			String query1 = "INSERT INTO transaction (number,date,amount,debitOrCredit) values (?,?,?,?)";
			PreparedStatement ps3 = con.prepareStatement(query1);
			ps3.setInt(1, toAccount.getAccNumber());
			// java.sql.Date sqlDat = new java.sql.Date(new java.util.Date().getTime());
			ps3.setDate(2, java.sql.Date.valueOf(fromDate));
			ps3.setDouble(3, amount);
			ps3.setString(4, "credit");
			// ps3.executeUpdate();
			int toRowCount = ps3.executeUpdate();
			if (toRowCount == 1) {
				System.out.println("transaction saved.");
			}

			//System.out.print("transaction updated");
			 logger.info("Transaction updated succesfully");

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	public static void loadBankAccountsAndTransaction() {

		Connection con = null;

		try {
			con = ConnectionFactory.getConnection();
			String sql1 = "select * from Account where number = ? ";

			PreparedStatement stm1 = con.prepareStatement(sql1);
			System.out.println("Enter fromAccount number");
			stm1.setInt(1, sc.nextInt());
			ResultSet rs1 = stm1.executeQuery();

			rs1.next();
			User fromAccount = new User(rs1.getInt(1), rs1.getDouble(2));
			System.out.println("fromaccount" + fromAccount.getBalance());

			String sql2 = "select * from Account where number = ? ";
			PreparedStatement stm2 = con.prepareStatement(sql2);
			System.out.println("Enter  ToAccount number");
			stm2.setInt(1, sc.nextInt());
			ResultSet rs2 = stm2.executeQuery();
			rs2.next();
			User toAccount = new User(rs2.getInt(1), rs2.getDouble(2));
			System.out.println("toaccount" + toAccount.getBalance());
			 logger.info("from and to account loaded succesfully ");

			transaction(fromAccount, toAccount, con);

		} catch (Exception e) {
			// TODO: handle exception
			// e.printStackTrace();
			throw new AccountNotFoundException("Invaild Account number");
		}

	}

}
