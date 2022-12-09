package com.stock.windows;

import com.stock.mysql.DBUtil;

public class StockSystem {
	// 程序的入口
	public static void main(String[] arg) {
		DBUtil db = new DBUtil("root", "root", "db_stock");
		Login window = new Login();
		window.frame.setVisible(true);
	}
}
