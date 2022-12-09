package com.stock.windows;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import com.manage.panl.BeiPinMan;
import com.manage.panl.CarRoom;
import com.manage.panl.InStock;
import com.manage.panl.OutStock;
import com.manage.panl.Stock;
import com.manage.panl.UserPanl;
import com.manage.panl.YuWarm;

public class ManageWindows {

	JFrame frame;

	/**
	 * Create the application.
	 */
	public ManageWindows() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("管理员");
		frame.setBounds(100, 100, 880, 479);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);

		UserPanl panel = new UserPanl();
		tabbedPane.addTab("用户管理", null, panel, null);
		panel.setLayout(null);

		CarRoom panel_1 = new CarRoom();
		tabbedPane.addTab("车间管理", null, panel_1, null);
		panel_1.setLayout(null);

		Stock panel_2 = new Stock();
		tabbedPane.addTab("仓库管理", null, panel_2, null);
		panel_2.setLayout(null);

		BeiPinMan panel_3 = new BeiPinMan();
		tabbedPane.addTab("备品备件管理", null, panel_3, null);
		panel_3.setLayout(null);

		YuWarm panel_8 = new YuWarm();
		tabbedPane.addTab("预警管理", null, panel_8, null);
		panel_8.setLayout(null);

		InStock panel_6 = new InStock();
		tabbedPane.addTab("入库审核", null, panel_6, null);
		panel_6.setLayout(null);

		OutStock panel_7 = new OutStock();
		tabbedPane.addTab("出库审核", null, panel_7, null);
		panel_7.setLayout(null);
	}
}
