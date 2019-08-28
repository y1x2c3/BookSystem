package org.first.project.daoAndimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.first.project.model.User;

/**
 * 用户DAO类
 * 
 * @author 20824 2019年8月2日上午1:43:56
 */
public class UserDao {
	/**
	 * 登录验证
	 * 
	 * @param con
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public User login(Connection con, User user) throws Exception {
		User resultUser = null;
		PreparedStatement pstmt = null;
		String sql = "select * from admin where name=? and password=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user.getname());
			pstmt.setString(2, user.getPassword());
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				resultUser = new User();
				resultUser.setId(rs.getInt("id"));
				resultUser.setname(rs.getString("name"));
				resultUser.setname(rs.getString("password"));
			}
			rs.close();
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (Exception e) {
			}
		}
		return resultUser;
	}
	/**
	 * 注册
	 */
	public int registerAdmin(Connection con,String username,String password){
		PreparedStatement pstmt = null;
		String sql="insert into admin(name,password) values(?,?)";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1,username);
			pstmt.setString(2,password);
			int n=pstmt.executeUpdate();
			//1成功 0用户名或密码错误 -1数据库异常
			if(n>0)
				return n;
			else
				return 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
		
	}

}
