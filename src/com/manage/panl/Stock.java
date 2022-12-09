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
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.stock.mysql.Mysql;
import com.stock.other.Table;
import com.stock.other.Tools;

public class Stock extends JPanel {

	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	public Stock() {
		JPanel panel_9 = new JPanel();
		panel_9.setBorder(new TitledBorder(null, "仓库信息", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_9.setBounds(10, 5, 841, 70);
		this.add(panel_9);
		panel_9.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		JLabel lblNewLabel = new JLabel("仓库号");
		panel_9.add(lblNewLabel);

		textField = new JTextField();
		panel_9.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("仓库名");
		panel_9.add(lblNewLabel_1);

		textField_1 = new JTextField();
		panel_9.add(textField_1);
		textField_1.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("仓库描述");
		panel_9.add(lblNewLabel_2);

		textField_2 = new JTextField();
		panel_9.add(textField_2);
		textField_2.setColumns(30);

		JPanel panel_10 = new JPanel();
		panel_10.setBorder(new TitledBorder(null, "仓库操作", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		FlowLayout flowLayout = (FlowLayout) panel_10.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel_10.setBounds(10, 85, 841, 70);
		this.add(panel_10);

		JLabel lblNewLabel_3 = new JLabel("仓库号");
		panel_10.add(lblNewLabel_3);

		textField_3 = new JTextField();
		panel_10.add(textField_3);
		textField_3.setColumns(10);

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
		Object[] columns = { "仓库号", "仓库名", "仓库描述" };
		// 初始化表格
		Table t1table = new Table(columns);
		JScrollPane JS = t1table.getJScrollPane();
		// 定义表格的控制权
		DefaultTableModel model = t1table.getModel();

		JS.setBounds(10, 165, 841, 238);
		this.add(JS);

		// 增加
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 连接数据库插入数据
				String id = textField.getText();
				String namez = textField_1.getText();
				String pos = textField_2.getText();

				// 以上注册信息不能为空
				if (id.equals("")) {
					Tools.showMessage("增加信息", "仓库号不能为空");
				} else if (namez.equals("")) {
					Tools.showMessage("增加信息", "仓库名不能为空");
				} else if (pos.equals("")) {
					Tools.showMessage("增加信息", "仓库描述不能为空");
				} else {
					// 以上条件都需要满足
					// 插入语句
					String sql = "INSERT INTO d_stock(id,namez,pos) VALUES (?,?,?)";
					int a = Mysql.update(sql, id, namez, pos);
					System.out.println(a);
					// 根据返回值判断状态
					if (a == -1) {
						Tools.showMessage("增加消息", "当前仓库已存在，请重新增加");
					}
					if (a == 1) {
						Tools.showMessage("增加消息", "增加成功");
					}
				}
			}

		});

		// 删除
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textField_3.getText().equals("")) {
					Tools.showMessage("系统提示", "请输入仓库号");
				} else {
					String sql = "delete from d_stock where id=?";
					int a = Mysql.update(sql, textField_3.getText());
					if (a == 1) {
						Tools.showMessage("系统提示", "删除成功");
					}
					if (a == 0) {
						Tools.showMessage("系统提示", "删除失败,仓库不存在");
					}
					if (a == -1) {
						Tools.showMessage("系统提示", "删除失败,仓库不存在");
					}
				}
			}

		});

		// 更改
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textField_3.getText().equals("")) {
					Tools.showMessage("系统提示", "更改仓库号不能为空");
				} else if (textField.getText().equals("")) {
					Tools.showMessage("系统提示", "仓库号不能为空");
				} else if (textField_1.getText().equals("")) {
					Tools.showMessage("系统提示", "仓库名不能为空");
				} else if (textField_2.getText().equals("")) {
					Tools.showMessage("系统提示", "仓库描述不能为空");
				} else {
					String sql = "UPDATE d_stock set id=?,namez=?,pos=? where id=?";
					int a = Mysql.update(sql, textField.getText(), textField_1.getText(), textField_2.getText(),
							textField_3.getText());
					if (a == 1) {
						Tools.showMessage("系统提示", "更改成功");
					}
					if (a == 0 || a == -1) {
						Tools.showMessage("系统提示", "更改失败");
					}
				}
			}

		});

		// 查询
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textField_3.getText().equals("")) {
					String sql = "select * from d_stock";
					ResultSet rs = Mysql.query(sql, null);
					// 用一个工具自动插入到表格
					Tools.addTable(rs, model, 3);
				} else {
					String sql = "select * from d_stock where id=?";
					ResultSet rs = Mysql.query(sql, textField_3.getText());
					// 用一个工具自动插入到表格
					ResultSet rs1 = Mysql.query(sql, textField_3.getText());
					Tools.addTable(rs, model, 3);
					try {
						if (rs1.next()) {
							textField.setText(rs1.getString(1));
							textField_1.setText(rs1.getString(2));
							textField_2.setText(rs1.getString(3));

						}
						rs1.close();
					} catch (SQLException e2) {
						e2.printStackTrace();
					}
				}
			}

		});
	}
}
