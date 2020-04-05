package com.telusko.demorest;

import java.util.ArrayList;
import java.sql.*;
import java.util.List;

//creating a mock database

public class AlienRepository {
	List<Alien> aliens;
	
	Connection con = null;
	
	public AlienRepository() {
		String url = "jdbc:mysql://localhost:3306/restdb";
		String username = "root";
		String password = "root";
		try {
				Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, username, password);
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	
	public List<Alien> getAliens(){
		
		List<Alien> aliens = new ArrayList<Alien>();
		String sql = "select * from alien";
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				Alien a = new Alien();
				a.setId(rs.getInt(1));
				a.setName(rs.getString(2));
				a.setPoints(rs.getInt(3));
				
				aliens.add(a);
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return aliens;
	}
	
	public Alien getAlien(int id) {
		String sql = "select * from alien where id = " + id;
		Alien a = new Alien();
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			if(rs.next()) {
				
				a.setId(rs.getInt(1));
				a.setName(rs.getString(2));
				a.setPoints(rs.getInt(3));
				
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return a;
	}

	public void create(Alien a1) {
		String sql = "insert into alien values(?,?,?)";
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, a1.getId());
			st.setString(2, a1.getName());
			st.setInt(3, a1.getPoints());
			st.executeUpdate();
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void update(Alien a1) {
		String sql = "update alien set name=?, points=? where id=?";
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(3, a1.getId());
			st.setString(1, a1.getName());
			st.setInt(2, a1.getPoints());
			st.executeUpdate();
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void delete(int id) {
		// TODO Auto-generated method stub
		String sql = "delete from alien where id=?";
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, id);
			st.executeUpdate();
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
}






