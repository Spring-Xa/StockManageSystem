package com.stock.windows;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import com.user.panl.InStock;
import com.user.panl.OutStock;

public class UserWindows {

	JFrame frame;

	/**
	 * Create the application.
	 */
	public UserWindows() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("用户");
		frame.setBounds(100, 100, 880, 479);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);

		// 出库与入库只能查看自己的和审核状态
		InStock panel_4 = new InStock();
		tabbedPane.addTab("备品备件入库", null, panel_4, null);
		panel_4.setLayout(null);

		OutStock panel_5 = new OutStock();
		tabbedPane.addTab("备品备件出库", null, panel_5, null);
		panel_5.setLayout(null);

	}
}
