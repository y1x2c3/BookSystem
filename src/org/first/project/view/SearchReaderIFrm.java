package org.first.project.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;

import org.first.project.daoAndimpl.ReadersDaoImpl;
import org.first.project.daoAndimpl.RentLogDaoimpl;
import org.first.project.model.Readers;
import org.first.project.model.RentLog;
import org.first.project.util.ReaderInfoTabelModel;
import org.first.project.util.ShowNotReturnReaderTableModel;
import org.first.project.util.StringUtil;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SearchReaderIFrm extends JInternalFrame {
	private JTextField bookCardTxt;
	private JTextField readerNameTxt;
	private JTable readerTable;
	private JTable readerInfoTable;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchReaderIFrm frame = new SearchReaderIFrm();
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
	public SearchReaderIFrm() {
		setFrameIcon(new ImageIcon(SearchReaderIFrm.class.getResource("/img/logo.png")));
		setTitle("图书管理系统-读者管理");
		setClosable(true);
		setBounds(100, 100, 665, 481);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "\u67E5\u8BE2\u6761\u4EF6",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));

		JLabel bookCardLabel = new JLabel("学号(图书证)：");

		bookCardTxt = new JTextField();
		bookCardTxt.setColumns(10);

		JLabel readerNameLabel_1 = new JLabel("姓  名：");

		readerNameTxt = new JTextField();
		readerNameTxt.setColumns(10);

		JButton searchButton = new JButton("查询");
		searchButton.addActionListener(new ActionListener() {
			@SuppressWarnings("serial")
			public void actionPerformed(ActionEvent e) {
				if (StringUtil.isEmpty(bookCardTxt.getText()) || StringUtil.isEmpty(readerNameTxt.getText())) {
					readerInfoTable.setModel(new DefaultTableModel(new Object[][] {}, new String[] {}) {
					});
					readerTable.setModel(new DefaultTableModel(new Object[][] {}, new String[] {}){ });
					JOptionPane.showMessageDialog(null, "查询的图书证或读者名不能为空!");
					return;
				}
				if (!StringUtil.isLong(bookCardTxt.getText())) {
					readerInfoTable.setModel(new DefaultTableModel(new Object[][] {}, new String[] {}) {
					});
					readerTable.setModel(new DefaultTableModel(new Object[][] {}, new String[] {}){ });
					JOptionPane.showMessageDialog(null, "无效的图书证");
					return;
				}
				long bookCard = Long.parseLong(bookCardTxt.getText().trim());
				String name = readerNameTxt.getText().trim();
				
				ReadersDaoImpl impl = new ReadersDaoImpl();
				List<Readers> list = new ArrayList<Readers>();
				
				RentLogDaoimpl rlimpl=new RentLogDaoimpl();
				List<RentLog> list2=new ArrayList<RentLog>();
				
				Readers readers = impl.getReader(bookCard);
				
				if(readers==null||!name.equals(readers.getName())) {
					readerInfoTable.setModel(new DefaultTableModel(new Object[][] {}, new String[] {}) {
					});
					readerTable.setModel(new DefaultTableModel(new Object[][] {}, new String[] {}){ });
					JOptionPane.showMessageDialog(null, "不存在该读者,请确认图书证或名字是否有误?");
					return;
				}
				list.add(readers);
				ReaderInfoTabelModel rModel = new ReaderInfoTabelModel(list);
				readerInfoTable.setModel(rModel);
				if(rlimpl.isExistReader(bookCard)) {
					list2=rlimpl.getRentLogByBookCard(bookCard);
					if(list2!=null) {
						ShowNotReturnReaderTableModel snModel=new ShowNotReturnReaderTableModel(list2);
						readerTable.setModel(snModel);
					}else {
						readerTable.setModel(new DefaultTableModel(new Object[][] {}, new String[] {}){ });
					}
					
				}else {
					readerTable.setModel(new DefaultTableModel(new Object[][] {}, new String[] {}){ });
				}
				

			}
		});
		searchButton.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		searchButton.setIcon(new ImageIcon(SearchReaderIFrm.class.getResource("/img/查询2.png")));

		JButton button_2 = new JButton("查询全部");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReadersDaoImpl readersDaoImpl = new ReadersDaoImpl();
				List<Readers> list = readersDaoImpl.getAllReader();
				
				RentLogDaoimpl rmpl=new RentLogDaoimpl();
				List<RentLog> list3=rmpl.getAllRentLog();
				
				if (list == null) {
					readerInfoTable.setModel(new DefaultTableModel(new Object[][] {}, new String[] {}) {
					});
					readerTable.setModel(new DefaultTableModel(new Object[][] {}, new String[] {}){ });
					JOptionPane.showMessageDialog(null, "读者列表空空如也,请添加后再来查看吧!");
					return;
				}
				ShowNotReturnReaderTableModel tableModel=new ShowNotReturnReaderTableModel(list3);
				readerTable.setModel(tableModel);
				
				ReaderInfoTabelModel rModel = new ReaderInfoTabelModel(list);
				readerInfoTable.setModel(rModel);

			}
		});
		button_2.setFont(new Font("Dialog", Font.BOLD, 15));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addGap(19).addComponent(bookCardLabel)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(bookCardTxt, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
						.addGap(18).addComponent(readerNameLabel_1).addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(readerNameTxt, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
						.addGap(18).addComponent(searchButton)
						.addPreferredGap(ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
						.addComponent(button_2, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
						.addContainerGap()));
		gl_panel.setVerticalGroup(
				gl_panel.createParallelGroup(Alignment.TRAILING).addGroup(gl_panel
						.createSequentialGroup().addContainerGap(GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(bookCardLabel)
								.addComponent(bookCardTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(readerNameLabel_1)
								.addComponent(readerNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(searchButton)
								.addComponent(button_2, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
						.addGap(16)));
		panel.setLayout(gl_panel);

		JScrollPane scrollPane = new JScrollPane();

		JLabel label = new JLabel("未归还图书：");
		label.setFont(new Font("Lucida Grande", Font.PLAIN, 13));

		JScrollPane readerInfoPane = new JScrollPane();

		JButton button = new JButton("修改");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 没有选中
				if (readerInfoTable.getSelectedColumn() == -1 && readerInfoTable.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null, "请选择您要操作的单元格!");
					return;
				}
				// 保存更改
				if (readerInfoTable.isEditing()) {
					TableCellEditor editor = readerInfoTable.getCellEditor();
					if (editor != null)
						editor.stopCellEditing();
				}
				int row = readerInfoTable.getSelectedRow();
				long bookCard =(long) readerInfoTable.getValueAt(row, 0);
				String name = (String) readerInfoTable.getValueAt(row, 1);
				String sex = (String) readerInfoTable.getValueAt(row, 2);
				String major = (String) readerInfoTable.getValueAt(row, 3);
				String department = (String) readerInfoTable.getValueAt(row, 4);
				if (bookCard==0|| StringUtil.isEmpty(name) || StringUtil.isEmpty(sex)
						|| StringUtil.isEmpty(major) || StringUtil.isEmpty(department)) {
					readerInfoTable.setModel(new DefaultTableModel(new Object[][] {}, new String[] {}) {
					});
					readerTable.setModel(new DefaultTableModel(new Object[][] {}, new String[] {}){ });
					JOptionPane.showMessageDialog(null, "读者信息缺少,请添加完毕后再试!");
					return;
				}

				
				Readers readers = new Readers(bookCard, name, sex, major, sex);
				ReadersDaoImpl rmpl = new ReadersDaoImpl();
				List<Readers> list=new ArrayList<Readers>();
				int ret = rmpl.updateReader(readers);
				if (ret > 0) {
					list.add(readers);
					ReaderInfoTabelModel rmModel=new ReaderInfoTabelModel(list);
					readerInfoTable.setModel(rmModel);
					JOptionPane.showMessageDialog(null, "修改成功!");
				}
				else {
					list=rmpl.getAllReader();
					ReaderInfoTabelModel rmModel=new ReaderInfoTabelModel(list);
					readerInfoTable.setModel(rmModel);
					JOptionPane.showMessageDialog(null, "修改失败");
				}
					
					
			}
		});
		button.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		button.setIcon(new ImageIcon(SearchReaderIFrm.class.getResource("/img/增加2.png")));

		JButton button_1 = new JButton("删除");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (readerInfoTable.getSelectedColumn() == -1 && readerInfoTable.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null, "请选择您要操作的单元格!");
					return;
				}
				// 保存更改
				if (readerInfoTable.isEditing()) {
					TableCellEditor editor = readerInfoTable.getCellEditor();
					if (editor != null)
						editor.stopCellEditing();
				}
				// 获取某行某列对象位置
				int row = readerInfoTable.getSelectedRow();
				long bookCard = (long) readerInfoTable.getValueAt(row, 0);
				int n = JOptionPane.showConfirmDialog(null, "确定删除该读者吗?");
				if (n == 0) {
					ReadersDaoImpl impl = new ReadersDaoImpl();
					if(impl.isExistReader(bookCard) == false){
						JOptionPane.showMessageDialog(null, "亲,没有这个人哦!");
						return;
					}
					int ret = impl.deleteReader(bookCard);
					if (ret > 0) {
						List<Readers> list=impl.getAllReader();
						if(list!=null) {
							ReaderInfoTabelModel rModel=new ReaderInfoTabelModel(list);
							readerInfoTable.setModel(rModel);
						}
						JOptionPane.showMessageDialog(null, "删除成功");
					}
					else
						JOptionPane.showMessageDialog(null, "删除失败");
				}

			}
		});
		button_1.setIcon(new ImageIcon(SearchReaderIFrm.class.getResource("/img/删除2.png")));
		button_1.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap(302, Short.MAX_VALUE).addComponent(button)
						.addGap(54).addComponent(button_1).addGap(123))
				.addGroup(groupLayout.createSequentialGroup().addContainerGap(54, Short.MAX_VALUE)
						.addComponent(label, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE).addGap(496))
				.addGroup(Alignment.LEADING,
						groupLayout.createSequentialGroup().addGap(102)
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 415, GroupLayout.PREFERRED_SIZE)
								.addContainerGap(132, Short.MAX_VALUE))
				.addGroup(Alignment.LEADING,
						groupLayout.createSequentialGroup().addGap(40)
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addComponent(panel, GroupLayout.PREFERRED_SIZE, 585,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(readerInfoPane, GroupLayout.PREFERRED_SIZE, 584,
												GroupLayout.PREFERRED_SIZE))
								.addContainerGap(24, Short.MAX_VALUE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addGap(16)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE).addGap(18)
						.addComponent(label).addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
						.addGap(18).addComponent(readerInfoPane, GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
						.addGap(18)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(button)
								.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
						.addGap(10)));

		readerInfoTable = new JTable();
		readerInfoTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

			}
		});
		
		readerInfoPane.setViewportView(readerInfoTable);
		getContentPane().setLayout(groupLayout);

		readerTable = new JTable();
		readerTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

			}
		});
		scrollPane.setViewportView(readerTable);
		getContentPane().setLayout(groupLayout);
		
		DefaultTableCellRenderer r=new DefaultTableCellRenderer();   
		r.setHorizontalAlignment(SwingConstants.CENTER);  
		readerInfoTable.setDefaultRenderer(Object.class,r);
		readerTable.setDefaultRenderer(Object.class,r);
	}
}
