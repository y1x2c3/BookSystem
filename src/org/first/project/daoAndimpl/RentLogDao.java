package org.first.project.daoAndimpl;
/**
 * 借阅表功能接口
 * @author 追风
 * @date 2019-8-6 16:32
 */

import java.util.List;

import org.first.project.model.RentLog;

public interface RentLogDao {
	/**
	 * 借阅一览表
	 * 
	 * @return
	 */
	public List<RentLog> getAllRentLog();

	/**
	 * 根据isbn和图书证查看借阅信息
	 * 
	 * @param isbn
	 * @param bookCard
	 * @return
	 */
	public RentLog getRentLogByIsbnAndBookCard(String isbn, long bookCard);
	
	/**
	 * 显示读者信息同步显示未归还的书籍 可能借了多本
	 * @param bookCard
	 * @return
	 */
	public List<RentLog> getRentLogByBookCard(long bookCard);

	/**
	 * 是否存在该读者
	 * 
	 * @param bookCard
	 * @return
	 */
	public boolean isExistReader(long bookCard);

	/**
	 * 是否存在该图书
	 * 
	 * @param isbn
	 * @return
	 */
	public boolean isExistBook(String isbn);

	/**
	 * 新增借阅
	 * 
	 * @param log
	 * @return
	 */
	public int addRentLog(RentLog log);

	/**
	 * 更新借阅表
	 * 
	 * @param log
	 * @return
	 */
	public int updateRentLog(RentLog log);

	/**
	 * 借阅归还删除
	 * 
	 * @param isbn
	 * @param bookCard
	 * @return
	 */
	public int deleteRentLog(String isbn, long bookCard);

}
