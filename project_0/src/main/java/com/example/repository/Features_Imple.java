package com.example.repository;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.example.db.ConnectionFactory;
import com.example.entity.Transaction;
import com.example.entity.User;

public class Features_Imple implements features  {
	
	

	@Override
	public void topTransaction() {
		// TODO Auto-generated method stub
		Connection con = null;
	try {	
		List <Transaction> list = new ArrayList<Transaction>();
		
		con = ConnectionFactory.getConnection();
		String sql1 = "select amount"
				+ "from transaction"
				+ "order by amount desc"
				+ "limit 10 ";
		Statement stm1 = con.createStatement();
		ResultSet rs1 = stm1.executeQuery(sql1);
		while(rs1.next()) {
			
			Transaction transac = new Transaction(rs1.getInt(1), rs1.getDate(2), rs1.getDouble(3), rs1.getString(4));
			list.add(transac);
			
		}
		//Iterator printTransac = list.iterator();
		list.forEach(transaction ->{
			System.out.println(transaction);
			
		});
	}catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
	finally {
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
		
		
		
	}
	
	
	
	public void lastMonthTransaction () {
		
		
		
		
	}

	
}
