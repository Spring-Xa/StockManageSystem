package com.stock.windows;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.stock.mysql.Mysql;
import com.stock.other.Tools;

public class Login {

	public JFrame frame;
	public static JTextField textField;
	private JPasswordField passwordField;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("仓库管理系统");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("账号");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("宋体", Font.BOLD, 14));
		lblNewLabel.setBounds(88, 93, 58, 15);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("密码");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("宋体", Font.BOLD, 14));
		lblNewLabel_1.setBounds(88, 139, 58, 15);
		frame.getContentPane().add(lblNewLabel_1);

		textField = new JTextField();
		textField.setBounds(131, 90, 173, 21);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(131, 136, 173, 21);
		frame.getContentPane().add(passwordField);

		lblNewLabel_2 = new JLabel("账号注册>");
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				Register window = new Register();
				window.frame.setVisible(true);
			}
		});
		lblNewLabel_2.setBounds(368, 238, 58, 15);
		frame.getContentPane().add(lblNewLabel_2);

		lblNewLabel_3 = new JLabel("仓库管理系统");
		lblNewLabel_3.setFont(new Font("华文行楷", Font.BOLD, 22));
		lblNewLabel_3.setForeground(Color.BLACK);
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(10, 10, 416, 59);
		frame.getContentPane().add(lblNewLabel_3);

		JButton btnNewButton = new JButton("登录");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 登录
				String account = textField.getText();
				String pwd = new String(passwordField.getPassword());
				// 账号密码不能为空
				if (account.equals("")) {
					Tools.showMessage("注册信息", "账号不能为空");
				} else if (pwd.equals("")) {
					Tools.showMessage("注册信息", "密码不能为空");
				} else {
					// 表示账号密码正确，可进行登录
					String sql = "select * from d_user where account=? and pwd=?;";
					ResultSet rs = Mysql.query(sql, account, pwd);
					try {
						// 有数值则成功，无则执行语句失败
						if (rs.next()) {
							String pow = rs.getString("pow");
							if (pow.equals("1")) {
								ManageWindows window = new ManageWindows();
								window.frame.setVisible(true);
								frame.dispose();
								Tools.showMessage("登录消息", "登录管理员");
							}
							if (pow.equals("2")) {
								UserWindows window = new UserWindows();
								window.frame.setVisible(true);
								frame.dispose();
								Tools.showMessage("登录消息", "登录用户");
							}
						} else {
							Tools.showMessage("登录消息", "当前用户不存在，或密码错误");
						}

						rs.close();
					} catch (Exception e2) {
						System.out.println("SQL语句错误");
						e2.printStackTrace();
					}
				}
			}

		});
		btnNewButton.setBounds(131, 186, 173, 23);
		frame.getContentPane().add(btnNewButton);
	}
}
