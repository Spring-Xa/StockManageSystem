package com.user.panl;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.stock.mysql.Mysql;
import com.stock.other.Table;
import com.stock.other.Tools;
import com.stock.windows.Login;

public class OutStock extends JPanel {

	private JTextField textField;
	private JTextField textField_1;

	public OutStock() {
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "出库操作", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 10, 841, 100);
		this.add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));

		JLabel lblNewLabel = new JLabel("备品备件号");
		panel.add(lblNewLabel);

		JComboBox comboBox = new JComboBox();
		panel.add(comboBox);

		JLabel lblNewLabel_1 = new JLabel("车间");
		panel.add(lblNewLabel_1);

		JComboBox comboBox_1 = new JComboBox();
		panel.add(comboBox_1);

		JLabel lblNewLabel_2 = new JLabel("出库数量");
		panel.add(lblNewLabel_2);

		textField = new JTextField();
		panel.add(textField);
		textField.setColumns(10);

		JButton btnNewButton = new JButton("提交出库");
		panel.add(btnNewButton);

		JButton btnNewButton_3 = new JButton("查询出库");
		panel.add(btnNewButton_3);

		JButton btnNewButton_4 = new JButton("刷新库存");
		panel.add(btnNewButton_4);

		JLabel lblNewLabel_3 = new JLabel("出库编号");
		panel.add(lblNewLabel_3);

		textField_1 = new JTextField();
		panel.add(textField_1);
		textField_1.setColumns(15);

		JButton btnNewButton_2 = new JButton("撤销出库");
		panel.add(btnNewButton_2);

		// 表格的数据
		// 表格的标题
		Object[] columns = { "出库编号", "备品备件编号", "备品备件名", "车间号", "车间名", "出库人", "出库时间", "出库数量", "审核状态" };
		// 初始化表格
		Table t1table = new Table(columns);
		JScrollPane JS = t1table.getJScrollPane();
		// 定义表格的控制权
		DefaultTableModel model = t1table.getModel();

		JS.setBounds(10, 115, 841, 293);
		this.add(JS);

		// 刷新
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 获取仓库，以及备品备件
				String sql = "SELECT id,namez FROM d_beipin";
				ResultSet rs = Mysql.query(sql, null);
				try {
					comboBox.removeAll();
					comboBox_1.removeAll();
					// 获取备件号
					while (rs.next()) {
						String id = rs.getString(1);
						String namez = rs.getString(2);
						String temp = id + "-" + namez;
						comboBox.addItem(temp);
					}
					rs.close();

					// 获取仓库
					sql = "SELECT id,namez FROM d_car_room";
					rs = Mysql.query(sql, null);
					while (rs.next()) {
						String id = rs.getString(1);
						String namez = rs.getString(2);
						String temp = id + "-" + namez;
						comboBox_1.addItem(temp);
					}

				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});

		// 出库
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 添加
				String tem = (String) comboBox.getSelectedItem();
				String tem1 = (String) comboBox_1.getSelectedItem();

				String id = tem.split("-")[0];
				String id1 = tem1.split("-")[0];
				String sql = "insert into d_outstock(bei_pin_id,out_stock_id,out_peo_id,out_time,out_nums) VALUES(?,?,?,now(),?)";
				String account = Login.textField.getText();
				String nums = textField.getText();
				if (nums.equals("")) {
					Tools.showMessage("系统提示", "请输入出库数量");
				} else {
					int a = Mysql.update(sql, id, id1, account, nums);
					if (a == 1) {
						Tools.showMessage("系统提示", "等待管理员审核中");
					} else {
						Tools.showMessage("系统提示", "请检查输入格式");
					}
				}

			}
		});

		// 撤销出库
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textField_1.equals("")) {
					Tools.showMessage("系统提示", "请输入出库编号");
				} else {
					String sql = "DELETE FROM d_outstock WHERE id=? AND star='0'";
					int a = Mysql.update(sql, textField_1.getText());
					if (a == 0 || a == -1) {
						Tools.showMessage("系统提示", "管理员已审核，无法撤销");
					} else {
						Tools.showMessage("系统提示", "撤销成功");
					}
				}

			}
		});

		// 查找出库
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql = "SELECT\r\n" + "	a.id,\r\n" + "	a.bei_pin_id,\r\n" + "	b.namez,\r\n"
						+ "	a.out_stock_id,\r\n" + "	c.namez,\r\n" + "	d.namez,\r\n" + "	a.out_time,\r\n"
						+ "	a.out_nums,\r\n" + "IF\r\n" + "	(\r\n" + "		a.star = 0,\r\n" + "		'审核中',\r\n"
						+ "	IF\r\n" + "	( a.star = 1, '通过审核', '未通过审核' )) \r\n" + "FROM\r\n" + "	d_outstock a,\r\n"
						+ "	d_beipin b,\r\n" + "	d_car_room c,\r\n" + "	d_user d\r\n" + "WHERE\r\n"
						+ "	b.id=a.bei_pin_id\r\n" + "	AND c.id=a.out_stock_id\r\n"
						+ "	AND d.account=a.out_peo_id AND d.account=?";
				ResultSet rs = Mysql.query(sql, Login.textField.getText());
				Tools.addTable(rs, model, 9);
			}
		});

		// 登录

	}
}
