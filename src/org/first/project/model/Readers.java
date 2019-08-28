package org.first.project.model;

/**
 * 书写读者类，来完成数据库与java代码的交互 TODO
 * 
 * @author 木木夕 2019年8月2日下午1:39:22
 */
public class Readers {
	// 读者号
	private long bookCard;
	// 读者名字
	private String name;
	// 读者性别
	private String sex;
	// 读者主修课
	private String major;
	// 读者学院
	private String department;

	public Readers() {
	}

	public Readers(long bookCard, String name, String sex, String major, String department) {
		this.bookCard = bookCard;
		this.name = name;
		this.sex = sex;
		this.major = major;
		this.department = department;
	}

	public long getbookCard() {
		return bookCard;
	}

	public void setbookCard(long bookCard) {
		this.bookCard = bookCard;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getdepartment() {
		return department;
	}

	public void setdepartment(String department) {
		this.department = department;
	}

	@Override
	public String toString() {
		return "Reader [bookCard=" + bookCard + ", name=" + name + ", sex=" + sex + ", major=" + major + ", department="
				+ department + "]";
	}

	/**
	 * 根据学号确定读者
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof Readers))
			return false;
		Readers reader = (Readers) obj;
		if (reader.getbookCard() == this.bookCard) {
			return true;
		} else {
			return false;
		}

	}

}
