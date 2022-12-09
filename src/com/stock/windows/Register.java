package com.stock.windows;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.stock.mysql.Mysql;
import com.stock.other.Tools;

public class Register {

	public JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;
	private JLabel lblNewLabel_2;
	private JTextField textField_1;
	private JLabel lblNewLabel_3;
	private JTextField textField_2;
	private JLabel lblNewLabel_4;
	private JTextField textField_3;
	private JButton btnNewButton;
	private JButton btnNewButton_1;

	/**
	 * Create the application.
	 */
	public Register() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("账号注册");
		frame.setBounds(100, 100, 390, 432);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("账号");
		lblNewLabel.setBounds(85, 62, 58, 15);
		frame.getContentPane().add(lblNewLabel);

		textField = new JTextField();
		textField.setBounds(124, 57, 171, 21);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("密码");
		lblNewLabel_1.setBounds(85, 114, 58, 15);
		frame.getContentPane().add(lblNewLabel_1);

		passwordField = new JPasswordField();
		passwordField.setBounds(124, 111, 171, 21);
		frame.getContentPane().add(passwordField);

		lblNewLabel_2 = new JLabel("姓名");
		lblNewLabel_2.setBounds(85, 167, 58, 15);
		frame.getContentPane().add(lblNewLabel_2);

		textField_1 = new JTextField();
		textField_1.setBounds(124, 164, 171, 21);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);

		lblNewLabel_3 = new JLabel("电话");
		lblNewLabel_3.setBounds(85, 216, 58, 15);
		frame.getContentPane().add(lblNewLabel_3);

		textField_2 = new JTextField();
		textField_2.setBounds(124, 213, 171, 21);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);

		lblNewLabel_4 = new JLabel("部门");
		lblNewLabel_4.setBounds(85, 266, 58, 15);
		frame.getContentPane().add(lblNewLabel_4);

		textField_3 = new JTextField();
		textField_3.setBounds(124, 263, 171, 21);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);

		btnNewButton = new JButton("注册");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 连接数据库插入数据
				String account = textField.getText();
				String pwd = new String(passwordField.getPassword());
				String namez = textField_1.getText();
				String phone = textField_2.getText();
				String depart = textField_3.getText();

				// 以上注册信息不能为空
				if (account.equals("")) {
					Tools.showMessage("注册信息", "账号不能为空");
				} else if (pwd.equals("")) {
					Tools.showMessage("注册信息", "密码不能为空");
				} else if (namez.equals("")) {
					Tools.showMessage("注册信息", "姓名不能为空");
				} else if (phone.equals("")) {
					Tools.showMessage("注册信息", "手机不能为空");
				} else if (depart.equals("")) {
					Tools.showMessage("注册信息", "部门不能为空");
				} else {
					// 以上条件都需要满足
					// 插入语句
					String sql = "INSERT INTO d_user(account,pwd,namez,phone,depart) VALUES (?,?,?,?,?)";
					int a = Mysql.update(sql, account, pwd, namez, phone, depart);
					System.out.println(a);
					// 根据返回值判断状态
					if (a == -1) {
						Tools.showMessage("注册消息", "当前账号已存在，请重新注册");
					}
					if (a == 1) {
						Tools.showMessage("注册消息", "注册成功");
					}
				}

			}
		});
		btnNewButton.setBounds(67, 328, 97, 23);
		frame.getContentPane().add(btnNewButton);

		btnNewButton_1 = new JButton("重置");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
				textField_3.setText("");
				passwordField.setText("");
			}
		});
		btnNewButton_1.setBounds(198, 328, 97, 23);
		frame.getContentPane().add(btnNewButton_1);
	}

}
