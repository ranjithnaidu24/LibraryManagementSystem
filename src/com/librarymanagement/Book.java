package com.librarymanagement;

//Book Class
public class Book {
	private String title;
	private String author;
	private String isbn;
	/*
	 * International Standard Book Number
	 */
	private String genre;
	private int quantity;

	public Book(String title, String author, String isbn, String genre, int quantity) {
		/*
		 * We can add an if condition here for ISBN that, only 13 digit ISBN number can
		 * be accepted
		 */
		this.title = title;
		this.author = author;
		this.isbn = isbn;
		this.genre = genre;
		this.quantity = quantity;
	}

	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}

	public String getIsbn() {
		return isbn;
	}

	public String getGenre() {
		return genre;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "Book{" + "Title='" + title + '\'' + ", Author='" + author + '\'' + ", ISBN='" + isbn + '\''
				+ ", Genre='" + genre + '\'' + ", Quantity=" + quantity + '}';
	}
}
