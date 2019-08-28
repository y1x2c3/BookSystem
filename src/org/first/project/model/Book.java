package org.first.project.model;

import java.util.Date;

/**
 * 定义图书类的实体类 TODO
 * 
 * @author 木木夕 2019年8月3日下午1:37:57
 */
public class Book {
	private String isbn;
	private String bookName;
	private String bookType;
	private String author;
	private String publisher;
	private Date instoreTime;
	private Boolean isRent;
	private int count;

	public Book() {
	}

	public Book(String isbn, String bookName, String bookType, String author, String publisher, Date instoreTime,
			Boolean isRent, int count) {
		// super();
		this.isbn = isbn;
		this.bookName = bookName;
		this.bookType = bookType;
		this.author = author;
		this.publisher = publisher;
		this.instoreTime = instoreTime;
		this.isRent = isRent;
		this.count = count;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getBookType() {
		return bookType;
	}

	public void setBookType(String bookType) {
		this.bookType = bookType;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public Date getInstoreTime() {
		return instoreTime;
	}

	public void setInstoreTime(Date instoreTime) {
		this.instoreTime = instoreTime;
	}

	public Boolean getIsRent() {
		return isRent;
	}

	public void setIsRent(Boolean isRent) {
		this.isRent = isRent;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "Book [isbn=" + isbn + ", bookName=" + bookName + ", bookType=" + bookType + ", author=" + author
				+ ", publisher=" + publisher + ", instoreTime=" + instoreTime + ", isRent=" + isRent + ", count="
				+ count + "]";
	}

	/**
	 * 根据isbn确定图书
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof Book))
			return false;
		Book book = (Book) obj;
		return this.isbn.equals(book.getIsbn());
	}

}
