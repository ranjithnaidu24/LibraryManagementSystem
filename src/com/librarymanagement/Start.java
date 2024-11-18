package com.librarymanagement;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
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

// BorrowedBook Class to store information about borrowed books
class BorrowedBook {
	private String isbn;
	private String borrowDate;
	private String dueDate;

	public BorrowedBook(String isbn, String borrowDate, String dueDate) {
		this.isbn = isbn;
		this.borrowDate = borrowDate;
		this.dueDate = dueDate;
	}

	public String getIsbn() {
		return isbn;
	}

	public String getBorrowDate() {
		return borrowDate;
	}

	public String getDueDate() {
		return dueDate;
	}

	@Override
	public String toString() {
		return "BorrowedBook{" + "ISBN='" + isbn + '\'' + ", BorrowDate='" + borrowDate + '\'' + ", DueDate='" + dueDate
				+ '\'' + '}';
	}
}

// Library Management System
class LibraryManagementSystem {
	private final Map<String, Book> books = new HashMap<>();
	private final Map<String, Borrower> borrowers = new HashMap<>();
	private final Map<String, Map<String, BorrowedBook>> borrowedBooks = new HashMap<>();

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
			book.setQuantity(book.getQuantity() + quantity);
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
	public void borrowBook(String isbn, String membershipId) {
		Book book = books.get(isbn);
		Borrower borrower = borrowers.get(membershipId);

		if (book != null && borrower != null) {
			if (book.getQuantity() > 0) {
				// Calculate borrow and due date
				String borrowDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
				String dueDate = LocalDate.now().plusWeeks(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

				book.setQuantity(book.getQuantity() - 1);
				borrowedBooks.computeIfAbsent(membershipId, k -> new HashMap<>()).put(isbn,
						new BorrowedBook(isbn, borrowDate, dueDate));

				System.out.println("Book borrowed successfully by: " + borrower.getName());
				System.out.println("Due Date: " + dueDate + "(one week from issue date)");
			} else {
				System.out.println("Book not available.");
			}
		} else {
			System.out.println("Invalid book or borrower details.");
		}
	}

	public void returnBook(String isbn, String membershipId) {
		Map<String, BorrowedBook> borrowerBooks = borrowedBooks.get(membershipId);
		if (borrowerBooks != null && borrowerBooks.remove(isbn) != null) {
			books.get(isbn).setQuantity(books.get(isbn).getQuantity() + 1);
			System.out.println("Book returned successfully.");
		} else {
			System.out.println("Book was not borrowed or invalid details.");
		}
	}

	// Book Search
	public void searchBook(Scanner scanner) {
		System.out.println("Choose search criteria:");
		System.out.println("1. Search by Book Title");
		System.out.println("2. Search by ISBN");
		System.out.println("3. Search by Author Name");
		System.out.print("Enter your choice: ");
		int choice = scanner.nextInt();
		scanner.nextLine(); // Consume newline after the integer input

		switch (choice) {
		case 1:
			System.out.print("Enter book title to search: ");
			String title = scanner.nextLine();
			List<Book> titleResults = books.values().stream()
					.filter(book -> book.getTitle().toLowerCase().contains(title.toLowerCase()))
					.collect(Collectors.toList());
			if (titleResults.isEmpty()) {
				System.out.println("No books found with title: " + title);
			} else {
				titleResults.forEach(System.out::println);
			}
			break;

		case 2:
			System.out.print("Enter ISBN to search: ");
			String isbn = scanner.nextLine();
			Book isbnResult = books.get(isbn);
			if (isbnResult != null) {
				System.out.println(isbnResult);
			} else {
				System.out.println("No book found with ISBN: " + isbn);
			}
			break;

		case 3:
			System.out.print("Enter author name to search: ");
			String author = scanner.nextLine();
			List<Book> authorResults = books.values().stream()
					.filter(book -> book.getAuthor().toLowerCase().contains(author.toLowerCase()))
					.collect(Collectors.toList());
			if (authorResults.isEmpty()) {
				System.out.println("No books found by author: " + author);
			} else {
				authorResults.forEach(System.out::println);
			}
			break;

		default:
			System.out.println("Invalid choice! Please try again.");
			break;
		}
	}

	// List all Borrowers
	public void listAllBorrowers() {
		if (borrowers.isEmpty()) {
			System.out.println("No borrowers in the system.");
		} else {
			System.out.println("Borrowers in the system:");
			borrowers.values().forEach(System.out::println);
		}
	}

	// View borrowed books
	public void viewBorrowedBooks() {
		if (borrowedBooks.isEmpty()) {
			System.out.println("No books have been borrowed yet.");
		} else {
			System.out.println("Borrowed Books:");
			borrowedBooks.forEach((membershipId, borrowedBookMap) -> {
				Borrower borrower = borrowers.get(membershipId);
				if (borrower != null) {
					borrowedBookMap.forEach((isbn, borrowedBook) -> {
						Book book = books.get(isbn);
						if (book != null) {
							System.out.println("Borrower: " + borrower.getName());
							System.out.println("Book: " + book.getTitle());
							System.out.println("Author: " + book.getAuthor());
							System.out.println("ISBN: " + book.getIsbn());
							System.out.println("Borrow Date: " + borrowedBook.getBorrowDate());
							System.out.println("Due Date: " + borrowedBook.getDueDate());
							System.out.println("-----------------------------------");
						}
					});
				}
			});
		}
	}
}

// Main Class
public class Start {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		// Hardcoded login credentials for the librarian
		String username = "librarian101";
		String password = "password01";

