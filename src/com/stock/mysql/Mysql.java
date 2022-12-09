package com.stock.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Mysql {

	/**
	 * 当前类是执行插入与查询的重复更新 数据库分为更新和查询
	 */
	private static Connection conn = DBUtil.conn;

	public static int update(String sql, String... parmes) {
		try {
			// 采用jdbc预处理方式
			PreparedStatement preSql = conn.prepareStatement(sql);
			int i = 1;
			for (String parme : parmes) {
				preSql.setString(i++, parme);
			}
			// 成功执行了 插入成功1 插入失败0
			return preSql.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}

	public static ResultSet query(String sql, String... parmes) {
		try {
			// 采用jdbc预处理方式
			PreparedStatement preSql = conn.prepareStatement(sql);
			int i = 1;
			if (parmes != null) {
				for (String parme : parmes) {
					preSql.setString(i++, parme);
				}
			}
			// 成功执行了 插入成功1 插入失败0
			// 返回一个rs的结果集
			return preSql.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
