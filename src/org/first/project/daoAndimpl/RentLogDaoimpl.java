package org.first.project.daoAndimpl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.first.project.model.Book;
import org.first.project.model.Readers;
import org.first.project.model.RentLog;
import org.first.project.util.JdbcUtil;

public class RentLogDaoimpl implements RentLogDao {
	/**
	 * 使用关联查询
	 */
	@Override
	public List<RentLog> getAllRentLog() {
		QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
		// 获取reader,book,rentlog三者交集
		String sql = "select r.bookCard,r.name,b.isbn,b.bookName,l.rentDate,l.returnDate from reader r inner join book b inner join rentlog l on r.bookCard=l.bookCard and b.isbn=l.isbn";
		try {
			List<RentLog> list = qr.query(sql, new BeanListHandler<RentLog>(RentLog.class));
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public RentLog getRentLogByIsbnAndBookCard(String isbn, long bookCard) {
		QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
		String sql = "select r.bookCard,r.name,b.isbn,b.bookName,l.rentDate,l.returnDate from reader r inner join book b inner join rentlog l on r.bookCard=? and b.isbn=?";
		try {
			RentLog log = qr.query(sql, new BeanHandler<RentLog>(RentLog.class), bookCard, isbn);
			return log;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public int addRentLog(RentLog log) {
		QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
		String sql = "insert into rentlog values(?,?,?,?)";
		try {
			int result = qr.update(sql, log.getBookCard(), log.getIsbn(), log.getRentDate(), log.getReturnDate());
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public int updateRentLog(RentLog log) {
		QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
		String sql = "update rentlog set bookCard=?,isbn=?,rentDate=?,returnDate=?";
		try {
			int result = qr.update(sql, log.getBookCard(), log.getIsbn(), log.getRentDate(), log.getReturnDate());
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public int deleteRentLog(String isbn, long bookCard) {
		QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
		String sql = "delete from rentlog where isbn=? and bookCard=?";
		try {
			int result = qr.update(sql, isbn, bookCard);
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public boolean isExistReader(long bookCard) {
		ReadersDaoImpl impl = new ReadersDaoImpl();
		Readers readers = impl.getReader(bookCard);
		if (readers == null)
			return false;
		return true;
	}

	@Override
	public boolean isExistBook(String isbn) {
		BookDaoImpl impl = new BookDaoImpl();
		Book book = impl.getBookByID(isbn);
		if (book == null)
			return false;
		return true;
	}

	@Override
	public List<RentLog> getRentLogByBookCard(long bookCard) {
		QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
		String sql = "select * from rentlog where bookCard=?";
		try {
			List<RentLog> log = qr.query(sql, new BeanListHandler<RentLog>(RentLog.class),bookCard);
			return log;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}

}
