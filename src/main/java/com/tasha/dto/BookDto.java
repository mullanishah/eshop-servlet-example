package com.tasha.dto;

/**
 * @author Shahrukh
 *
 */
public class BookDto {
	
	private int bookId;
	private String bookTitle, bookAuthor, bookCategory;
	private double bookPrice;
	
	public BookDto() {
		
	}

	/**
	 * @param bookTitle
	 * @param bookAuthor
	 * @param bookCategory
	 * @param bookPrice
	 */
	public BookDto(String bookTitle, String bookAuthor, String bookCategory, double bookPrice) {
		super();
		this.bookTitle = bookTitle;
		this.bookAuthor = bookAuthor;
		this.bookCategory = bookCategory;
		this.bookPrice = bookPrice;
	}

	/**
	 * @param bookId
	 * @param bookTitle
	 * @param bookAuthor
	 * @param bookCategory
	 * @param bookPrice
	 */
	public BookDto(int bookId, String bookTitle, String bookAuthor, String bookCategory, double bookPrice) {
		super();
		this.bookId = bookId;
		this.bookTitle = bookTitle;
		this.bookAuthor = bookAuthor;
		this.bookCategory = bookCategory;
		this.bookPrice = bookPrice;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public String getBookAuthor() {
		return bookAuthor;
	}

	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}

	public String getBookCategory() {
		return bookCategory;
	}

	public void setBookCategory(String bookCategory) {
		this.bookCategory = bookCategory;
	}

	public double getBookPrice() {
		return bookPrice;
	}

	public void setBookPrice(double bookPrice) {
		this.bookPrice = bookPrice;
	}

	@Override
	public String toString() {
		return "BookDTO [bookId=" + bookId + ", bookTitle=" + bookTitle + ", bookAuthor=" + bookAuthor
				+ ", bookCategory=" + bookCategory + ", bookPrice=" + bookPrice + "]";
	}
}
