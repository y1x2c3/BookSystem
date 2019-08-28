package org.first.project.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.JTree;
import javax.swing.Timer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
//import javax.swing.JMenuBar;
//import javax.swing.JMenu;
//import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import java.net.URI;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.LayoutStyle.ComponentPlacement;

public class MainFrm extends JFrame implements TreeSelectionListener {

	private JPanel contentPane;
	private JTree mainTree;
	private JDesktopPane viewTable;
	private JLabel iconLabel;
	private JLabel time;
	Login loginFram = null;
	BorrowBookIFrm borrowBookIFrm = null;
	ReturnBookIFrm returnBookIFrm;
	SearchReaderIFrm searchReaderIFrm = null;
	AddReaderIFrm addReaderIFrm = null;
	SearchBookIFrm searchBookIFrm = null;
	AddBookIFrm addBookIFrm = null;

	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;
		Line2D lin = new Line2D.Float(176, 0, 176, 1000);
		g2.draw(lin);
	}

	private void setTimer(JLabel time) {
		final JLabel varTime = time;
		Timer timeAction = new Timer(1000, new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				long timemillis1 = System.currentTimeMillis();

				// 转换日期显示格式
				SimpleDateFormat df = new SimpleDateFormat("YYYY-MM-dd  hh:mm:ss");
				varTime.setText(df.format(new Date(timemillis1)));
			}
		});
		timeAction.start();
	}

	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {

			public void run() {
				try {
					MainFrm frame = new MainFrm();
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
	public MainFrm() {
		setTitle("图书管理系统-主菜单");
		setResizable(false);
		setLocationRelativeTo(null);
		URL imgUrl = MainFrm.class.getResource("/img/logo.png");
		ImageIcon icon = new ImageIcon(imgUrl);
		setIconImage(icon.getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1057, 593);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		mainTree = new JTree();
		mainTree.setBackground(SystemColor.window);
		mainTree.setFont(new Font("Lucida Grande", Font.PLAIN, 16));

		mainTree.setModel(new DefaultTreeModel(new DefaultMutableTreeNode("主菜单") {
			{
				DefaultMutableTreeNode managerNode;
				DefaultMutableTreeNode readerBookNode;
				DefaultMutableTreeNode systemBookNode;

				managerNode = new DefaultMutableTreeNode("管理员管理");
				managerNode.add(new DefaultMutableTreeNode("书籍借阅"));
				managerNode.add(new DefaultMutableTreeNode("书籍归还"));

				systemBookNode = new DefaultMutableTreeNode("系统管理");
				systemBookNode.add(new DefaultMutableTreeNode("注销"));
				systemBookNode.add(new DefaultMutableTreeNode("退出"));

				readerBookNode = new DefaultMutableTreeNode("图书管理");
				readerBookNode.add(new DefaultMutableTreeNode("新书入库"));
				readerBookNode.add(new DefaultMutableTreeNode("书籍查询"));
				managerNode.add(readerBookNode);
				readerBookNode = new DefaultMutableTreeNode("读者管理");
				readerBookNode.add(new DefaultMutableTreeNode("新增读者"));
				readerBookNode.add(new DefaultMutableTreeNode("读者查询"));
				// readerBookNode.add(new DefaultMutableTreeNode("读者修改"));
				// readerBookNode.add(new DefaultMutableTreeNode("读者删除"));
				managerNode.add(readerBookNode);
				// 3.给树上节点添加图片
				// 首先要获得树的DefaultTreeCellRenderer
				DefaultTreeCellRenderer renderer = (DefaultTreeCellRenderer) mainTree.getCellRenderer();
				Icon rootIcon = new ImageIcon(MainFrm.class.getResource("/img/bookTypeManager.png"));
				Icon leafIcon = new ImageIcon(MainFrm.class.getResource("/img/about.png"));
				Icon collIcon = new ImageIcon(MainFrm.class.getResource("/img/base.png"));

				// 添加图片
				renderer.setLeafIcon(leafIcon);// 叶节点图片
				renderer.setOpenIcon(rootIcon);// 树打开时显示的图片
				renderer.setClosedIcon(collIcon);// 树关闭时显示的图片

				mainTree.addTreeSelectionListener(MainFrm.this);
				mainTree.expandPath(new TreePath(managerNode.getPath()));

				add(managerNode);
				add(systemBookNode);
			}
		}));

		viewTable = new JDesktopPane();
		viewTable.setBackground(Color.LIGHT_GRAY);
		viewTable.setLayout(null);

		// 创建一个标签组件，用于放置背景图片
		final JLabel backgroundLabel = new JLabel();

		ImageIcon icon2 = new ImageIcon(MainFrm.class.getResource("/img/library.jpg"));
		// 压缩背景图片，使其适应窗口大小
		icon2.setImage(icon2.getImage().getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_DEFAULT));
		backgroundLabel.setIcon(icon2);
		backgroundLabel.setBounds(0, 0, this.getWidth(), this.getHeight());
		viewTable.add(backgroundLabel, new Integer(Integer.MIN_VALUE));
		
		//系统时间
		JLabel time = new JLabel();
		time.setBounds(685, 509, 149, 16);
		viewTable.add(time);
		this.setTimer(time);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addComponent(mainTree, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE).addGap(37)
						.addComponent(viewTable, GroupLayout.PREFERRED_SIZE,976, GroupLayout.PREFERRED_SIZE)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(viewTable, GroupLayout.PREFERRED_SIZE, 561, GroupLayout.PREFERRED_SIZE)
								.addComponent(mainTree, GroupLayout.PREFERRED_SIZE, 561, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		contentPane.setLayout(gl_contentPane);

	}

	// 点击事件
	@Override
	public void valueChanged(TreeSelectionEvent e) {
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) mainTree.getLastSelectedPathComponent();
		if (node == null) {
			return;
		}
		if (node.isLeaf()) {
			String s = node.toString();
			System.out.println(s);
			if (s == "退出") {
				int result = JOptionPane.showConfirmDialog(null, "是否退出系统！");
				if (result == 0) {
					dispose();
				}
			} else if (s == "注销") {
				// testLabel.setText("您选择了："+s);
				int result = JOptionPane.showConfirmDialog(null, "确认注销！");
				if (result == 0) {
					dispose();
					Login loginFram = new Login();
					loginFram.setVisible(true);
					loginFram.setLocationRelativeTo(null);
				}
			}

			else if (s == "书籍借阅") {
				// testLabel.setText("您选择了："+s);
				if (borrowBookIFrm != null)
					borrowBookIFrm.dispose();
				if (searchReaderIFrm != null)
					searchReaderIFrm.dispose();
				if (addReaderIFrm != null)
					addReaderIFrm.dispose();
				if (searchBookIFrm != null)
					searchBookIFrm.dispose();
				if (addBookIFrm != null)
					addBookIFrm.dispose();
				if (loginFram != null)
					loginFram.dispose();
				if (returnBookIFrm != null)
					returnBookIFrm.dispose();
				borrowBookIFrm = new BorrowBookIFrm();
				borrowBookIFrm.setVisible(true);
				viewTable.add(borrowBookIFrm);

			}

			else if (s == "新书入库") {
				viewTable.getAllFrames();

				// testLabel.setText("您选择了："+s);
				if (borrowBookIFrm != null)
					borrowBookIFrm.dispose();
				if (searchReaderIFrm != null)
					searchReaderIFrm.dispose();
				if (addReaderIFrm != null)
					addReaderIFrm.dispose();
				if (searchBookIFrm != null)
					searchBookIFrm.dispose();
				if (addBookIFrm != null)
					addBookIFrm.dispose();
				if (loginFram != null)
					loginFram.dispose();
				if (returnBookIFrm != null)
					returnBookIFrm.dispose();
				addBookIFrm = new AddBookIFrm();
				addBookIFrm.setVisible(true);
				viewTable.add(addBookIFrm);
			}

			else if (s == "书籍查询") {
				// testLabel.setText("您选择了："+s);
				if (borrowBookIFrm != null)
					borrowBookIFrm.dispose();
				if (searchReaderIFrm != null)
					searchReaderIFrm.dispose();
				if (addReaderIFrm != null)
					addReaderIFrm.dispose();
				if (searchBookIFrm != null)
					searchBookIFrm.dispose();
				if (addBookIFrm != null)
					addBookIFrm.dispose();
				if (loginFram != null)
					loginFram.dispose();
				if (returnBookIFrm != null)
					returnBookIFrm.dispose();
				searchBookIFrm = new SearchBookIFrm();
				searchBookIFrm.setVisible(true);
				viewTable.add(searchBookIFrm);
			}

			else if (s == "新增读者") {
				// testLabel.setText("您选择了："+s);
				if (borrowBookIFrm != null)
					borrowBookIFrm.dispose();
				if (searchReaderIFrm != null)
					searchReaderIFrm.dispose();
				if (addReaderIFrm != null)
					addReaderIFrm.dispose();
				if (searchBookIFrm != null)
					searchBookIFrm.dispose();
				if (addBookIFrm != null)
					addBookIFrm.dispose();
				if (loginFram != null)
					loginFram.dispose();
				if (returnBookIFrm != null)
					returnBookIFrm.dispose();
				addReaderIFrm = new AddReaderIFrm();
				addReaderIFrm.setVisible(true);
				viewTable.add(addReaderIFrm);
			}

			else if (s == "读者查询") {
				// testLabel.setText("您选择了："+s);
				if (borrowBookIFrm != null)
					borrowBookIFrm.dispose();
				if (searchReaderIFrm != null)
					searchReaderIFrm.dispose();
				if (addReaderIFrm != null)
					addReaderIFrm.dispose();
				if (searchBookIFrm != null)
					searchBookIFrm.dispose();
				if (addBookIFrm != null)
					addBookIFrm.dispose();
				if (loginFram != null)
					loginFram.dispose();
				if (returnBookIFrm != null)
					returnBookIFrm.dispose();
				searchReaderIFrm = new SearchReaderIFrm();
				searchReaderIFrm.setVisible(true);
				viewTable.add(searchReaderIFrm);
			} else if (s == "书籍归还") {
				// testLabel.setText("您选择了："+s);

				if (borrowBookIFrm != null)
					borrowBookIFrm.dispose();
				if (searchReaderIFrm != null)
					searchReaderIFrm.dispose();
				if (addReaderIFrm != null)
					addReaderIFrm.dispose();
				if (searchBookIFrm != null)
					searchBookIFrm.dispose();
				if (addBookIFrm != null)
					addBookIFrm.dispose();
				if (loginFram != null)
					loginFram.dispose();
				if (returnBookIFrm != null)
					returnBookIFrm.dispose();

				returnBookIFrm = new ReturnBookIFrm();
				returnBookIFrm.setVisible(true);
				viewTable.add(returnBookIFrm);

			}

		}
	}
}
