package org.first.project.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSourceFactory;

/**
 * 编写一个连接数据库的工具类 TODO
 * 
 * @author 木木夕 2019年8月2日下午1:36:17
 */
public class JdbcUtil {

	public static DataSource ds = null;
	static {
		try {
			// 1.加载配置文件
			Properties p = new Properties();
			// 获取字节码目录
			p.load(JdbcUtil.class.getResourceAsStream("db.properties"));
			ds = DruidDataSourceFactory.createDataSource(p);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 获取数据源
	public static DataSource getDataSource() {
		return ds;
	}

	public static Connection getConn() {
		try {
			// 2.连接数据
			return ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 关闭资源
	 */
	public static void close(Connection conn, Statement st, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
