package org.first.project.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import org.first.project.daoAndimpl.BookDaoImpl;
import org.first.project.model.Book;
import org.first.project.util.StringUtil;

import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Font;

public class AddBookIFrm extends JInternalFrame {
	/**
	 * 
	 */
	// private static final long serialVersionUID = 1L;
	private JTextField isbnTxt;
	private JTextField bookTypeTxt;
	private JTextField bookNameTxt;
	private JTextField AuthorTxt;
	private JTextField publishTxt;
	private JTextField publishDateTxt;
	private JTextField countTxt;
	private JTextField inStoreCount;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				AddBookIFrm frame = null;
				try {
					frame = new AddBookIFrm();
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
	public AddBookIFrm() {
		setFrameIcon(new ImageIcon(AddBookIFrm.class.getResource("/img/logo.png")));

		setClosable(true);
		setTitle("图书管理系统 - 新书入库");
		setBounds(100, 100, 483, 436);

		JLabel isbnLabel = new JLabel("ISBN书号：");

		JLabel bookTypeLabel = new JLabel("图书类别：");

		JLabel bookNameLabel = new JLabel("图书名称：");

		JLabel AuthorLabel = new JLabel("作  者：");

		JLabel publishLabel = new JLabel("出 版 社：");

		JLabel publishDateLabel = new JLabel("入库时间：");

		JLabel borrowAbleCountLabel = new JLabel("入库数量：");

		isbnTxt = new JTextField();
		isbnTxt.setColumns(10);

		bookTypeTxt = new JTextField();
		bookTypeTxt.setColumns(10);

		bookNameTxt = new JTextField();
		bookNameTxt.setColumns(10);

		AuthorTxt = new JTextField();
		AuthorTxt.setColumns(10);

		publishTxt = new JTextField();
		publishTxt.setColumns(10);

		publishDateTxt = new JTextField();
		publishDateTxt.setColumns(10);

		JLabel borrowAbleLabel = new JLabel("是否可借：");

		JRadioButton yesLabel = new JRadioButton("是");
		yesLabel.setSelected(true);

		JRadioButton noLabel = new JRadioButton("否");

		ButtonGroup group = new ButtonGroup();
		group.add(yesLabel);
		group.add(noLabel);

		JButton button = new JButton("入库");
		button.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		button.setIcon(new ImageIcon(AddBookIFrm.class.getResource("/img/入库.png")));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (StringUtil.isEmpty(isbnTxt.getText()) || StringUtil.isEmpty(bookNameTxt.getText())
						|| StringUtil.isEmpty(bookTypeTxt.getText()) || StringUtil.isEmpty(AuthorTxt.getText())
						|| StringUtil.isEmpty(publishTxt.getText()) || StringUtil.isEmpty(publishDateTxt.getText())) {
					JOptionPane.showMessageDialog(null, "缺少必要的入库信息,请填写完毕后再试!");
					return;
				}
				String isbn = isbnTxt.getText();
				String bName = bookNameTxt.getText();
				String bType = bookTypeTxt.getText();
				String author = AuthorTxt.getText();
				String publisher = publishTxt.getText();
				
				
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				Date inStoreTime;
				try {
					if(!StringUtil.isDate(publishDateTxt.getText())){
						JOptionPane.showMessageDialog(null, "日期格式错误!");
						return;
					}
					inStoreTime = (Date) format.parse(publishDateTxt.getText());
				} catch (ParseException e1) {
					JOptionPane.showMessageDialog(null, "转换异常!");
					e1.printStackTrace();
					return;
				}
				// 入库默认可借
				boolean isRent;
				if (yesLabel.isSelected() == true || (yesLabel.isSelected() == false && noLabel.isSelected() == false))
					isRent = true;
				else
					isRent = false;
				if(!StringUtil.isLong(inStoreCount.getText())) {
					JOptionPane.showMessageDialog(null, "您输入了非法的数量,请检查!");
					return;
				}
				int count = Integer.parseInt(inStoreCount.getText());
				if (count <= 0) {
					JOptionPane.showMessageDialog(null, "入库数量不能小于0!");
					return;
				}
				
				
				Book book = new Book(isbn, bName, bType, author, publisher, inStoreTime, isRent, count);
				BookDaoImpl bImpl = new BookDaoImpl();
				bImpl.addBook(book);
				JOptionPane.showMessageDialog(null, "入库成功！\n这本书是:\n" + book);
			}
		});

		JButton resetButton = new JButton("重置");
		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isbnTxt.setText("");
				bookNameTxt.setText("");
				bookTypeTxt.setText("");
				AuthorTxt.setText("");
				publishTxt.setText("");
				publishDateTxt.setText("");
				inStoreCount.setText("");
			}
		});
		resetButton.setIcon(new ImageIcon(AddBookIFrm.class.getResource("/img/重置3.png")));
		resetButton.setFont(new Font("Lucida Grande", Font.BOLD, 15));

		countTxt = new JTextField();
		countTxt.setEditable(false);
		countTxt.setColumns(10);

		inStoreCount = new JTextField();
		inStoreCount.setColumns(10);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(24)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(isbnLabel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(isbnTxt, GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(publishLabel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(button)
								.addComponent(publishTxt, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)))
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(bookNameLabel)
								.addComponent(AuthorLabel))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(AuthorTxt, GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
								.addComponent(bookNameTxt, GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE))))
					.addGap(43)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(resetButton)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(borrowAbleLabel)
									.addGap(18)
									.addComponent(yesLabel)
									.addPreferredGap(ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
									.addComponent(noLabel))
								.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
									.addComponent(publishDateLabel)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(publishDateTxt))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(borrowAbleCountLabel)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(inStoreCount))
								.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
									.addComponent(bookTypeLabel)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(bookTypeTxt, 104, 104, 104)))
							.addGap(33)
							.addComponent(countTxt, GroupLayout.PREFERRED_SIZE, 0, GroupLayout.PREFERRED_SIZE)))
					.addGap(200))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(29)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(publishDateLabel)
							.addComponent(publishDateTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(isbnLabel)
										.addComponent(isbnTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(bookTypeLabel)
										.addComponent(bookTypeTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addGap(18)
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(bookNameLabel)
										.addComponent(bookNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(borrowAbleCountLabel)
										.addComponent(inStoreCount, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addGap(18)
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addGroup(groupLayout.createSequentialGroup()
											.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
												.addComponent(AuthorLabel)
												.addComponent(AuthorTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
											.addPreferredGap(ComponentPlacement.RELATED))
										.addGroup(groupLayout.createSequentialGroup()
											.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
												.addComponent(borrowAbleLabel)
												.addComponent(noLabel)
												.addComponent(yesLabel))
											.addGap(3))))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(countTxt, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
									.addGap(10)))
							.addGap(28)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(publishLabel)
								.addComponent(publishTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addGap(98)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(resetButton, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
						.addComponent(button))
					.addContainerGap(95, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);

	}
}
