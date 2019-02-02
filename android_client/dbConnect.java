package com.example.apostolis.katanemhmena;

import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class dbConnect {
	private Connection connect;
	private Statement statement;
	private ResultSet result;
	String query;		
	double x;
	ArrayList<CheckIn> list;
	private CheckIn checkin; 
	private String dburl="";
	public dbConnect(double firstx, double secondx, double firsty, double secondy){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection(dburl);
			statement = connect.createStatement();
			query = "select * from checkins where "
					+ "latitude >= " + firstx + " and latitude < " + secondx + " and longitude >= " + firsty + " and longitude < " + secondy;
			result = statement.executeQuery(query);
			result.first();
			list = new ArrayList<CheckIn>();
			while(result.next()){
				checkin = new CheckIn(result.getLong(1), result.getInt(2), result.getString(3), result.getString(4), result.getString(5), result.getInt(6), result.getDouble(7), result.getDouble(8), result.getTimestamp(9), result.getString(10));
				list.add(checkin);
			}
			if(list.size() == 0){
				list.add(new CheckIn(0, 0, "" , "", "", 0 , 0, 0, null, ""));
			}
		}catch(ClassNotFoundException e){
			System.err.println("Driver Not Found!!!");
		} catch (SQLException e) {
			System.err.println("Cannot Conect to database");
		}
	}
	
	public dbConnect(){
		
	}
	
	public boolean insert(String name,String point,String url){
		String query;
		try{

			try {
				Class.forName("com.mysql.jdbc.Driver").newInstance();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			connect = DriverManager.getConnection(dburl);
			statement = connect.createStatement();
			query="select max(id),max(user) from checkins";
			result=statement.executeQuery(query);
			result.first();
			long id=result.getLong(1);
			int user=result.getInt(2);
			id++;
			user++;
			Log.d("db","aaaaaaaa");
			StringTokenizer token = new StringTokenizer(point, ",");
			double latitude=Double.parseDouble(token.nextToken().trim());
			double longtitude=Double.parseDouble(token.nextToken().trim());
			query="select now() as date";
			result=statement.executeQuery(query);
			result.first();
			Timestamp x=result.getTimestamp(1);
			query="insert into checkins values("+id+"," + user + ", aaa,"+name+", null, null,"+latitude+","+longtitude+","+x+","+url+")";
			System.out.println(query);
			result=statement.executeQuery(query);
			return true;
			
		}catch(ClassNotFoundException e){
			System.err.println("Driver Not Found!!!");
			return false;
		} catch (SQLException e) {
			System.err.println("Cannot Conect to database");
			return false;
		}
	}
	
	
	public ArrayList<CheckIn> getList(){
		return list;
	}
}
