package com.manage.panl;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.stock.mysql.Mysql;
import com.stock.other.Table;
import com.stock.other.Tools;

public class OutStock extends JPanel {

	private JTextField textField;

	public OutStock() {
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(null, "出库审核", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		FlowLayout flowLayout = (FlowLayout) panel_4.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel_4.setBounds(10, 10, 841, 56);
		this.add(panel_4);

		JLabel lblNewLabel = new JLabel("仓库号");
		panel_4.add(lblNewLabel);

		textField = new JTextField();
		panel_4.add(textField);
		textField.setColumns(10);

		JButton btnNewButton = new JButton("查询出库");
		panel_4.add(btnNewButton);

		JLabel lblNewLabel_1 = new JLabel("tip:(单击对应表格进行审核)");
		lblNewLabel_1.setForeground(Color.RED);
		panel_4.add(lblNewLabel_1);

		// 表格的数据
		// 表格的标题
		Object[] columns = { "出库编号", "备品备件编号", "备品备件名", "车间号", "车间名", "出库人", "出库时间", "出库数量", "审核状态" };
		// 初始化表格
		Table t1table = new Table(columns);
		JScrollPane JS = t1table.getJScrollPane();
		// 定义表格的控制权
		DefaultTableModel model = t1table.getModel();

		JS.setBounds(10, 76, 841, 327);
		JTable table = t1table.getTables();

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JTable temptable = (JTable) e.getSource();
				int row = temptable.getSelectedRow();
//				int column = temptable.getSelectedColumn();
				String temp = (String) temptable.getValueAt(row, 0);
				ResultSet rs = Mysql.query("SELECT * FROM d_outstock WHERE id=?", temp);
				try {
					while (rs.next()) {
						temp = rs.getString("out_nums");
						// 获取到出库的值
						System.out.println(temp);
						int opt = JOptionPane.showConfirmDialog(null, "是否通过审核？", "确认信息", JOptionPane.YES_NO_OPTION);
						// 0 1 -1
						if (opt == JOptionPane.YES_OPTION) {
							System.out.println("点击确认了");
							// 获取出库编号
							String temp2 = (String) temptable.getValueAt(row, 0);
							// 判断仓库库存是否满足出库
							ResultSet rs1 = Mysql.query("select * from d_beipin where id=?",
									rs.getString("bei_pin_id"));
							rs1.next();
							String nums = rs1.getString("nums");

							rs1.close();

							int c = Integer.parseInt(nums);
							int cc = Integer.parseInt(rs.getString("out_nums"));
							if (c > cc) {
								// 满足条件,进行出库
								int a = Mysql.update("update d_outstock set star='1' where id=? and star='0'", temp2);
								if (a == 1) {
									String temp1 = rs.getString("bei_pin_id");
									// 已经出库的数量，出库的备品号
									String sql = "update d_beipin set nums=nums-? where id=?";
									a = Mysql.update(sql, temp, temp1);
									if (a == 1) {
										Tools.showMessage("系统提示", "审核通过");

									} else {
										Tools.showMessage("系统提示", "审核未通过");
									}
								} else {
									Tools.showMessage("系统提示", "当前状态不可操作");
								}
							} else {
								Tools.showMessage("系统提示", "库存不足，无法出库，已自动拒绝");
								Mysql.update("update d_outstock set star='2' where id=? and star='0'", temp2);
							}

						}
						if (opt == JOptionPane.NO_OPTION) {
							// 点击否，代表拒绝
							String temp2 = (String) temptable.getValueAt(row, 0);
							int a = Mysql.update("update d_outstock set star='2' where id=? and star='0'", temp2);
							if (a == 1) {
								Tools.showMessage("系统提示", "成功拒绝");
							} else {
								Tools.showMessage("系统提示", "当前状态不可操作");
							}
						}
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

			}
		});

		this.add(JS);

		// 查找出库
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textField.getText().equals("")) {
					String sql = "SELECT\r\n" + "	a.id,\r\n" + "	a.bei_pin_id,\r\n" + "	b.namez,\r\n"
							+ "	a.out_stock_id,\r\n" + "	c.namez,\r\n" + "	d.namez,\r\n" + "	a.out_time,\r\n"
							+ "	a.out_nums,\r\n" + "IF\r\n" + "	(\r\n" + "		a.star = 0,\r\n" + "		'审核中',\r\n"
							+ "	IF\r\n" + "	( a.star = 1, '通过审核', '未通过审核' )) \r\n" + "FROM\r\n" + "	d_outstock a,\r\n"
							+ "	d_beipin b,\r\n" + "	d_car_room c,\r\n" + "	d_user d\r\n" + "WHERE\r\n"
							+ "	b.id=a.bei_pin_id\r\n" + "	AND c.id=a.out_stock_id\r\n"
							+ "	AND d.account=a.out_peo_id";
					ResultSet rs = Mysql.query(sql, null);
					Tools.addTable(rs, model, 9);
				} else {
					String sql = "SELECT\r\n" + "	a.id,\r\n" + "	a.bei_pin_id,\r\n" + "	b.namez,\r\n"
							+ "	a.out_stock_id,\r\n" + "	c.namez,\r\n" + "	d.namez,\r\n" + "	a.out_time,\r\n"
							+ "	a.out_nums,\r\n" + "IF\r\n" + "	(\r\n" + "		a.star = 0,\r\n" + "		'审核中',\r\n"
							+ "	IF\r\n" + "	( a.star = 1, '通过审核', '未通过审核' )) \r\n" + "FROM\r\n" + "	d_outstock a,\r\n"
							+ "	d_beipin b,\r\n" + "	d_car_room c,\r\n" + "	d_user d\r\n" + "WHERE\r\n"
							+ "	b.id=a.bei_pin_id\r\n" + "	AND c.id=a.out_stock_id\r\n"
							+ "	AND d.account=a.out_peo_id AND a.id=?";
					ResultSet rs = Mysql.query(sql, textField.getText());
					Tools.addTable(rs, model, 9);
				}

			}
		});
	}
}
