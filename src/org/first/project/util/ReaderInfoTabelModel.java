package org.first.project.util;

import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import org.first.project.model.Readers;

/**
 * 展示Jtable中读者信息
 * 
 * @author 追风
 * @date 2019-8-5 19:20
 */
public class ReaderInfoTabelModel implements TableModel {
	/**
	 * 读者列表对象
	 */
	private List<Readers> listReaders;

	public ReaderInfoTabelModel(List<Readers> listReaders) {
		this.listReaders = listReaders;
	}

	@Override
	public int getRowCount() {
		return listReaders.size();
	}

	@Override
	public int getColumnCount() {
		return 5;
	}

	@Override
	public String getColumnName(int columnIndex) {
		switch (columnIndex) {
		case 0:
			return "图书证";
		case 1:
			return "姓名";
		case 2:
			return "性别";
		case 3:
			return "专业";
		case 4:
			return "院系";
		default:
			break;
		}
		return "无此字段";
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		if (columnIndex == 0)
			return Long.class;
		return String.class;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		// 图书证是唯一标识
		if (columnIndex == 0)
			return false;
		return true;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Readers reader = listReaders.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return reader.getbookCard();
		case 1:
			return reader.getName();
		case 2:
			return reader.getSex();
		case 3:
			return reader.getMajor();
		case 4:
			return reader.getdepartment();
		default:
			return new Exception("outOfBoundIndex:-1");
		}
	}

	/**
	 * 修改表数据
	 */
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		String change = (rowIndex + 1) + "行" + (columnIndex + 1) + "列的数据已被修改为:\n" + aValue.toString();
		// 获取选中的单元格
		Readers reader = listReaders.get(rowIndex);
		String editData = String.valueOf(aValue);
		switch (columnIndex) {
		case 0:
			reader.setbookCard(Integer.parseInt(editData));
			break;
		case 1:
			reader.setName(editData);
			break;
		case 2:
			reader.setSex(editData);
			break;
		case 3:
			reader.setMajor(editData);
			break;
		case 4:
			reader.setdepartment(editData);
			break;
		default:
			// 越界
			throw new IndexOutOfBoundsException();
		}
		JOptionPane.showMessageDialog(null, change);
	}

	@Override
	public void addTableModelListener(TableModelListener l) {

	}

	@Override
	public void removeTableModelListener(TableModelListener l) {

	}

}
