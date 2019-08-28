package org.first.project.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;

import org.first.project.daoAndimpl.BookDaoImpl;
import org.first.project.daoAndimpl.ReadersDaoImpl;
import org.first.project.daoAndimpl.RentLogDaoimpl;
import org.first.project.model.Book;
import org.first.project.model.Readers;
import org.first.project.model.RentLog;
import org.first.project.util.RentLogTableModel;
import org.first.project.util.StringUtil;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;

public class ReturnBookIFrm extends JInternalFrame {
	private JTextField returnBookIDTxt;
	private JTextField ISBNTxt;
	private JTable returnBookTable;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReturnBookIFrm frame = new ReturnBookIFrm();
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
	public ReturnBookIFrm() {
		setFrameIcon(new ImageIcon(ReturnBookIFrm.class.getResource("/img/logo.png")));
		setTitle("图书管理系统-归还图书");
		setClosable(true);
		setBounds(100, 100, 546, 354);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "\u4E66\u7C4D\u5F52\u8FD8",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));

		JLabel label_1 = new JLabel("图书证：");
		label_1.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		
		JLabel lblNewLabel = new JLabel("是否损坏:");
		
		JRadioButton yesRadio = new JRadioButton("是");
		JRadioButton noRadio = new JRadioButton("否");
		noRadio.setSelected(true);
		ButtonGroup group = new ButtonGroup();
		group.add(yesRadio);
		group.add(noRadio);
		
		returnBookIDTxt = new JTextField();
		returnBookIDTxt.setColumns(10);

		JLabel label_3 = new JLabel("ISBN:");

		JButton returnBookButton = new JButton("还书");
		returnBookButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (StringUtil.isEmpty(ISBNTxt.getText()) || StringUtil.isEmpty(returnBookIDTxt.getText())) {
					JOptionPane.showMessageDialog(null, "图书证和isbn二者缺一不可!");
					returnBookTable.setModel(new DefaultTableModel(new Object[][] {}, new String[] {}){ });
					return;
				}
				if (!StringUtil.isLong(returnBookIDTxt.getText())) {
					JOptionPane.showMessageDialog(null, "无效的图书证!");
					returnBookTable.setModel(new DefaultTableModel(new Object[][] {}, new String[] {}){ });
					return;
				}
				String isbn = ISBNTxt.getText().trim();
				long bookCard = Long.parseLong(returnBookIDTxt.getText());
	
				RentLogDaoimpl rimpl = new RentLogDaoimpl();
				if(!rimpl.isExistBook(isbn)) {
					JOptionPane.showMessageDialog(null, "没有您要归还的图书");
					returnBookTable.setModel(new DefaultTableModel(new Object[][] {}, new String[] {}){ });
					return;
				}
				if(!rimpl.isExistReader(bookCard)) {
					JOptionPane.showMessageDialog(null, "找不到要归还的读者!");
					returnBookTable.setModel(new DefaultTableModel(new Object[][] {}, new String[] {}){ });
					return;
				}
				
				int n = JOptionPane.showConfirmDialog(null, "您确定要归还该图书吗?");
				if (n == 0) {
					int ret = rimpl.deleteRentLog(isbn, bookCard);
					if (ret > 0) {
						BookDaoImpl bmpl = new BookDaoImpl();
						Book book = bmpl.getBookByID(isbn);
						int count = book.getCount();
						count++;
						book.setCount(count);
						//判断是否有损坏
						String isDamage="否";
						if(yesRadio.isSelected()) {
							isDamage="是";
							book.setIsRent(false);
						}
						else
							book.setIsRent(true);
						
						
						bmpl.updateBook(book);
						// 判断是否逾期
						RentLog getDate = rimpl.getRentLogByIsbnAndBookCard(isbn, bookCard);
						Date shouldDate = getDate.getReturnDate();
						long currentDate = System.currentTimeMillis();
						Date date = new Date(currentDate);
						
						if (shouldDate.compareTo(date) == 1 || shouldDate.compareTo(date) == 0) {
							JOptionPane.showMessageDialog(null, "系统提示:学号为" + bookCard + "归还了一本书[备注:按时   是否损坏:"+isDamage+"]");
						} else
							JOptionPane.showMessageDialog(null, "系统提示:学号为" + bookCard + "归还了一本书\n[已逾期,需要缴纳一定的滞纳金!]");
						List<RentLog> list=rimpl.getAllRentLog();
						RentLogTableModel rmLogTableModel=new RentLogTableModel(list);
						returnBookTable.setModel(rmLogTableModel);
					} else
						JOptionPane.showMessageDialog(null, "归还失败");
				}
			}
		});
		returnBookButton.setIcon(new ImageIcon(ReturnBookIFrm.class.getResource("/img/还书.png")));
		returnBookButton.setFont(new Font("Lucida Grande", Font.BOLD, 15));

		ISBNTxt = new JTextField();
		ISBNTxt.setColumns(10);

		JButton reInfoButton = new JButton("借阅一览");
		reInfoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RentLogDaoimpl impl = new RentLogDaoimpl();
				List<RentLog> list = impl.getAllRentLog();
				if (list == null) {
					returnBookTable.setModel(new DefaultTableModel(new Object[][] {}, new String[] {}){ });
					JOptionPane.showMessageDialog(null, "暂无借阅图书信息");
					return;
				}
				RentLogTableModel rModel = new RentLogTableModel(list);
				returnBookTable.setModel(rModel);
			}
		});
		reInfoButton.setIcon(new ImageIcon(ReturnBookIFrm.class.getResource("/img/查询.png")));
		reInfoButton.setFont(new Font("Lucida Grande", Font.BOLD, 15));

		JButton button = new JButton("查询");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (StringUtil.isEmpty(ISBNTxt.getText()) || StringUtil.isEmpty(returnBookIDTxt.getText())) {
					returnBookTable.setModel(new DefaultTableModel(new Object[][] {}, new String[] {}){ });
					JOptionPane.showMessageDialog(null, "图书证和isbn二者缺一不可!");
					return;
				}
				if(!StringUtil.isLong(returnBookIDTxt.getText())) {
					returnBookTable.setModel(new DefaultTableModel(new Object[][] {}, new String[] {}){ });
					JOptionPane.showMessageDialog(null, "无效的图书证");
					return;
				}
				String isbn = ISBNTxt.getText().trim();
				long bookCard = Long.parseLong(returnBookIDTxt.getText());

				RentLogDaoimpl rimpl = new RentLogDaoimpl();
				RentLog log = rimpl.getRentLogByIsbnAndBookCard(isbn, bookCard);
				List<RentLog> listRentLog = new ArrayList<RentLog>();
				listRentLog.add(log);
				// 判断是否有这本书/这个人
				if (!rimpl.isExistBook(isbn)) {
					returnBookTable.setModel(new DefaultTableModel(new Object[][] {}, new String[] {}){ });
					JOptionPane.showMessageDialog(null, "亲,没有这个这本图书,请确定是否输入了正确的isbn!");
					return;
				}
				if (!rimpl.isExistReader(bookCard)) {
					returnBookTable.setModel(new DefaultTableModel(new Object[][] {}, new String[] {}){ });
					JOptionPane.showMessageDialog(null, "亲,没有这个人哦,请确定是否输入了正确的图书证!");
					return;
				}
				if (log == null) {
					returnBookTable.setModel(new DefaultTableModel(new Object[][] {}, new String[] {}){ });
					JOptionPane.showMessageDialog(null, "暂时还未有人来借阅书籍");
					return;
				}
				RentLogTableModel rModel = new RentLogTableModel(listRentLog);
				returnBookTable.setModel(rModel);
			}
		});
		button.setIcon(new ImageIcon(ReturnBookIFrm.class.getResource("/img/查询 (1).png")));
		button.setFont(new Font("Dialog", Font.BOLD, 15));
		
	
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(returnBookIDTxt, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
							.addGap(33)
							.addComponent(label_3)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(ISBNTxt, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
							.addGap(38)
							.addComponent(lblNewLabel)
							.addGap(3)
							.addComponent(yesRadio, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(noRadio, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addContainerGap(5, Short.MAX_VALUE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(button)
							.addGap(18)
							.addComponent(returnBookButton)
							.addPreferredGap(ComponentPlacement.RELATED, 177, Short.MAX_VALUE)
							.addComponent(reInfoButton)
							.addGap(22))))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_1)
						.addComponent(ISBNTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(returnBookIDTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_3)
						.addComponent(lblNewLabel)
						.addComponent(yesRadio)
						.addComponent(noRadio))
					.addGap(23)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(button, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
						.addComponent(returnBookButton, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
						.addComponent(reInfoButton))
					.addGap(26))
		);
		panel.setLayout(gl_panel);

		JScrollPane scrollPane = new JScrollPane();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 511, Short.MAX_VALUE)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 521, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addGap(21)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 130, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)
					.addGap(24))
		);

		returnBookTable = new JTable();

		returnBookTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

			}
		});
	
		scrollPane.setViewportView(returnBookTable);
		getContentPane().setLayout(groupLayout);
		
		DefaultTableCellRenderer r=new DefaultTableCellRenderer();   
		r.setHorizontalAlignment(SwingConstants.CENTER);  
		returnBookTable.setDefaultRenderer(Object.class,r);
	}
}
