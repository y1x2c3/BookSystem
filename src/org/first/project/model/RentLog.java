package org.first.project.model;
/**
 * 借阅表实体
 * @author 追风
 * @date 2019-8-6
 */

import java.sql.Date;

public class RentLog {
	private long bookCard;
	private String isbn;
	private Date rentDate;
	private Date returnDate;

	public RentLog() {
	}

	public RentLog(long bookCard, String isbn, Date rentDate, Date returnDate) {
		this.bookCard = bookCard;
		this.isbn = isbn;
		this.rentDate = rentDate;
		this.returnDate = returnDate;
	}

	public long getBookCard() {
		return bookCard;
	}

	public void setBookCard(long bookCard) {
		this.bookCard = bookCard;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public Date getRentDate() {
		return rentDate;
	}

	public void setRentDate(Date rentDate) {
		this.rentDate = rentDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

}
