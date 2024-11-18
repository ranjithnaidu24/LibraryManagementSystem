package com.librarymanagement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//Library Management System
public class LibraryManagementSystem {
	private final Map<String, Book> books = new HashMap<>();
	private final Map<String, Borrower> borrowers = new HashMap<>();
	private final Map<String, Map<String, String>> borrowedBooks = new HashMap<>();

	// Book Management
	public void addBook(Book book) {
		books.put(book.getIsbn(), book);
		System.out.println("Book added: " + book);
	}

	// Display all books
	public void listAllBooks() {
		if (books.isEmpty()) {
			System.out.println("No books available in the library.");
		} else {
			System.out.println("Available books in the library:");
			books.values().forEach(System.out::println);
		}
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
