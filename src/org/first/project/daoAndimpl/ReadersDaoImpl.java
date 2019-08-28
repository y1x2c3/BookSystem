package org.first.project.daoAndimpl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.first.project.model.Readers;
import org.first.project.util.JdbcUtil;

/**
 * 读者数据库操作的实现类 TODO
 * 
 * @author 木木夕 2019年8月2日下午2:13:18
 */
public class ReadersDaoImpl implements ReadersDao {

	@Override
	public List<Readers> getAllReader() {
		// 直接利用QueryRunner操作数据库
		QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
		// 编写数据库语言
		String sql = "select * from reader";
		try {
			// 查询到所有数据，而且封装在一个集合中
			List<Readers> list = qr.query(sql, new BeanListHandler<Readers>(Readers.class));
			// 返回集合
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int deleteReader(long bookCard) {
		// 直接利用QueryRunner操作数据库
		QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
		// 编写数据库语言
		String sql = "delete from reader where bookCard=?";
		try {
			// 删除数据，如果成功返回1
			int result = qr.update(sql, bookCard);
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int addReader(Readers reader) {
		// 直接利用QueryRunner操作数据库
		QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
		// 编写数据库语言
		String sql = "insert into reader value(?,?,?,?,?)";
		try {
			// 新增读者
			int result = qr.update(sql, reader.getbookCard(), reader.getName(), reader.getSex(), reader.getMajor(),
					reader.getdepartment());
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int updateReader(Readers reader) {
		// 直接利用QueryRunner操作数据库
		QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
		// 编写数据库语言
		String sql = "update reader set name=? ,sex=?,major=?,department=? where bookCard=?";
		try {
			// 删除数据，如果成功返回1
			int result = qr.update(sql, reader.getName(), reader.getSex(), reader.getMajor(), reader.getdepartment(),
					reader.getbookCard());
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public Readers getReader(long bookCard) {
		// 直接利用QueryRunner操作数据库
		QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
		// 编写数据库语言
		String sql = "select * from reader where bookCard=?";
		try {
			// 根据读者号查询到一条读者信息
			Readers reader = qr.query(sql, new BeanHandler<Readers>(Readers.class), bookCard);
			return reader;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public boolean isExistReader(long bookCard) {
		List<Readers> list = getAllReader();
		if (list == null)
			return false;
		for (Readers readers : list) {
			if (bookCard == readers.getbookCard())
				return true;
		}
		return false;
	}

}
