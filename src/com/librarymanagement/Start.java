package com.librarymanagement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// Book Class
class Book {
	private String title;
	private String author;
	private String isbn;
	private String genre;
	private int quantity;

	public Book(String title, String author, String isbn, String genre, int quantity) {
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

// Borrower Class
class Borrower {
	private String name;
	private String contactDetails;
	private String membershipId;

	public Borrower(String name, String contactDetails, String membershipId) {
		this.name = name;
		this.contactDetails = contactDetails;
		this.membershipId = membershipId;
	}

	public String getName() {
		return name;
	}

	public String getMembershipId() {
		return membershipId;
	}

	@Override
	public String toString() {
		return "Borrower{" + "Name='" + name + '\'' + ", ContactDetails='" + contactDetails + '\'' + ", MembershipID='"
				+ membershipId + '\'' + '}';
	}
}

// Library Management System
class LibraryManagementSystem {
	private final Map<String, Book> books = new HashMap<>();
	private final Map<String, Borrower> borrowers = new HashMap<>();
	private final Map<String, Map<String, String>> borrowedBooks = new HashMap<>();

	// Book Management
	public void addBook(Book book) {
		books.put(book.getIsbn(), book);
		System.out.println("Book added: " + book);
	}

	public void updateBook(String isbn, int quantity) {
		Book book = books.get(isbn);
		if (book != null) {
			book.setQuantity(quantity);
			System.out.println("Updated book: " + book);
		} else {
			System.out.println("Book not found!");
		}
	}

	public void removeBook(String isbn) {
		if (books.remove(isbn) != null) {
			System.out.println("Book removed successfully.");
		} else {
			System.out.println("Book not found!");
		}
	}

	// Borrower Management
	public void addBorrower(Borrower borrower) {
		borrowers.put(borrower.getMembershipId(), borrower);
		System.out.println("Borrower added: " + borrower);
	}

	public void removeBorrower(String membershipId) {
		if (borrowers.remove(membershipId) != null) {
			System.out.println("Borrower removed successfully.");
		} else {
			System.out.println("Borrower not found!");
		}
	}

	// Book Borrowing
	public void borrowBook(String isbn, String membershipId, String dueDate) {
		Book book = books.get(isbn);
		Borrower borrower = borrowers.get(membershipId);

		if (book != null && borrower != null) {
			if (book.getQuantity() > 0) {
				book.setQuantity(book.getQuantity() - 1);
				borrowedBooks.computeIfAbsent(membershipId, k -> new HashMap<>()).put(isbn, dueDate);
				System.out.println("Book borrowed successfully by: " + borrower.getName());
			} else {
				System.out.println("Book not available.");
			}
		} else {
			System.out.println("Invalid book or borrower details.");
		}
	}

	public void returnBook(String isbn, String membershipId) {
		Map<String, String> borrowerBooks = borrowedBooks.get(membershipId);
		if (borrowerBooks != null && borrowerBooks.remove(isbn) != null) {
			books.get(isbn).setQuantity(books.get(isbn).getQuantity() + 1);
			System.out.println("Book returned successfully.");
		} else {
			System.out.println("Book was not borrowed or invalid details.");
		}
	}

	// Book Search
	public void searchBook(String query) {
		List<Book> results = books.values().stream()
				.filter(book -> book.getTitle().toLowerCase().contains(query.toLowerCase())
						|| book.getAuthor().toLowerCase().contains(query.toLowerCase())
						|| book.getGenre().toLowerCase().contains(query.toLowerCase()))
				.collect(Collectors.toList());

		if (results.isEmpty()) {
			System.out.println("No books found.");
		} else {
			results.forEach(System.out::println);
		}
	}
}

// Main Class
public class Start {
	public static void main(String[] args) {
		LibraryManagementSystem lms = new LibraryManagementSystem();

		// Adding Books
		lms.addBook(new Book("Java Programming", "John Doe", "ISBN001", "Programming", 5));
		lms.addBook(new Book("Python Basics", "Jane Smith", "ISBN002", "Programming", 3));

		// Adding Borrowers
		lms.addBorrower(new Borrower("Alice", "1234567890", "M001"));
		lms.addBorrower(new Borrower("Bob", "9876543210", "M002"));

		// Borrowing Books
		lms.borrowBook("ISBN001", "M001", "2024-11-30");

		// Returning Books
		lms.returnBook("ISBN001", "M001");

		// Searching Books
		lms.searchBook("Java");

		// Removing a Book
		lms.removeBook("ISBN001");

		// Removing a Borrower
		lms.removeBorrower("M001");
	}
}
