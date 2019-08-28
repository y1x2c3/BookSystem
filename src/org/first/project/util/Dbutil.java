package org.first.project.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 数据库工具类
 * 
 * @author
 *
 */
public class Dbutil {
	private String dbUrl = "jdbc:mysql://localhost:3306/booksystem";// 数据库地址
	private String dbUserName = "root";
	private String dbPassword = "Y13627037792"; // "258456";
	private String jdbcName = "com.mysql.jdbc.Driver";

	/**
	 * 获取数据库连接
	 * 
	 * @return
	 * @throws Exception
	 */
	public Connection getCon() {
		try {
			Class.forName(jdbcName);
			Connection con = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
			return con;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 关闭数据库
	 * 
	 * @param con
	 * @throws Exception
	 */
	public void closeCon(Connection con) throws Exception {
		if (con != null) {
			con.close();
		}
	}

	public static void main(String[] args) {
		Dbutil dbUtil = new Dbutil();
		try {
			dbUtil.getCon();
			System.out.println("数据库连接成功");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("失败");
		}
	}
}
