package com.librarymanagement;

import java.util.Scanner;

// Main Class
public class Start {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		boolean runAgain;

		do {
			LibraryManagementSystem lms = new LibraryManagementSystem();
			System.out.println("These 5 books are added by default:");
			lms.addBook(new Book("Java Programming", "John Doe", "9781234567890", "Programming", 5));
			lms.addBook(new Book("Python Basics", "Jane Smith", "9780987654321", "Programming", 3));
			lms.addBook(new Book("Clean Code", "Robert C. Martin", "9780132350884", "Software Engineering", 4));
			lms.addBook(
					new Book("The Pragmatic Programmer", "Andrew Hunt", "9780201616224", "Software Engineering", 2));
			lms.addBook(new Book("Design Patterns", "Erich Gamma", "9780201633610", "Programming", 3));

			int choice;
			do {
				System.out.println("\n----- Library Management System -----");
				System.out.println("1. Add Book");
				System.out.println("2. Remove Book");
				System.out.println("3. Update Book Quantity");
				System.out.println("4. Add Borrower");
				System.out.println("5. Remove Borrower");
				System.out.println("6. Borrow Book");
				System.out.println("7. Return Book");
				System.out.println("8. Search Book");
				System.out.println("9. Exit");
				System.out.println("10. List All Available Books");

				System.out.print("Enter your choice: ");
				choice = scanner.nextInt();
				scanner.nextLine(); // Consume the newline character

				switch (choice) {
				case 1: // Add Book
					System.out.print("Enter title: ");
					String title = scanner.nextLine();
					System.out.print("Enter author: ");
					String author = scanner.nextLine();
					System.out.print("Enter ISBN: ");
					String isbn = scanner.nextLine();
					System.out.print("Enter genre: ");
					String genre = scanner.nextLine();
					System.out.print("Enter quantity: ");
					int quantity = scanner.nextInt();
					lms.addBook(new Book(title, author, isbn, genre, quantity));
					break;

				case 2: // Remove Book
					System.out.print("Enter ISBN of the book to remove: ");
					String removeIsbn = scanner.nextLine();
					lms.removeBook(removeIsbn);
					break;

				case 3: // Update Book Quantity
					System.out.print("Enter ISBN of the book to update: ");
					String updateIsbn = scanner.nextLine();
					System.out.print("Enter new quantity: ");
					int newQuantity = scanner.nextInt();
					lms.updateBook(updateIsbn, newQuantity);
					break;

				case 4: // Add Borrower
					System.out.print("Enter borrower's name: ");
					String borrowerName = scanner.nextLine();
					System.out.print("Enter borrower's contact details: ");
					String contactDetails = scanner.nextLine();
					System.out.print("Enter borrower's membership ID: ");
					String membershipId = scanner.nextLine();
					lms.addBorrower(new Borrower(borrowerName, contactDetails, membershipId));
					break;

				case 5: // Remove Borrower
					System.out.print("Enter membership ID of the borrower to remove: ");
					String removeMembershipId = scanner.nextLine();
					lms.removeBorrower(removeMembershipId);
					break;

				case 6: // Borrow Book
					System.out.print("Enter ISBN of the book to borrow: ");
					String borrowIsbn = scanner.nextLine();
					System.out.print("Enter borrower's membership ID: ");
					String borrowMembershipId = scanner.nextLine();
					System.out.print("Enter due date (YYYY-MM-DD): ");
					String dueDate = scanner.nextLine();
					lms.borrowBook(borrowIsbn, borrowMembershipId, dueDate);
					break;

				case 7: // Return Book
					System.out.print("Enter ISBN of the book to return: ");
					String returnIsbn = scanner.nextLine();
					System.out.print("Enter borrower's membership ID: ");
					String returnMembershipId = scanner.nextLine();
					lms.returnBook(returnIsbn, returnMembershipId);
					break;

				case 8: // Search Book
					System.out.print("Enter search query (title, author, or genre): ");
					String query = scanner.nextLine();
					lms.searchBook(query);
					break;

				case 9: // Exit
					System.out.println("Exiting the Library Management System.");
					break;

				case 10: // List All Available Books
					lms.listAllBooks();
					break;

				default:
					System.out.println("Invalid choice! Please try again.");
				}
			} while (choice != 9);

			System.out.print("Do you want to start again? (yes/no): ");
			String restartChoice = scanner.nextLine().trim().toLowerCase();
			runAgain = restartChoice.equals("yes");

		} while (runAgain);

		System.out.println("Goodbye");
		scanner.close();
	}
}
