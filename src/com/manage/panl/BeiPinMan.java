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

public class BeiPinMan extends JPanel {

	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;

	public BeiPinMan() {
		JPanel panel_9 = new JPanel();
		panel_9.setBorder(new TitledBorder(null, "备品备件信息", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		FlowLayout flowLayout = (FlowLayout) panel_9.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel_9.setBounds(10, 10, 841, 78);
		this.add(panel_9);

		JLabel lblNewLabel = new JLabel("备品备件号");
		panel_9.add(lblNewLabel);

		textField = new JTextField();
		panel_9.add(textField);
		textField.setColumns(15);

		JLabel lblNewLabel_1 = new JLabel("备品备件名");
		panel_9.add(lblNewLabel_1);

		textField_1 = new JTextField();
		panel_9.add(textField_1);
		textField_1.setColumns(15);

		JLabel lblNewLabel_2 = new JLabel("供应商");
		panel_9.add(lblNewLabel_2);

		textField_2 = new JTextField();
		panel_9.add(textField_2);
		textField_2.setColumns(15);

		JLabel lblNewLabel_3 = new JLabel("规格");
		panel_9.add(lblNewLabel_3);

		textField_3 = new JTextField();
		panel_9.add(textField_3);
		textField_3.setColumns(12);

		JLabel lblNewLabel_4 = new JLabel("单位");
		panel_9.add(lblNewLabel_4);

		textField_4 = new JTextField();
		panel_9.add(textField_4);
		textField_4.setColumns(15);

		JLabel lblNewLabel_6 = new JLabel("单价");
		panel_9.add(lblNewLabel_6);

		textField_6 = new JTextField();
		panel_9.add(textField_6);
		textField_6.setColumns(10);

		JLabel lblNewLabel_7 = new JLabel("描述");
		panel_9.add(lblNewLabel_7);

		textField_7 = new JTextField();
		panel_9.add(textField_7);
		textField_7.setColumns(20);

		JLabel lblNewLabel_8 = new JLabel("仓库预警数量");
		panel_9.add(lblNewLabel_8);

		textField_8 = new JTextField();
		panel_9.add(textField_8);
		textField_8.setColumns(10);

		JPanel panel_10 = new JPanel();
		panel_10.setBorder(new TitledBorder(null, "备品备件操作", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		FlowLayout flowLayout_1 = (FlowLayout) panel_10.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		panel_10.setBounds(10, 98, 841, 78);
		this.add(panel_10);

		JLabel lblNewLabel_9 = new JLabel("备品备件号");
		panel_10.add(lblNewLabel_9);

		textField_9 = new JTextField();
		panel_10.add(textField_9);
		textField_9.setColumns(10);

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
		Object[] columns = { "备品备件号", "备品备件名", "供应商", "规格", "单位", "库存", "价格", "描述", "预警数量" };
		// 初始化表格
		Table t1table = new Table(columns);
		JScrollPane JS = t1table.getJScrollPane();
		// 定义表格的控制权
		DefaultTableModel model = t1table.getModel();

		JS.setBounds(10, 186, 841, 217);
		this.add(JS);

		// 增加
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 连接数据库插入数据
				String id = textField.getText();
				String namez = textField_1.getText();
				String supplier = textField_2.getText();
				String mode = textField_3.getText();
				String depart = textField_4.getText();
				String price = textField_6.getText();
				String descrip = textField_7.getText();
				String expectnums = textField_8.getText();

				// 以上注册信息不能为空
				if (id.equals("")) {
					Tools.showMessage("增加信息", "备品备件号不能为空");
				} else if (namez.equals("")) {
					Tools.showMessage("增加信息", "备品备件名不能为空");
				} else if (supplier.equals("")) {
					Tools.showMessage("增加信息", "供应商不能为空");
				} else if (mode.equals("")) {
					Tools.showMessage("增加信息", "规格不能为空");
				} else if (depart.equals("")) {
					Tools.showMessage("增加信息", "单位不能为空");
				} else if (price.equals("")) {
					Tools.showMessage("增加信息", "单价不能为空");
				} else if (descrip.equals("")) {
					Tools.showMessage("增加信息", "描述不能为空");
				} else {
					// 以上条件都需要满足
					// 插入语句
					if (expectnums.equals("")) {
						expectnums = "-1";
					}
					String sql = "INSERT INTO d_beipin(id,namez,supplier,mode,depart,price,descrip,expectnums) VALUES (?,?,?,?,?,?,?,?)";
					int a = Mysql.update(sql, id, namez, supplier, mode, depart, price, descrip, expectnums);
					System.out.println(a);
					// 根据返回值判断状态
					if (a == -1) {
						Tools.showMessage("增加消息", "当前备品备件已存在，请重新增加");
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
				if (textField_9.getText().equals("")) {
					Tools.showMessage("系统提示", "请输入备品备件号");
				} else {
					String sql = "delete from d_beipin where id=?";
					int a = Mysql.update(sql, textField_9.getText());
					if (a == 1) {
						Tools.showMessage("系统提示", "删除成功");
					}
					if (a == 0) {
						Tools.showMessage("系统提示", "删除失败,备品备件不存在");
					}
					if (a == -1) {
						Tools.showMessage("系统提示", "删除失败,备品备件不存在");
					}
				}
			}

		});

		// 更改
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 连接数据库插入数据
				String id = textField.getText();
				String namez = textField_1.getText();
				String supplier = textField_2.getText();
				String mode = textField_3.getText();
				String depart = textField_4.getText();
				String price = textField_6.getText();
				String descrip = textField_7.getText();
				String expectnums = textField_8.getText();

				// 以上注册信息不能为空
				if (id.equals("")) {
					Tools.showMessage("更改信息", "备品备件号不能为空");
				} else if (namez.equals("")) {
					Tools.showMessage("更改信息", "备品备件名不能为空");
				} else if (supplier.equals("")) {
					Tools.showMessage("更改信息", "供应商不能为空");
				} else if (mode.equals("")) {
					Tools.showMessage("更改信息", "规格不能为空");
				} else if (depart.equals("")) {
					Tools.showMessage("更改信息", "单位不能为空");
				} else if (price.equals("")) {
					Tools.showMessage("更改信息", "单价不能为空");
				} else if (descrip.equals("")) {
					Tools.showMessage("更改信息", "描述不能为空");
				} else {
					// 以上条件都需要满足
					// 插入语句
					if (expectnums.equals("")) {
						expectnums = "-1";
					}
					String sql = "update d_beipin set id=?,namez=?,supplier=?,mode=?,depart=?,price=?,descrip=?,expectnums=? where id=?";
					int a = Mysql.update(sql, textField.getText(), textField_1.getText(), textField_2.getText(),
							textField_3.getText(), textField_4.getText(), textField_6.getText(), textField_7.getText(),
							textField_8.getText(), textField_9.getText());
					System.out.println(a);
					// 根据返回值判断状态
					if (a == -1) {
						Tools.showMessage("更改消息", "更改失败，请重新更改");
					}
					if (a == 1) {
						Tools.showMessage("更改消息", "更改成功");
					}
				}
			}

		});

		// 查询
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textField_9.getText().equals("")) {
					String sql = "select * from d_beipin";
					ResultSet rs = Mysql.query(sql, null);
					// 用一个工具自动插入到表格
					Tools.addTable(rs, model, 9);
				} else {
					String sql = "select * from d_beipin where id=?";
					ResultSet rs = Mysql.query(sql, textField_9.getText());
					// 用一个工具自动插入到表格
					ResultSet rs1 = Mysql.query(sql, textField_9.getText());
					Tools.addTable(rs, model, 9);
					try {
						if (rs1.next()) {
							textField.setText(rs1.getString(1));

							textField_1.setText(rs1.getString(2));
							textField_2.setText(rs1.getString(3));
							textField_3.setText(rs1.getString(4));
							textField_4.setText(rs1.getString(5));
							textField_6.setText(rs1.getString(7));
							textField_7.setText(rs1.getString(8));
							textField_8.setText(rs1.getString(9));

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
