package org.first.project.util;

import java.util.Date;
import java.util.List;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import org.first.project.daoAndimpl.BookDaoImpl;
import org.first.project.model.Book;
import org.first.project.model.RentLog;
import org.w3c.dom.ls.LSInput;
/**
 * 同步显示读者信息后的未归还图书信息   isbn 书名  待归还时间
 * @author 追风
 * @date 2019-8-7 23:22
 */
public class ShowNotReturnReaderTableModel implements TableModel{
	private List<RentLog> list;

	
	public ShowNotReturnReaderTableModel(List<RentLog> list) {
		this.list = list;
	}

	@Override
	public int getRowCount() {
		return list.size();
	}

	@Override
	public int getColumnCount() {
		return 3;
	}

	@Override
	public String getColumnName(int columnIndex) {
		switch (columnIndex) {
		case 0:
			return "ISBN";
		case 1:
			return "图书名称";
		case 2:
			return "待归还日期";
		default:
			return "无此字段";
		}
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		if(columnIndex==2)
			return Date.class;
		else if(columnIndex==0||columnIndex==1)
			return String.class;
		return null;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		RentLog log=list.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return log.getIsbn();
		case 1:
			String isbn=log.getIsbn();
			BookDaoImpl impl=new BookDaoImpl();
			Book book=impl.getBookByID(isbn);
			return book.getBookName();
		case 2:
			return log.getReturnDate();
		default:
			return "无此字段";
		}
		
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
	}

	@Override
	public void addTableModelListener(TableModelListener l) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeTableModelListener(TableModelListener l) {
		// TODO Auto-generated method stub
		
	}

}
