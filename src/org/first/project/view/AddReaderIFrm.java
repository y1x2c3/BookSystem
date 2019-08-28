package org.first.project.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.LayoutStyle.ComponentPlacement;

import org.first.project.daoAndimpl.ReadersDaoImpl;
import org.first.project.model.Readers;
import org.first.project.util.StringUtil;

import com.alibaba.druid.stat.TableStat.Name;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddReaderIFrm extends JInternalFrame {
	private JTextField bookCardTxt;
	private JTextField NameTxt;
	private JTextField majorTxt;
	private JTextField departmentTxt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddReaderIFrm frame = new AddReaderIFrm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AddReaderIFrm() {
		setFrameIcon(new ImageIcon(AddReaderIFrm.class.getResource("/img/logo.png")));
		setTitle("图书管理系统-添加读者");
		setClosable(true);
		setBounds(100, 100, 295, 300);

		JLabel label = new JLabel("借书证号：");

		JLabel lblNewLabel = new JLabel("姓  名：");

		JLabel label_1 = new JLabel("性  别：");

		JRadioButton maleRadioButton = new JRadioButton("男");
		JRadioButton femaleRadioButton = new JRadioButton("女");
		
		ButtonGroup group = new ButtonGroup();
		group.add(maleRadioButton);
		group.add(femaleRadioButton);

		JLabel label_2 = new JLabel("专  业：");

		JLabel label_3 = new JLabel("学  院：");

		bookCardTxt = new JTextField();
		bookCardTxt.setColumns(10);

		NameTxt = new JTextField();
		NameTxt.setColumns(10);

		majorTxt = new JTextField();
		majorTxt.setColumns(10);

		departmentTxt = new JTextField();
		departmentTxt.setColumns(10);

		JButton button = new JButton("添加");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (StringUtil.isEmpty(bookCardTxt.getText()) || StringUtil.isEmpty(NameTxt.getText())
						|| StringUtil.isEmpty(majorTxt.getText()) || StringUtil.isEmpty(departmentTxt.getText())) {
					JOptionPane.showMessageDialog(null, "读者信息缺少,请添加完毕后再试!");
					return;
				}
				if(!StringUtil.isLong(bookCardTxt.getText())) {
					JOptionPane.showMessageDialog(null, "添加失败,非法的图书证!");
					return;
				}
				String sex = "";
				if (maleRadioButton.isSelected()) {
					sex = maleRadioButton.getText();
				} else if (femaleRadioButton.isSelected()) {
					sex = femaleRadioButton.getText();
				} else {
					sex = null;
				}
				if (StringUtil.isEmpty(sex)) {
					JOptionPane.showMessageDialog(null, "读者性别未选中!");
					return;
				}
				
				
				
				long bookCard = Long.parseLong(bookCardTxt.getText().trim());
				String name = NameTxt.getText().trim();
				String major = majorTxt.getText().trim();
				String department = departmentTxt.getText().trim();

				ReadersDaoImpl impl = new ReadersDaoImpl();
				Readers readers = new Readers(bookCard, name, sex, major, department);
				int ret = impl.addReader(readers);
				if (ret > 0)
					JOptionPane.showMessageDialog(null, "添加成功!新读者信息是:\n" + readers);
				else
					JOptionPane.showMessageDialog(null, "添加失败");

			}
		});
		button.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		button.setIcon(new ImageIcon(AddReaderIFrm.class.getResource("/img/添加3.png")));

		JButton button_1 = new JButton("重置");
		button_1.setIcon(new ImageIcon(AddReaderIFrm.class.getResource("/img/重置2.png")));
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bookCardTxt.setText("");
				NameTxt.setText("");
				majorTxt.setText("");
				departmentTxt.setText("");
			}
		});
		button_1.setFont(new Font("Dialog", Font.BOLD, 15));

		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addContainerGap(26, Short.MAX_VALUE)
				.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING).addComponent(label)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(lblNewLabel)
								.addComponent(label_3, Alignment.TRAILING).addComponent(label_2, Alignment.TRAILING)
								.addComponent(label_1, Alignment.TRAILING))
						.addComponent(button))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
										.addComponent(majorTxt, GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
										.addGroup(groupLayout.createSequentialGroup().addComponent(femaleRadioButton)
												.addGap(18).addComponent(maleRadioButton))
										.addComponent(NameTxt, GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
										.addComponent(departmentTxt, GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
										.addComponent(bookCardTxt))
								.addContainerGap(32, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup().addComponent(button_1).addGap(33)))));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addGap(38)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(bookCardTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(label))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
										.addComponent(NameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addGroup(groupLayout
												.createParallelGroup(Alignment.BASELINE).addComponent(femaleRadioButton)
												.addComponent(maleRadioButton).addComponent(label_1))
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
												.addComponent(majorTxt, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(label_2))
										.addGap(7)
										.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
												.addComponent(departmentTxt, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(label_3)))
								.addComponent(lblNewLabel))
						.addGap(27)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
								.addComponent(button))
						.addContainerGap(38, Short.MAX_VALUE)));
		getContentPane().setLayout(groupLayout);

	}
}
