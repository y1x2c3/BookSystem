package org.first.project.daoAndimpl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.first.project.model.Book;
import org.first.project.util.JdbcUtil;

public class BookDaoImpl implements BookDao {

	@Override
	public List<Book> getAllBook() {
		// 直接利用QueryRunner操作数据库
		QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
		// 编写数据库语言
		String sql = "select * from book";
		try {
			// 查询到所有数据，而且封装在一个集合中
			List<Book> list = qr.query(sql, new BeanListHandler<Book>(Book.class));
			// 返回集合
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Book getBook(String name) {
		QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
		// 编写数据库语言
		String sql = "select * from book where bookName=?";
		try {
			// 根据读者号查询到一条读者信息
			Book book = qr.query(sql, new BeanHandler<Book>(Book.class), name);
			return book;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int deleteBook(String name) {
		// 直接利用QueryRunner操作数据库
		QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
		// 编写数据库语言
		String sql = "delete from book where bookName=?";
		try {
			// 删除数据，如果成功返回1
			int result = qr.update(sql, name);
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int addBook(Book book) {
		// 直接利用QueryRunner操作数据库
		QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
		// 编写数据库语言
		String sql = "insert into book value(?,?,?,?,?,?,?,?)";
		try {
			// 新增读者
			int result = qr.update(sql, book.getIsbn(), book.getBookName(), book.getBookType(), book.getAuthor(),
					book.getPublisher(), book.getInstoreTime(), book.getIsRent(), book.getCount());
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	/** 更新图书 */
	public int updateBook(Book book) {
		// 直接利用QueryRunner操作数据库
		QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
		// 编写数据库语言
		String sql = "update book set bookName=?,bookType=?,author=?,publisher=?,instoreTime=?,isRent=?,count=? where isbn=?";
		try {
			int result = qr.update(sql, book.getBookName(), book.getBookType(), book.getAuthor(), book.getPublisher(),
					book.getInstoreTime(), book.getIsRent(), book.getCount(), book.getIsbn());
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	/** 根据书号删除图书 */
	public int deleteBookByID(String isbn) {
		// 直接利用QueryRunner操作数据库
		QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
		// 编写数据库语言
		String sql = "delete from book where isbn=?";
		try {
			// 删除数据，如果成功返回1
			int result = qr.update(sql, isbn);
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	/** 根据书号查找图书 */
	public Book getBookByID(String isbn) {
		QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
		// 编写数据库语言
		String sql = "select * from book where isbn=?";
		try {
			// 根据读者号查询到一条读者信息
			Book book = qr.query(sql, new BeanHandler<Book>(Book.class), isbn);
			return book;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean isExistBook(String isbn) {
		List<Book> list = getAllBook();
		if (list == null)
			return false;
		for (Book book : list) {
			if (isbn.equals(book.getIsbn()))
				return true;
		}
		return false;
	}

}
