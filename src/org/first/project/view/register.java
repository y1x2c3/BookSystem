package org.first.project.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.first.project.daoAndimpl.UserDao;
import org.first.project.model.User;
import org.first.project.util.Dbutil;
import org.first.project.util.StringUtil;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Frame;

import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;

import java.awt.event.ActionListener;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import java.awt.Color;
import java.awt.Container;

import javax.swing.SwingConstants;
import javax.swing.UIManager;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.Toolkit;

public class register extends JFrame {
	private JLabel lblNewLabel_1;

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private JPasswordField ensurelabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					register frame = new register();
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
	public register() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(register.class.getResource("/img/logo.png")));
		setTitle("管理员注册");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 475, 347);
		setResizable(false);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblYong = new JLabel("管理员注册");
		lblYong.setFont(new Font("宋体", Font.BOLD, 19));
		lblYong.setBounds(156, 25, 108, 35);
		contentPane.add(lblYong);

		JLabel label_1 = new JLabel("用户名：");
		label_1.setFont(new Font("微软雅黑", Font.BOLD, 14));
		label_1.setBounds(81, 73, 67, 35);
		contentPane.add(label_1);

		textField = new JTextField();
		textField.setBounds(134, 80, 172, 21);
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel = new JLabel("密   码：");
		lblNewLabel.setFont(new Font("微软雅黑", Font.BOLD, 14));
		lblNewLabel.setBounds(81, 118, 78, 23);
		contentPane.add(lblNewLabel);

		JButton button = new JButton("注册");
		button.setFont(new Font("宋体", Font.BOLD, 12));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				regisertActionperformed(e);
			}
		});
		button.setBounds(134, 248, 66, 23);
		contentPane.add(button);

		passwordField = new JPasswordField();
		passwordField.setBackground(Color.WHITE);
		passwordField.setBounds(134, 119, 172, 21);
		contentPane.add(passwordField);

		JButton btnNewButton = new JButton("重置");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registeresetValueActionPerformed(e);
			}
		});
		btnNewButton.setFont(new Font("宋体", Font.BOLD, 12));
		btnNewButton.setBounds(228, 248, 67, 23);
		contentPane.add(btnNewButton);

		JLabel label_2 = new JLabel("密码强度：");
		label_2.setFont(new Font("微软雅黑", Font.BOLD, 14));
		label_2.setBounds(63, 186, 85, 15);
		contentPane.add(label_2);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(136, 186, 37, 15);
		contentPane.add(lblNewLabel_1);
		lblNewLabel_1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		lblNewLabel_1.setOpaque(true);

		JLabel StrongLevel = new JLabel("");
		StrongLevel.setBounds(183, 186, 54, 15);
		contentPane.add(StrongLevel);
		
		JLabel label = new JLabel("确认密码：");
		label.setFont(new Font("微软雅黑", Font.BOLD, 14));
		label.setBounds(63, 151, 78, 23);
		contentPane.add(label);
		
		ensurelabel = new JPasswordField();
		ensurelabel.setBackground(Color.WHITE);
		ensurelabel.setBounds(134, 155, 172, 21);
		contentPane.add(ensurelabel);
		
		JLabel lblNewLabel_2 = new JLabel("3-10位的字母或数字");
		lblNewLabel_2.setBounds(316, 84, 146, 15);
		contentPane.add(lblNewLabel_2);
		
		JLabel label_3 = new JLabel("必须为6位的字母或数字");
		label_3.setBounds(316, 123, 156, 15);
		contentPane.add(label_3);
		String password = String.valueOf(passwordField.getPassword());
		passwordField.addKeyListener(new KeyListener() {
			@Override
			/**
			 * 检测密码强度 弱：红  中：黄   强：绿
			 */
			public void keyTyped(KeyEvent e) {
				char[] password_1 = passwordField.getPassword();
				String temp = new String(password_1);
				if (temp.matches("\\d*")) {
					lblNewLabel_1.setBackground(Color.RED);
					StrongLevel.setText("");
					StrongLevel.setText("弱");
				} else if (temp.matches("[a-zA-Z]+")) {
					lblNewLabel_1.setBackground(Color.RED);
					StrongLevel.setText("");
					StrongLevel.setText("弱");
				} else if (temp.matches("\\W+$")) {
					lblNewLabel_1.setBackground(Color.RED);
					StrongLevel.setText("");
					StrongLevel.setText("弱");
				} else if (temp.matches("\\D*")) {
					lblNewLabel_1.setBackground(Color.yellow);
					StrongLevel.setText("");
					StrongLevel.setText("中");
				} else if (temp.matches("[\\d\\W]*")) {
					lblNewLabel_1.setBackground(Color.yellow);
					StrongLevel.setText("");
					StrongLevel.setText("中");
				} else if (temp.matches("\\w*")) {
					lblNewLabel_1.setBackground(Color.yellow);
					StrongLevel.setText("");
					StrongLevel.setText("中");
				} else if (password.matches("[\\w\\W]*")) {
					lblNewLabel_1.setBackground(Color.green);
					StrongLevel.setText("");
					StrongLevel.setText("强");
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});
	}
	/**
	 * 重置
	 * @param evt
	 */
	protected void registeresetValueActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		this.textField.setText("");
		this.passwordField.setText("");
		this.ensurelabel.setText("");
	}
	/**
	 * 注册
	 * @param evt
	 */
	protected void regisertActionperformed(ActionEvent evt) {
		String password = new String(this.passwordField.getPassword());
		String userName = this.textField.getText();
		String enSurePass=new String(this.ensurelabel.getPassword());
		// 不能包含特殊字符
		String regEx = "[ `%^&*()+=|{}':;',\\[\\].<>/?￥%……&*（）——+|{}【】‘；：”“’。，、？]|\n|\r|\t";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(passwordField.getText());
		if (m.find() == true) {
			JOptionPane.showMessageDialog(null, "有非法字符,请重试!");
			return;
		}
		if (StringUtil.isEmpty(userName)) {
			JOptionPane.showMessageDialog(null, "用户名不能为空!");
			return;
		}
		if (StringUtil.isEmpty(password)) {
			JOptionPane.showMessageDialog(null, "密码不能为空！");
			return;
		}
		if (StringUtil.isEmpty(enSurePass)) {
			JOptionPane.showMessageDialog(null, "请确认密码!");
			return;
		}
		int len1 = userName.length();
		int len2 = password.length();
		if (len1 < 3 || len1 > 10) {
			JOptionPane.showMessageDialog(null, "用户名为3——10位");
			return;
		} else if (len2 != 6) {
			JOptionPane.showMessageDialog(null, "密码必须为6位");
			return;
		}
		if (len1 >= 3 && len1 <= 10 && len2 == 6 && m.find() == false&&password.equals(enSurePass)) {
			Dbutil dbutil=new Dbutil();
			UserDao userDao=new UserDao();
			Connection connection=null;
			connection=dbutil.getCon();
			int n=userDao.registerAdmin(connection,userName,enSurePass);
			if(n>0)
				JOptionPane.showMessageDialog(null, "注册成功！");
			else
				JOptionPane.showMessageDialog(null, "注册失败!确认是否打开数据库连接");
		}else {
			JOptionPane.showMessageDialog(null, "两次密码不一致!请重试");
			return;
		}
	}

	
}
