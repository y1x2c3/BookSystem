package org.first.project.view;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JInternalFrame;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;

import org.first.project.util.StringUtil;
import org.first.project.daoAndimpl.BookDaoImpl;
import org.first.project.model.Book;
import org.first.project.util.BookInfoTableModel;
import org.first.project.util.Dbutil;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JPanel;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class SearchBookIFrm extends JInternalFrame {
	private JTextField searchISBNTxt;
	private JTable bookTypeTable;
	private JTextField isbnTxt;
	private JTextField bookTypeTxt;
	private JTextField bookNameTxt;
	private JTextField authorTxt;
	private JTextField publishTxt;
	private JTextField instoreTimeTxt;
	private JTextField bookCountTxt;
	private JLabel label_4;
	private JLabel label_5;
	private JLabel label_6;
	private JLabel label_7;
	private JTextField rentAbleTxt;
	private Dbutil dbUtil = new Dbutil();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchBookIFrm frame = new SearchBookIFrm();
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
	public SearchBookIFrm() {
		setFrameIcon(new ImageIcon(SearchBookIFrm.class.getResource("/img/logo.png")));
		setClosable(true);
		setTitle("图书管理系统-图书管理");
		setBounds(100, 100, 620, 506);

		searchISBNTxt = new JTextField();
		searchISBNTxt.setToolTipText("请输入图书的各种信息，如书名、作者、出版社、ISBN书号等，支持模糊查询");
		searchISBNTxt.setColumns(10);

		JButton searchISBNButton = new JButton("ISBN查询");
		searchISBNButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String isbn = searchISBNTxt.getText().trim();
				if (StringUtil.isEmpty(isbn)) {
					bookTypeTable.setModel(new DefaultTableModel(new Object[][] {}, new String[] {}){ });
					reSetTxt();
					JOptionPane.showMessageDialog(null, "查询内容不能为空!");
					return;
				}
				BookDaoImpl bImpl = new BookDaoImpl();
				Book book = bImpl.getBookByID(isbn);
				if (book == null) {
					bookTypeTable.setModel(new DefaultTableModel(new Object[][] {}, new String[] {}){ });
					reSetTxt();
					JOptionPane.showMessageDialog(null, "没有您要查找的图书!请确认是否输入了错误的isbn");
					return;
				}else {
					List<Book> list = new ArrayList<Book>();
					list.add(book);
					BookInfoTableModel bModel = new BookInfoTableModel(list);
					bookTypeTable.setModel(bModel);
				}

			}
		});
		searchISBNButton.setIcon(new ImageIcon(SearchBookIFrm.class.getResource("/img/查询.png")));
		searchISBNButton.setFont(new Font("Lucida Grande", Font.BOLD, 13));

		JScrollPane scrollPane = new JScrollPane();

		JButton updateButton = new JButton("修改");
		updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bookTypeUpdateActionEvent(e);
			}
		});
		updateButton.setIcon(new ImageIcon(SearchBookIFrm.class.getResource("/img/修改.png")));
		updateButton.setFont(new Font("Lucida Grande", Font.BOLD, 13));

		JButton deleteButton = new JButton("删除");
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bookTypeDeleteActionEvent(e);
			}
		});
		deleteButton.setIcon(new ImageIcon(SearchBookIFrm.class.getResource("/img/删 除.png")));
		deleteButton.setFont(new Font("Lucida Grande", Font.BOLD, 13));

		JPanel panel = new JPanel();
		panel.setBorder(
				new TitledBorder(null, "\u4E66\u7C4D\u7BA1\u7406", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		JButton searchBookNameButton = new JButton("查询全部");
		searchBookNameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BookDaoImpl impl = new BookDaoImpl();
				List<Book> list = impl.getAllBook();
				if (list == null) {
					bookTypeTable.setModel(new DefaultTableModel(new Object[][] {}, new String[] {}){ });
					reSetTxt();
					JOptionPane.showMessageDialog(null, "图书馆里空空如也,请添加图书后在试");
					return;
				}
				BookInfoTableModel bModel = new BookInfoTableModel(list);
				bookTypeTable.setModel(bModel);

			}
		});
		searchBookNameButton.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.TRAILING).addGroup(groupLayout
				.createSequentialGroup().addContainerGap()
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 584, GroupLayout.PREFERRED_SIZE)
								.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
								.addComponent(searchISBNTxt, GroupLayout.PREFERRED_SIZE, 161,
										GroupLayout.PREFERRED_SIZE)
								.addGap(18).addComponent(searchISBNButton)
								.addPreferredGap(ComponentPlacement.RELATED, 175, Short.MAX_VALUE)
								.addComponent(searchBookNameButton).addGap(40))))
				.addGroup(groupLayout.createSequentialGroup().addGap(17)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 563, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(24, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup().addContainerGap(367, Short.MAX_VALUE)
						.addComponent(updateButton).addGap(18).addComponent(deleteButton).addGap(57)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addGap(29)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(searchISBNTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(searchBookNameButton, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
						.addComponent(searchISBNButton))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE).addGap(18)
				.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addGap(18).addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(deleteButton)
						.addComponent(updateButton))
				.addContainerGap(65, Short.MAX_VALUE)));

		JLabel lblIsbn = new JLabel("I S B N：");
		lblIsbn.setFont(new Font("Lucida Grande", Font.PLAIN, 13));

		JLabel label_1 = new JLabel("图书类别：");

		JLabel label_2 = new JLabel("图书名称：");

		JLabel label_3 = new JLabel("作    者：");

		isbnTxt = new JTextField();
		isbnTxt.setColumns(10);

		bookTypeTxt = new JTextField();
		bookTypeTxt.setColumns(10);

		bookNameTxt = new JTextField();
		bookNameTxt.setColumns(10);

		authorTxt = new JTextField();
		authorTxt.setColumns(10);

		publishTxt = new JTextField();
		publishTxt.setColumns(10);

		instoreTimeTxt = new JTextField();
		instoreTimeTxt.setColumns(10);

		bookCountTxt = new JTextField();
		bookCountTxt.setColumns(10);

		label_4 = new JLabel("出版社:");

		label_5 = new JLabel("入库时间：");

		label_6 = new JLabel("馆藏数量：");

		label_7 = new JLabel("是否可借：");

		rentAbleTxt = new JTextField();
		rentAbleTxt.setColumns(10);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addContainerGap()
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblIsbn, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
								.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
								.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
								.addComponent(label_3, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(isbnTxt, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
								.addComponent(bookTypeTxt, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
								.addComponent(bookNameTxt, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
								.addComponent(authorTxt, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED, 101, Short.MAX_VALUE)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
										.addComponent(label_7, GroupLayout.PREFERRED_SIZE, 65,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
										.addComponent(rentAbleTxt, GroupLayout.PREFERRED_SIZE, 130,
												GroupLayout.PREFERRED_SIZE))
								.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
										.addComponent(label_6, GroupLayout.PREFERRED_SIZE, 65,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
										.addComponent(bookCountTxt, GroupLayout.PREFERRED_SIZE, 130,
												GroupLayout.PREFERRED_SIZE))
								.addGroup(Alignment.TRAILING,
										gl_panel.createSequentialGroup()
												.addComponent(label_5, GroupLayout.PREFERRED_SIZE, 65,
														GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
												.addComponent(instoreTimeTxt, GroupLayout.PREFERRED_SIZE, 130,
														GroupLayout.PREFERRED_SIZE))
								.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
										.addComponent(label_4, GroupLayout.PREFERRED_SIZE, 52,
												GroupLayout.PREFERRED_SIZE)
										.addGap(33).addComponent(publishTxt, GroupLayout.PREFERRED_SIZE, 130,
												GroupLayout.PREFERRED_SIZE)))
						.addContainerGap()));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addGap(20)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(lblIsbn)
								.addComponent(isbnTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(publishTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(label_4))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(label_1)
								.addComponent(bookTypeTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(instoreTimeTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(label_5))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING).addComponent(label_2)
								.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
										.addComponent(bookNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(bookCountTxt, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(label_6)))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(label_3)
										.addComponent(authorTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(label_7)
										.addComponent(rentAbleTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)))
						.addGap(26)));
		panel.setLayout(gl_panel);

		bookTypeTable = new JTable();
		bookTypeTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				bookTypeTableMousePressed(e);
			}
		});
		scrollPane.setViewportView(bookTypeTable);
		getContentPane().setLayout(groupLayout);
		
		DefaultTableCellRenderer r=new DefaultTableCellRenderer();   
		r.setHorizontalAlignment(SwingConstants.CENTER);  
		bookTypeTable.setDefaultRenderer(Object.class,r);
	}

	/**
	 * 修改
	 * 
	 * @param e
	 */
	private void bookTypeUpdateActionEvent(ActionEvent e) {
		// 没有选中
		if (bookTypeTable.getSelectedColumn() == -1 && bookTypeTable.getSelectedRow() == -1) {
			JOptionPane.showMessageDialog(null, "请选择您要操作的单元格!");
			return;
		}
		
		String isbn = isbnTxt.getText();
		String bookName = bookNameTxt.getText();
		String bookType = bookTypeTxt.getText();
		String author = authorTxt.getText();
		String publish = publishTxt.getText();
		Date inStoreTime = null;
		//输入合法性
		if(!StringUtil.isDate(instoreTimeTxt.getText())) {
			JOptionPane.showMessageDialog(null, "日期格式不对!");
			return;
		}
		if(!StringUtil.isLong(bookCountTxt.getText())) {	
			JOptionPane.showMessageDialog(null, "非法的数量!");
			return;
		}
		
		// 格式化日期
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			inStoreTime =(Date)format.parse(instoreTimeTxt.getText());
		} catch (ParseException e1) {
			JOptionPane.showMessageDialog(null, "日期格式转换错误!");
			e1.printStackTrace();
			return;
		}
		String strDate = format.format(inStoreTime);
		boolean rentAble = Boolean.parseBoolean(rentAbleTxt.getText());
		int bookCount = Integer.parseInt(bookCountTxt.getText());
		if (StringUtil.isEmpty(isbn) || StringUtil.isEmpty(bookName) || StringUtil.isEmpty(bookType)
				|| StringUtil.isEmpty(author) || StringUtil.isEmpty(publish) || StringUtil.isEmpty(strDate)) {
			JOptionPane.showMessageDialog(null, "请选择您要修改的数据,点击表格某行即可!");
			return;
		}
		
		
		Book book = new Book(isbn, bookName, bookType, author, publish, inStoreTime, rentAble, bookCount);
		BookDaoImpl impl = new BookDaoImpl();
		int ret = impl.updateBook(book);
		List<Book> list = new ArrayList<Book>();
		if (ret > 0) {
			list.add(book);
			JOptionPane.showMessageDialog(null, "修改成功!");	
		}else {
			list=impl.getAllBook();
			JOptionPane.showMessageDialog(null, "没有做任何修改");
		}
			
		BookInfoTableModel bModel = new BookInfoTableModel(list);
		bookTypeTable.setModel(bModel);

	}

	/**
	 * 表格行点击处理
	 * 
	 * @param e
	 */
	private void bookTypeTableMousePressed(MouseEvent e) {
		// TODO 自动生成的方法存根
		int row = bookTypeTable.getSelectedRow();
		isbnTxt.setText((String) bookTypeTable.getValueAt(row, 0));
		bookNameTxt.setText((String) bookTypeTable.getValueAt(row, 1));
		bookTypeTxt.setText((String) bookTypeTable.getValueAt(row, 2));
		authorTxt.setText((String) bookTypeTable.getValueAt(row, 3));
		publishTxt.setText((String) bookTypeTable.getValueAt(row, 4));

		instoreTimeTxt.setText(bookTypeTable.getValueAt(row, 5).toString());
		rentAbleTxt.setText(bookTypeTable.getValueAt(row, 6).toString());
		bookCountTxt.setText(bookTypeTable.getValueAt(row, 7).toString());
	}
	private void reSetTxt(){
		isbnTxt.setText("");
		bookNameTxt.setText("");
		bookTypeTxt.setText("");
		authorTxt.setText("");
		publishTxt.setText("");
		instoreTimeTxt.setText("");
		rentAbleTxt.setText("");
		bookCountTxt.setText("");
	}
	/**
	 * 图书类别删除事件处理
	 * 
	 * @param e
	 */
	private void bookTypeDeleteActionEvent(ActionEvent evt) {
		// 没有选中
		if (bookTypeTable.getSelectedColumn() == -1 && bookTypeTable.getSelectedRow() == -1) {
			JOptionPane.showMessageDialog(null, "请选择您要操作的单元格!");
			return;
		}
		String isbn = isbnTxt.getText();
		if (StringUtil.isEmpty(isbn)) {
			JOptionPane.showMessageDialog(null, "请选择要删除的记录");
			return;
		}
		
		
		int n = JOptionPane.showConfirmDialog(null, "确定要删除该记录吗？");
		if (n == 0) {
			BookDaoImpl impl = new BookDaoImpl();
			if (impl.isExistBook(isbn) == false) {
				JOptionPane.showMessageDialog(null, "亲没有这本书!请确定后再试");
				return;
			}
			int ret = impl.deleteBookByID(isbn);
			if (ret > 0)
				JOptionPane.showMessageDialog(null, "删除成功");
			else
				JOptionPane.showMessageDialog(null, "删除失败");
		}
	}
	

}
