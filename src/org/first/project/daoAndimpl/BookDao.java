package org.first.project.daoAndimpl;

import java.util.List;
import org.first.project.model.Book;

/**
 * 图书的接口 TODO
 * 
 * @author 木木夕 2019年8月4日上午8:45:05
 */
public interface BookDao {
	/** 定义一个方法查询所有的书籍 */
	public List<Book> getAllBook();

	/** 定义一个方法查询一本书籍（根据书本名字） */
	public Book getBook(String name);

	/** 是否存在这本书 */
	public boolean isExistBook(String isbn);

	/** 根据书号查找图书 */
	public Book getBookByID(String isbn);

	/** 根据书号删除 */
	public int deleteBook(String isbn);

	/** 根据书号删除图书 */
	public int deleteBookByID(String isbn);

	/** 添加书籍 */
	public int addBook(Book book);

	/** 更新图书 */
	public int updateBook(Book book);

}
