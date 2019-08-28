package org.first.project.util;

import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import org.first.project.model.Book;

/**
 * 展示JTable中的book数据
 * 
 * @author 追风
 * @Date 2019-8-5 14-41
 */
// 自定义TableModel实现类
public class BookInfoTableModel implements TableModel {
	/**
	 * 要传入的book数据对象
	 */
	private List<Book> bookList;

	public BookInfoTableModel(List<Book> bookList) {
		this.bookList = bookList;
	}

	/**
	 * 行数
	 */
	@Override
	public int getRowCount() {
		// 多少条数据多少行
		return bookList.size();
	}

	/**
	 * 列数
	 */
	@Override
	public int getColumnCount() {
		// 8个字段
		return 8;
	}

	/**
	 * 列名
	 */
	@Override
	public String getColumnName(int columnIndex) {
		switch (columnIndex) {
		case 0:
			return "ISBN";
		case 1:
			return "图书名称";
		case 2:
			return "图书类型";
		case 3:
			return "作者";
		case 4:
			return "出版社";
		case 5:
			return "入库时间";
		case 6:
			return "是否可借";
		case 7:
			return "馆藏数量";
		default:
			return "无此字段";
		}

	}

	/**
	 * 指定某列类型,默认string
	 */
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		// Date,boolean,int,string的包装类
		//boolean必须返回对应列的元素,不然只显示复选框
		
		if (columnIndex == 5) {
			return Date.class;
		} else if (columnIndex == 6) {
			return Boolean.class;
		} else if (columnIndex == 7) {
			return Integer.class;
		} else {
			return String.class;
		}
	}

	/**
	 * 指定某列是否可编辑
	 */
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		//不可编辑
		return false;
	}

	/**
	 * 获得某行对象
	 */
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Book list = bookList.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return list.getIsbn();
		case 1:
			return list.getBookName();
		case 2:
			return list.getBookType();
		case 3:
			return list.getAuthor();
		case 4:
			return list.getPublisher();
		case 5:
			return list.getInstoreTime();
		case 6:
			return list.getIsRent();
		case 7:
			return list.getCount();
		default:
			break;
		}
		return "无此对象";
	}

	/**
	 * 表格响应修改内容
	 */
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		String change = (rowIndex + 1) + "行" + (columnIndex + 1) + "列的数据已被修改为:\n" + aValue.toString();
		JOptionPane.showMessageDialog(null, change);
	}

	/**
	 * 添加监听器
	 */
	@Override
	public void addTableModelListener(TableModelListener l) {

	}

	/**
	 * 移除监听器
	 */
	@Override
	public void removeTableModelListener(TableModelListener l) {

	}

}
