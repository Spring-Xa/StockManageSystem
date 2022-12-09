package com.stock.other;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Tools {
	/*
	 * 重复使用的代码
	 */

	/**
	 * 弹窗
	 * 
	 * @param title   标题
	 * @param content 内容
	 */
	public static void showMessage(String title, String content) {
		JOptionPane.showMessageDialog(null, content, title, JOptionPane.WARNING_MESSAGE);
	}

	/**
	 * 
	 * @param rs    结果
	 * @param model 表格控制
	 * @param count 行数
	 */
	public static void addTable(ResultSet rs, DefaultTableModel model, int count) {

		try {
			model.setNumRows(0);
			while (rs.next()) {
				String[] data = new String[count];
				for (int i = 0; i < count; i++) {
					data[i] = rs.getString(i + 1);
				}
				model.addRow(data);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
