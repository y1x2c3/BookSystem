package org.first.project.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import org.first.project.daoAndimpl.UserDao;
import org.first.project.model.User;
import org.first.project.util.Dbutil;
import org.first.project.util.StringUtil;
import javax.swing.ImageIcon;
import java.awt.Toolkit;

public class Login extends JFrame {

	private JPanel contentPane;
	private JPasswordField passwordTxt;
	private JTextField userNameTxt;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/img/logo.png")));
		setTitle("管理员登录");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 397, 304);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel titleLabel = new JLabel("图书管理系统");
		titleLabel.setIcon(new ImageIcon(Login.class.getResource("/img/书籍.png")));
		titleLabel.setFont(new Font("宋体", Font.BOLD, 19));

		JLabel userNameLabel = new JLabel("用户名：");
		userNameLabel.setFont(new Font("宋体", Font.PLAIN, 12));
		userNameLabel.setIcon(new ImageIcon(Login.class.getResource("/img/userName.png")));
		userNameLabel.setVerticalAlignment(SwingConstants.BOTTOM);

		JLabel passwordLabel = new JLabel("密  码：");
		passwordLabel.setFont(new Font("宋体", Font.PLAIN, 12));
		passwordLabel.setIcon(new ImageIcon(Login.class.getResource("/img/Lock.png")));

		passwordTxt = new JPasswordField();

		userNameTxt = new JTextField();
		userNameTxt.setColumns(10);

		JButton loginButton = new JButton("登录");
		loginButton.setIcon(new ImageIcon(Login.class.getResource("/img/登录.png")));
		loginButton.setFont(new Font("宋体", Font.PLAIN, 12));
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginActionperformed(e);
			}
		});

		JButton resetButton = new JButton("重置");
		resetButton.setIcon(new ImageIcon(Login.class.getResource("/img/重置.png")));
		resetButton.setFont(new Font("宋体", Font.PLAIN, 12));
		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetValueActionPerformed(e);
			}
		});

		JButton registerButton = new JButton("注册");
		registerButton.setIcon(new ImageIcon(Login.class.getResource("/img/注册.png")));
		registerButton.setFont(new Font("宋体", Font.PLAIN, 12));
		registerButton.setFocusable(false);
		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registerAction(e);
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(92)
							.addComponent(titleLabel))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(81)
							.addComponent(loginButton)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(resetButton)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(registerButton))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(65)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(userNameLabel)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(userNameTxt, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(passwordLabel)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(passwordTxt)))))
					.addContainerGap(33, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(titleLabel, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addGap(35)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(userNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(userNameLabel))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(passwordTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(passwordLabel))
					.addGap(30)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(registerButton)
						.addComponent(resetButton)
						.addComponent(loginButton))
					.addGap(53))
		);
		contentPane.setLayout(gl_contentPane);
	}

	/**
	 * 注册事件处理
	 * 
	 * @param e
	 */
	protected void registerAction(ActionEvent evt) {
		new register().setVisible(true);;

	}

	/**
	 * 登录事件处理
	 * 
	 * @param e
	 */
	protected void loginActionperformed(ActionEvent evt) {
		String userName = this.userNameTxt.getText();
		String password = new String(this.passwordTxt.getPassword());
		if (StringUtil.isEmpty(userName)) {
			JOptionPane.showMessageDialog(null, "用户名不能为空！");
			return;
		}
		if (StringUtil.isEmpty(password)) {
			JOptionPane.showMessageDialog(null, "密码不能为空！");
			return;
		}
		User user = new User(userName, password);
	    Dbutil dbUtil = new Dbutil();
		UserDao userDao = new UserDao();
		Connection con = null;
		try {
			con = dbUtil.getCon();
			User currentUser = userDao.login(con, user);
			if (currentUser != null) {
				JOptionPane.showMessageDialog(null, "登录成功");
				// 销毁登录窗口
				dispose();
				// 主菜单界面
				new MainFrm().setVisible(true);
			} else {
				JOptionPane.showMessageDialog(null, "用户名或密码错误");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 重置事件处理
	 * 
	 * @param e
	 */
	private void resetValueActionPerformed(ActionEvent e) {
		this.userNameTxt.setText("");
		this.passwordTxt.setText("");
	}
}
