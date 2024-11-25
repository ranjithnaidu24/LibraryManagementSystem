# Library Management System

This is a simple **Library Management System** built in Java to manage books, borrowers, and borrowing transactions. The system allows the librarian to perform various operations like adding/removing books, managing borrower details, and tracking borrowed books.

## Features
- **Book Management**: Add, remove, update the quantity of books.
- **Borrower Management**: Add and remove borrowers from the system.
- **Borrowing and Returning Books**: Borrow and return books with automatic tracking of due dates.
- **Book Search**: Search books by title, author, or ISBN.
- **View Borrowed Books**: View details of all borrowed books, including borrow and due dates.
- **Login**: Requires librarian login with predefined credentials before accessing the system.

## Login Credentials

To start using the system, log in using the following credentials:

- **Username**: librarian101
- **Password**: password01

## How to Run the Project

1. **Prerequisites**:
   - Ensure that you have **Java 8 or higher** installed on your machine.
   - If you don't have Java installed, you can download it from [here](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html).

2. **Steps to Run**:
-- Open the terminal or command prompt in the project folder.
compile and run Start.java
--Once the program runs, you will be prompted to log in. Use the login credentials provided above.

--After successful login, you will be able to access the **Main Menu** with options to:
   - Add/Remove books
   - Update book quantities
   - Manage borrowers
   - Borrow/Return books
   - Search books by title, ISBN, or author
   - View borrowed books and more.

## Classes in the Project

### 1. **Book Class**
   - Represents a book in the library.
   - Attributes: `title`, `author`, `isbn`, `genre`, `quantity`.
   - Methods: Getters and setters for each attribute, and a `toString` method for displaying book information.

### 2. **Borrower Class**
   - Represents a borrower in the library system.
   - Attributes: `name`, `contactDetails`, `membershipId`.
   - Methods: Getters for each attribute and a `toString` method for displaying borrower information.

### 3. **BorrowedBook Class**
   - Stores information about borrowed books.
   - Attributes: `isbn`, `borrowDate`, `dueDate`.
   - Methods: Getters for each attribute and a `toString` method for displaying borrowed book information.

### 4. **LibraryManagementSystem Class**
   - Contains the main logic for managing books, borrowers, and borrowed books.
   - Methods: Add, remove, and update books and borrowers, search books, borrow/return books, and view borrowed books.

### 5. **Start Class (Main)**
   - The main entry point of the application that handles the login logic and displays the main menu for the librarian to interact with.

## Example Usage

1. **Login**:
   - Enter the username: `librarian101`
   - Enter the password: `password01`

2. **Perform Operations**:
   - After logging in, choose an option from the menu, for example:
     - Add a book: Enter the book details.
     - Search a book by title: Enter the title to search.
     - Borrow a book: Enter the book ISBN and borrower membership ID.

3. **Exit**:
   - To exit the system, select the `Exit` option from the menu.
