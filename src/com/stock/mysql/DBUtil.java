package com.stock.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class DBUtil {
	/**
	 * 
	 * @param dbaccount  数据库账号
	 * @param dbpassword 数据库密码
	 * @param dbname     数据库名
	 */

	private String dbaccount;
	private String dbpassword;
	private String dbname;
	public static Connection conn;

	public DBUtil(String dbaccount, String dbpassword, String dbname) {
		this.dbaccount = dbaccount;
		this.dbname = dbname;
		this.dbpassword = dbpassword;
		// 连接数据库
		init();
	}

	void init() {
		// 连接驱动
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Loading driver succeeded");
		} catch (Exception e) {
			System.out.println("Failed to load driver");
		}
		// 连接数据库
		try {
			String url = "jdbc:mysql://localhost:3306/" + dbname
					+ "?characterEncoding=utf-8&useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
			conn = DriverManager.getConnection(url, dbaccount, dbpassword);
			System.out.println("Successfully connected to database");
		} catch (SQLException e1) {
			System.out.println(e1);
			System.out.println("Failed to connect to database");

		}
	}

	public static void main(String[] arg) {

		DBUtil db = new DBUtil("root", "root", "db_stock");
	}
}