		// Asking for the librarian login
		System.out.println("Please enter the librarian username: ");
		String enteredUsername = scanner.nextLine();

		System.out.println("Please enter the librarian password: ");
		String enteredPassword = scanner.nextLine();

		// Validate login credentials
		if (username.equals(enteredUsername) && password.equals(enteredPassword)) {
			System.out.println("Login successful!");
			// Proceed to main menu
			LibraryManagementSystem lms = new LibraryManagementSystem();
			System.out.println("These 5 books are added by default:");
			lms.addBook(new Book("Java Programming", "John Doe", "9781234567890", "Programming", 5));
			lms.addBook(new Book("Python Basics", "Jane Smith", "9780987654321", "Programming", 3));
			lms.addBook(new Book("Clean Code", "Robert C. Martin", "9780132350884", "Software Engineering", 4));
			lms.addBook(
					new Book("The Pragmatic Programmer", "Andrew Hunt", "9780201616224", "Software Engineering", 2));
			lms.addBook(new Book("Design Patterns", "Erich Gamma", "9780201633610", "Programming", 6));

			// Adding default borrowers Ajay and Jagadeesh
			lms.addBorrower(new Borrower("Ajay", "9876543210", "M001"));
			lms.addBorrower(new Borrower("Jagadeesh", "1234567890", "M002"));

			// Main menu loop
			while (true) {
				int choice;
				System.out.println("\nLibrary Management System");
				System.out.println("1. Add Book");
				System.out.println("2. Remove Book");
				System.out.println("3. Update Book Quantity");
				System.out.println("4. Add Borrower");
				System.out.println("5. Remove Borrower");
				System.out.println("6. Borrow Book");
				System.out.println("7. Return Book");
				System.out.println("8. Search Book");
				System.out.println("9. List All Available Books");
				System.out.println("10. View All Borrowers");
				System.out.println("11. View Borrowed Books");
				System.out.println("12. Exit");
				System.out.print("Enter your choice: ");
				choice = scanner.nextInt();
				scanner.nextLine(); // Consume newline

				switch (choice) {
				case 1:
					System.out.print("Enter book title: ");
					String title = scanner.nextLine();
					System.out.print("Enter book author: ");
					String author = scanner.nextLine();
					System.out.print("Enter book ISBN: ");
					String isbn = scanner.nextLine();
					System.out.print("Enter book genre: ");
					String genre = scanner.nextLine();
					System.out.print("Enter book quantity: ");
					int quantity = scanner.nextInt();
					scanner.nextLine(); // Consume newline
					lms.addBook(new Book(title, author, isbn, genre, quantity));
					break;

				case 2:
					System.out.print("Enter book ISBN to remove: ");
					String removeIsbn = scanner.nextLine();
					lms.removeBook(removeIsbn);
					break;

				case 3:
					System.out.print("Enter book ISBN to update quantity: ");
					String updateIsbn = scanner.nextLine();
					System.out.print("Enter quantity to add: ");
					int addQuantity = scanner.nextInt();
					scanner.nextLine(); // Consume newline
					lms.updateBook(updateIsbn, addQuantity);
					break;

				case 4:
					System.out.print("Enter borrower name: ");
					String borrowerName = scanner.nextLine();
					System.out.print("Enter borrower contact details: ");
					String contactDetails = scanner.nextLine();
					System.out.print("Enter borrower membership ID: ");
					String membershipId = scanner.nextLine();
					lms.addBorrower(new Borrower(borrowerName, contactDetails, membershipId));
					break;

				case 5:
					System.out.print("Enter borrower membership ID to remove: ");
					String removeMemberId = scanner.nextLine();
					lms.removeBorrower(removeMemberId);
					break;

				case 6:
					System.out.print("Enter book ISBN to borrow: ");
					String borrowIsbn = scanner.nextLine();
					System.out.print("Enter borrower membership ID: ");
					String borrowMemberId = scanner.nextLine();
					lms.borrowBook(borrowIsbn, borrowMemberId);
					break;

				case 7:
					System.out.print("Enter book ISBN to return: ");
					String returnIsbn = scanner.nextLine();
					System.out.print("Enter borrower membership ID: ");
					String returnMemberId = scanner.nextLine();
					lms.returnBook(returnIsbn, returnMemberId);
					break;

				case 8:
					lms.searchBook(scanner);
					break;

				case 9:
					lms.listAllBooks();
					break;

				case 10:
					lms.listAllBorrowers();
					break;

				case 11:
					lms.viewBorrowedBooks();
					break;

				case 12:
					System.out.println("Exiting the Library Management System.");
					scanner.close();
					return;

				default:
					System.out.println("Invalid choice! Please try again.");
				}
			}
		} else {
			System.out.println("Invalid credentials! Please try again.");
			scanner.close();
		}
	}
}
