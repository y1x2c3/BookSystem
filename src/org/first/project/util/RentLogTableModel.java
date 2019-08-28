package org.first.project.util;

import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import org.first.project.daoAndimpl.BookDaoImpl;
import org.first.project.daoAndimpl.ReadersDaoImpl;
import org.first.project.model.Book;
import org.first.project.model.Readers;
import org.first.project.model.RentLog;

public class RentLogTableModel implements TableModel {

	private List<RentLog> listRentLog;

	public RentLogTableModel(List<RentLog> listRentLog) {
		this.listRentLog = listRentLog;
	}

	@Override
	public int getRowCount() {
		return listRentLog.size();
	}

	@Override
	public int getColumnCount() {
		return 6;
	}

	@Override
	public String getColumnName(int columnIndex) {
		switch (columnIndex) {
		case 0:
			return "图书证";
		case 1:
			return "姓名";
		case 2:
			return "ISBN";
		case 3:
			return "图书名称";
		case 4:
			return "借阅时间";
		case 5:
			return "归还时间";
		default:
			return "无此字段";
		}
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		if (columnIndex == 0) {
			return Long.class;
		} else if (columnIndex >= 1 && columnIndex <= 3) {
			return String.class;
		} else if (columnIndex == 4 || columnIndex == 5) {
			return Date.class;
		} else
			return null;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		RentLog log = listRentLog.get(rowIndex);
		if (log == null)
			return null;
		String isbn = log.getIsbn();
		long bookCard = log.getBookCard();
		switch (columnIndex) {
		case 0:
			return log.getBookCard();
		case 1:
			ReadersDaoImpl rmpl = new ReadersDaoImpl();
			Readers readers = rmpl.getReader(bookCard);
			return readers.getName();
		case 2:
			return log.getIsbn();
		case 3:
			BookDaoImpl bmpl = new BookDaoImpl();
			Book b = bmpl.getBookByID(isbn);
			return b.getBookName();
		case 4:
			return log.getRentDate();
		case 5:
			// 注意getVauleAt返回非String类型必须干净
			return log.getReturnDate();
		default:
			return new Exception("OutOfBoundIndex:-1");
		}
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		String change = (rowIndex + 1) + "行" + (columnIndex + 1) + "列的数据已被修改为:\n" + aValue.toString();
		JOptionPane.showMessageDialog(null, change);
	}

	@Override
	public void addTableModelListener(TableModelListener l) {

	}

	@Override
	public void removeTableModelListener(TableModelListener l) {

	}

}
