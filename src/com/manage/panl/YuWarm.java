package com.manage.panl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.stock.mysql.Mysql;
import com.stock.other.Table;
import com.stock.other.Tools;

public class YuWarm extends JPanel {

	public YuWarm() {

		JPanel panel_10 = new JPanel();
		panel_10.setBorder(new TitledBorder(null, "预警查询", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_10.setBounds(10, 10, 841, 81);
		this.add(panel_10);

		JButton btnNewButton = new JButton("查询预警");

		// 表格的数据
		// 表格的标题
		Object[] columns = { "备品备件号", "备品备件名", "供应商", "规格", "单位", "库存", "价格", "描述", "预警数量" };
		// 初始化表格
		Table t1table = new Table(columns);
		JScrollPane JS = t1table.getJScrollPane();
		// 定义表格的控制权
		DefaultTableModel model = t1table.getModel();

		JS.setBounds(10, 100, 841, 310);
		this.add(JS);

		// 查询
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql = "SELECT a.* FROM d_beipin a, d_beipin b WHERE a.id = b.id AND a.nums <= b.expectnums AND a.expectnums != -1";
				ResultSet rs = Mysql.query(sql, null);
				// 用一个工具自动插入到表格
				Tools.addTable(rs, model, 9);
			}
		});
		panel_10.add(btnNewButton);
	}
}
