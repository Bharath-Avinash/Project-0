package com.example.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.example.db.ConnectionFactory;
import com.example.entity.Transaction;
import com.example.entity.User;

public class Features_Imple implements features {
	
	
	Scanner sc = new Scanner(System.in);
	public static Logger loggers = Logger.getLogger("app1");

	public Features_Imple() {
		super();
		
		loggers.info("features_imple instance created");
	}

	@Override
	public void topTransaction() {
		// TODO Auto-generated method stub
		
		Connection con = null;

		try {
			List<Transaction> list = new ArrayList<Transaction>();

			con = ConnectionFactory.getConnection();
			String sql1 = "select * from transaction  order by amount desc limit 10 ";
			Statement stm1 = con.createStatement();
			ResultSet rs1 = stm1.executeQuery(sql1);
			while (rs1.next()) {

				Transaction transac = new Transaction(rs1.getInt(1), rs1.getDate(2), rs1.getDouble(3),
						rs1.getString(4));
				list.add(transac);

			}
			// Iterator printTransac = list.iterator();
			list.forEach(transaction -> {
				System.out.println(transaction);

			});
			
			
			loggers.info("topTranaction done successfully");
		} catch (Exception e) {
			// TODO: handle exception
			loggers.error("oops ");
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	@Override
	public void currentMonthTransaction() {
		// TODO Auto-generated method stub
		Connection con = null;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
		String fdate = null;
		String tdate = null;

		try {
			List<Transaction> list = new ArrayList<Transaction>();
			LocalDate today = LocalDate.now();
			int month = today.getMonthValue();
			fdate = "1/" + month + "/2021";
			LocalDate fromDate = LocalDate.parse(fdate, formatter);
			//LocalDate toDate = LocalDate.now();
			LocalDate toDate1 = LocalDate.now();

			con = ConnectionFactory.getConnection();
			String sql1 = "select * from transaction  where date between ? and ? ";
			PreparedStatement stm1 = con.prepareStatement(sql1);
			stm1.setDate(1, java.sql.Date.valueOf(fromDate));
			stm1.setDate(2, java.sql.Date.valueOf(toDate1));

			ResultSet rs1 = stm1.executeQuery();

			while (rs1.next()) {

				Transaction transac = new Transaction(rs1.getInt(1), rs1.getDate(2), rs1.getDouble(3),
						rs1.getString(4));
				list.add(transac);

			}
			// Iterator printTransac = list.iterator();
			list.forEach(transaction -> {
				System.out.println(transaction);

			});
			
			loggers.info("Current month transaction succesfully");
		} catch (Exception e) {
			// TODO: handle exception
			loggers.error("oops");
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	@Override
	public void lastThreeMonthTransaction() {
		// TODO Auto-generated method stub
		Connection con = null;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
		String fdate = null;
		// String tdate = null;

		try {
			List<Transaction> list = new ArrayList<Transaction>();

			fdate = "1/5/2021";
			LocalDate fromDate = LocalDate.parse(fdate, formatter);
			// LocalDate toDate = LocalDate.now();
			LocalDate toDate1 = LocalDate.now();

			con = ConnectionFactory.getConnection();
			String sql1 = "select * from transaction  where date between ? and ? ";
			PreparedStatement stm1 = con.prepareStatement(sql1);
			stm1.setDate(1, java.sql.Date.valueOf(fromDate));
			stm1.setDate(2, java.sql.Date.valueOf(toDate1));

			ResultSet rs1 = stm1.executeQuery();

			while (rs1.next()) {

				Transaction transac = new Transaction(rs1.getInt(1), rs1.getDate(2), rs1.getDouble(3),
						rs1.getString(4));
				list.add(transac);

			}
			// Iterator printTransac = list.iterator();
			list.forEach(transaction -> {
				System.out.println(transaction);

			});
			
			
		loggers.info("Last month transaction succesfully");
		} catch (Exception e) {
			// TODO: handle exception
			loggers.error("oops");
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	@Override
	public void dateRange() {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
		String fdate = null;
		String tdate = null;

		Connection con = null;
		try {

			System.out.println("Enter fromDate ");

			fdate = scanner.next();
			System.out.println("Enter toDate ");
			tdate = scanner.next();

			// LocalDate toDate = LocalDate.of(toYear, toMonth, toDay);

			// String date1 = scanner.next();
			// Date toDate = (Date) dateFormat.parse(date);
			// convert String to LocalDate
			LocalDate fromDate = LocalDate.parse(fdate, formatter);
			LocalDate toDate = LocalDate.parse(tdate, formatter);
			List<Transaction> list = new ArrayList<Transaction>();

			con = ConnectionFactory.getConnection();
			String sql1 = "select * from transaction  where date between ? and ? ";
			PreparedStatement stm1 = con.prepareStatement(sql1);
			stm1.setDate(1, java.sql.Date.valueOf(fromDate));
			stm1.setDate(2, java.sql.Date.valueOf(toDate));

			ResultSet rs1 = stm1.executeQuery();
			while (rs1.next()) {

				Transaction transac = new Transaction(rs1.getInt(1), rs1.getDate(2), rs1.getDouble(3),
						rs1.getString(4));
				list.add(transac);

			}
			// Iterator printTransac = list.iterator();
			list.forEach(transaction -> {
				System.out.println(transaction);

			});
			
			loggers.info("date range transaction successfull");
		} catch (Exception e) {
			// TODO: handle exception
			loggers.error("oops");
			e.printStackTrace();
		} finally {
			try {
				con.close();
				scanner.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
