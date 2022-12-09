package com.manage.panl;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.stock.mysql.Mysql;
import com.stock.other.Table;
import com.stock.other.Tools;

public class UserPanl extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTable table;

	public UserPanl() {
		JPanel panel_9 = new JPanel();
		panel_9.setBorder(new TitledBorder(null, "用户管理", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		FlowLayout flowLayout = (FlowLayout) panel_9.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel_9.setBounds(10, 10, 841, 60);
		this.add(panel_9);

		JLabel lblNewLabel = new JLabel("账号");
		panel_9.add(lblNewLabel);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);

		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.LEFT);
		panel_9.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("密码");
		panel_9.add(lblNewLabel_1);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);

		textField_1 = new JTextField();
		textField_1.setHorizontalAlignment(SwingConstants.LEFT);
		panel_9.add(textField_1);
		textField_1.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("姓名");
		panel_9.add(lblNewLabel_2);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);

		textField_2 = new JTextField();
		textField_2.setHorizontalAlignment(SwingConstants.LEFT);
		panel_9.add(textField_2);
		textField_2.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("电话");
		panel_9.add(lblNewLabel_3);
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);

		textField_3 = new JTextField();
		textField_3.setHorizontalAlignment(SwingConstants.LEFT);
		panel_9.add(textField_3);
		textField_3.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("部门");
		panel_9.add(lblNewLabel_4);
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);

		textField_4 = new JTextField();
		textField_4.setHorizontalAlignment(SwingConstants.LEFT);
		panel_9.add(textField_4);
		textField_4.setColumns(10);

		JPanel panel_10 = new JPanel();
		panel_10.setBorder(new TitledBorder(null, "操作管理", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		FlowLayout flowLayout_1 = (FlowLayout) panel_10.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		panel_10.setBounds(10, 93, 841, 60);
		this.add(panel_10);

		JLabel lblNewLabel_5 = new JLabel("账号");
		panel_10.add(lblNewLabel_5);

		textField_5 = new JTextField();
		panel_10.add(textField_5);
		textField_5.setColumns(10);

		JButton btnNewButton = new JButton("增加");

		panel_10.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("删除");

		panel_10.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("更改");

		panel_10.add(btnNewButton_2);

		JButton btnNewButton_3 = new JButton("查询");

		panel_10.add(btnNewButton_3);

		// 表格的数据
		// 表格的标题
		Object[] columns = { "账号", "密码", "姓名", "电话", "部门" };
		// 初始化表格
		Table t1table = new Table(columns);
		JScrollPane JS = t1table.getJScrollPane();
		// 定义表格的控制权
		DefaultTableModel model = t1table.getModel();

		JS.setBounds(10, 163, 841, 248);
		this.add(JS);

		// 按钮监听
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 增加
				// 连接数据库插入数据
				String account = textField.getText();
				String pwd = textField_1.getText();
				String namez = textField_2.getText();
				String phone = textField_3.getText();
				String depart = textField_4.getText();

				// 以上注册信息不能为空
				if (account.equals("")) {
					Tools.showMessage("增加信息", "账号不能为空");
				} else if (pwd.equals("")) {
					Tools.showMessage("增加信息", "密码不能为空");
				} else if (namez.equals("")) {
					Tools.showMessage("增加信息", "姓名不能为空");
				} else if (phone.equals("")) {
					Tools.showMessage("增加信息", "手机不能为空");
				} else if (depart.equals("")) {
					Tools.showMessage("增加信息", "部门不能为空");
				} else {
					// 以上条件都需要满足
					// 插入语句
					String sql = "INSERT INTO d_user(account,pwd,namez,phone,depart) VALUES (?,?,?,?,?)";
					int a = Mysql.update(sql, account, pwd, namez, phone, depart);
					System.out.println(a);
					// 根据返回值判断状态
					if (a == -1) {
						Tools.showMessage("增加消息", "当前账号已存在，请重新增加");
					}
					if (a == 1) {
						Tools.showMessage("增加消息", "增加成功");
					}
				}

			}
		});

		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textField_5.getText().equals("")) {
					Tools.showMessage("系统提示", "请输入账号");
				} else {
					String sql = "delete from d_user where account=?";
					int a = Mysql.update(sql, textField_5.getText());
					if (a == 1) {
						Tools.showMessage("系统提示", "删除成功");
					}
					if (a == 0) {
						Tools.showMessage("系统提示", "删除失败,账号不存在");
					}
					if (a == -1) {
						Tools.showMessage("系统提示", "删除失败,账号不存在");
					}
				}
			}
		});

		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 分为两种
				if (textField_5.getText().equals("")) {
					String sql = "select * from d_user where pow='2'";
					ResultSet rs = Mysql.query(sql, null);
					// 用一个工具自动插入到表格
					Tools.addTable(rs, model, 5);
				} else {
					String sql = "select * from d_user where pow='2' and account=?";
					ResultSet rs = Mysql.query(sql, textField_5.getText());
					// 用一个工具自动插入到表格
					ResultSet rs1 = Mysql.query(sql, textField_5.getText());
					Tools.addTable(rs, model, 5);
					try {
						if (rs1.next()) {
							textField.setText(rs1.getString(1));
							textField_1.setText(rs1.getString(2));
							textField_2.setText(rs1.getString(3));
							textField_3.setText(rs1.getString(4));
							textField_4.setText(rs1.getString(5));
						}
						rs1.close();
					} catch (SQLException e2) {
						e2.printStackTrace();
					}
				}
			}
		});

		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textField_5.getText().equals("")) {
					Tools.showMessage("系统提示", "更改账号不能为空");
				} else if (textField.getText().equals("")) {
					Tools.showMessage("系统提示", "账号不能为空");
				} else if (textField_1.getText().equals("")) {
					Tools.showMessage("系统提示", "密码不能为空");
				} else if (textField_2.getText().equals("")) {
					Tools.showMessage("系统提示", "姓名不能为空");
				} else if (textField_3.getText().equals("")) {
					Tools.showMessage("系统提示", "电话不能为空");
				} else if (textField_4.getText().equals("")) {
					Tools.showMessage("系统提示", "部门不能为空");
				} else {
					String sql = "UPDATE d_user set account=?,pwd=?,namez=?,phone=?,depart=? where account=?";
					int a = Mysql.update(sql, textField.getText(), textField_1.getText(), textField_2.getText(),
							textField_3.getText(), textField_4.getText(), textField_5.getText());
					if (a == 1) {
						Tools.showMessage("系统提示", "更改成功");
					}
					if (a == 0 || a == -1) {
						Tools.showMessage("系统提示", "更改失败");
					}
				}
			}
		});
	}

}
