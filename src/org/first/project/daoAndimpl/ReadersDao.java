package org.first.project.daoAndimpl;

import java.util.List;

import org.first.project.model.Readers;

/**
 * 定义一个接口，面向接口编程 TODO
 * 
 * @author 木木夕 2019年8月2日下午2:02:59
 */
public interface ReadersDao {
	// 定义一个查找所有读者的方法
	public List<Readers> getAllReader();

	// 定义一个删除读者的方法（根据主键读者号）返回1成功 0失败
	public int deleteReader(long bookCard);

	// 是否存在该读者
	public boolean isExistReader(long bookCard);

	// 定义一个新增读者的方法 返回1成功 0失败
	public int addReader(Readers reader);

	// 定义一个方法修改读者信息
	public int updateReader(Readers reader);

	// 定义一个方法，根据名字读者号查询读者信息
	public Readers getReader(long bookCard);

}
