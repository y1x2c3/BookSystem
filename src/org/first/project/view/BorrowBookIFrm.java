package org.first.project.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import org.first.project.daoAndimpl.BookDaoImpl;
import org.first.project.daoAndimpl.RentLogDaoimpl;
import org.first.project.model.Book;
import org.first.project.model.RentLog;
import org.first.project.util.StringUtil;
import org.omg.PortableServer.IMPLICIT_ACTIVATION_POLICY_ID;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SpinnerNumberModel;
import javax.swing.JTextArea;
import javax.swing.JSpinner;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;

public class BorrowBookIFrm extends JInternalFrame {
	private JTextField bookIdTxt;
	private JTextField ISBNTxt;
	private JTextField rentDateTxt;
	private JTextField returnDateTxt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BorrowBookIFrm frame = new BorrowBookIFrm();
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
	public BorrowBookIFrm() {
		setFrameIcon(new ImageIcon(BorrowBookIFrm.class.getResource("/img/logo.png")));
		setTitle("图书管理系统-图书借阅");
		setClosable(true);
		setBounds(100, 100, 456, 366);

		JPanel returDateTxt = new JPanel();
		returDateTxt.setBorder(
				new TitledBorder(null, "\u4E66\u7C4D\u501F\u9605", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(42)
					.addComponent(returDateTxt, GroupLayout.PREFERRED_SIZE, 349, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(49, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(19)
					.addComponent(returDateTxt, GroupLayout.PREFERRED_SIZE, 276, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(41, Short.MAX_VALUE))
		);

		JLabel bookIDLabel = new JLabel("借书证号：");
		bookIDLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 13));

		bookIdTxt = new JTextField();
		bookIdTxt.setColumns(10);

		JLabel ISBNLabel = new JLabel("I S B N:");

		ISBNTxt = new JTextField();
		ISBNTxt.setColumns(10);

		JLabel borrowAbleCountLabel = new JLabel("归还时间:");

		JButton updateButton = new JButton("借书");
		updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (StringUtil.isEmpty(bookIdTxt.getText()) || StringUtil.isEmpty(ISBNTxt.getText())
						|| StringUtil.isEmpty(rentDateTxt.getText()) || StringUtil.isEmpty(returnDateTxt.getText())) {
					JOptionPane.showMessageDialog(null, "缺少必要借阅录入信息,请添加完毕后再试!");
					return;
				}
				if(!StringUtil.isLong(bookIdTxt.getText())) {
					JOptionPane.showMessageDialog(null, "非法的图书证!");
					return;
				}
				if(!StringUtil.isDate(rentDateTxt.getText())||!StringUtil.isDate(returnDateTxt.getText())) {
					JOptionPane.showMessageDialog(null, "日期格式错误,请检查后再试!");
					return;
				}
				
				long bookCard = Long.parseLong(bookIdTxt.getText());
				String isbn = ISBNTxt.getText();
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				Date rentDate = null;
				Date returnDate = null;
				try {
					rentDate = (Date) format.parse(rentDateTxt.getText());
					returnDate = (Date) format.parse(returnDateTxt.getText());
				} catch (ParseException e1) {
					JOptionPane.showMessageDialog(null, "转换异常");
					e1.printStackTrace();
					return;
				}
				RentLog rentLog = new RentLog(bookCard, isbn, rentDate, returnDate);
				RentLogDaoimpl rimpl = new RentLogDaoimpl();
				// 判断是否有这本书/这个人
				if (!rimpl.isExistBook(isbn)) {
					JOptionPane.showMessageDialog(null, "亲,没有这个这本图书,请确定后再试!");
					return;
				}
				if (!rimpl.isExistReader(bookCard)) {
					JOptionPane.showMessageDialog(null, "亲,没有这个人哦,请确定后再试!");
					return;
				}
				int ret = rimpl.addRentLog(rentLog);
				// 借书的同时应该减少库存
				if (ret > 0) {
					BookDaoImpl bmpl = new BookDaoImpl();
					Book book = bmpl.getBookByID(isbn);
					int count = book.getCount();
					count--;
					book.setCount(count);
					bmpl.updateBook(book);
					JOptionPane.showMessageDialog(null, "学号:" + bookCard + "成功借阅一本书!");
				} else {
					JOptionPane.showMessageDialog(null, "借阅失败!");
				}

			}
		});
		updateButton.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		updateButton.setIcon(new ImageIcon(BorrowBookIFrm.class.getResource("/img/借书.png")));

		JLabel label = new JLabel("借书时间：");

		rentDateTxt = new JTextField();
		rentDateTxt.setColumns(10);

		returnDateTxt = new JTextField();
		returnDateTxt.setColumns(10);

		JLabel lblNewLabel = new JLabel("在校学生:限定每次只能借一本书");

		JLabel label_1 = new JLabel("在校教师:限定每次只能借三本书");
		GroupLayout gl_returDateTxt = new GroupLayout(returDateTxt);
		gl_returDateTxt.setHorizontalGroup(
			gl_returDateTxt.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_returDateTxt.createSequentialGroup()
					.addGroup(gl_returDateTxt.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_returDateTxt.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_returDateTxt.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_returDateTxt.createSequentialGroup()
									.addComponent(bookIDLabel)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(bookIdTxt, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_returDateTxt.createSequentialGroup()
									.addComponent(label, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(rentDateTxt, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)))
							.addGap(18)
							.addGroup(gl_returDateTxt.createParallelGroup(Alignment.LEADING)
								.addComponent(ISBNLabel)
								.addComponent(borrowAbleCountLabel))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_returDateTxt.createParallelGroup(Alignment.LEADING)
								.addComponent(returnDateTxt, GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
								.addComponent(ISBNTxt, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_returDateTxt.createSequentialGroup()
							.addGap(120)
							.addComponent(updateButton))
						.addGroup(gl_returDateTxt.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE))
						.addGroup(gl_returDateTxt.createSequentialGroup()
							.addContainerGap()
							.addComponent(label_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
					.addGap(14))
		);
		gl_returDateTxt.setVerticalGroup(
			gl_returDateTxt.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_returDateTxt.createSequentialGroup()
					.addGap(30)
					.addGroup(gl_returDateTxt.createParallelGroup(Alignment.BASELINE)
						.addComponent(bookIDLabel)
						.addComponent(ISBNLabel)
						.addComponent(ISBNTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(bookIdTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(41)
					.addGroup(gl_returDateTxt.createParallelGroup(Alignment.BASELINE)
						.addComponent(rentDateTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(borrowAbleCountLabel)
						.addComponent(returnDateTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label))
					.addGap(35)
					.addComponent(lblNewLabel)
					.addGap(18)
					.addComponent(label_1)
					.addPreferredGap(ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
					.addComponent(updateButton, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
		);
		returDateTxt.setLayout(gl_returDateTxt);
		getContentPane().setLayout(groupLayout);

	}
}
