package com.librarymanagement;

public class Book {
	private String title;
	private String author;
	private String isbn;
	private String genre;
	private int quantity;

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getAuthor() {
		return author;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getGenre() {
		return genre;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getQuantity() {
		return quantity;
	}

	public Book(String title, String author, String isbn, String genre, int quantity) {
		this.title = title;
		this.author = author;
		this.isbn = isbn;
		this.genre = genre;
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "Book [title=" + title + ", author=" + author + ", isbn=" + isbn + ", genre=" + genre + ", quantity="
				+ quantity + "]";
	}

}