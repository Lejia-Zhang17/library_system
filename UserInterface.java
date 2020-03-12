
package library_cse104;
import java.util.Scanner;

// This class is used to create methods for users' operations.

public class UserInterface {
	private static Scanner kb;

	// This method is used to create basic UserInterface for users.
	public static void showMenu() {
		kb = new Scanner(System.in);
		kb.useDelimiter("\n"); // This is used to omit the disturb of "space" when input.
		System.out.println("Welcome to the library management system, functions provided include the following:");
		System.out.println("1. Add – to add a new book");
		System.out.println("2. Update – to update book info");
		System.out.println("3. Search - to enquire about book info");
		System.out.println("4. Delete - to delete a book");
		System.out.println("5. Display - to display book(s) info");
		System.out.println("6. Quit - to exit from the current level of interacions");

		int command = getIntInput1();
		switch (command) {
		case 1:
			AddISBN();
			break;
		case 2:
			UpdateBook();
			break;
		case 3:
			SearchBook();
			break;
		case 4:
			DeleteBook();
			break;
		case 5:
			DisplayBook();
			break;
		case 6:
			QuitLibrary();
			break;
		default:
			showMenu();
			break;
		}
	}

	// This method is used to add ISBN for adding a new book into library.
	public static String AddISBN() {
		System.out.println("Enter your command here (Enter 'Quit' at any time to exit from current level:)");
		System.out.println("Enter a new book ISBN: ");
		String isbn = ISBN_Verification(); // This used to verify the validity of input ISBN.
		if (Database.Search_isbn(isbn) == true) { // It calls the method in Database class. It is used to test whether
													// the book with INPUT ISBN has existed
													// in the database.
			System.out.println("This book has existed in the library");
			showMenu();
		} else {
			System.out.println("ISBN: " + isbn + (" Entered."));
			AddTitle(isbn);
		}

		return isbn;

	}

	// This method is used to add title for adding a new book into library.
	public static String AddTitle(String isbn) {
		System.out.println("Enter the title: ");
		String title = Input_String();
		System.out.println("Title: " + title + (" Entered."));
		AddAuthor(isbn, title);
		return title;
	}

	// This method is used to add adding author for adding a new book into library.
	public static void AddAuthor(String isbn, String title) {
		System.out.println("Enter the author: ");
		String author = Input_String();
		if (Database.Search_Title_Author(title, author) == true) { // It calls the method in Database class. It is used
																	// to test whether
																	// the book with INPUT TITLE and Author has existed
																	// in the database.

			System.out.println("Book " + title + " by " + author + " exists in system.");
			System.out.println("Please enter 'A' to re-enter Author or enter ‘T’ to re-enter title");
			String i = kb.nextLine();
			switch (i) {
			case "Quit":
			case "quit":
			case "QUIT":
				showMenu();
				break;
			case "T":
			case "t":
				AddTitle(isbn);
				break;
			case "A":
			case "a":
				AddAuthor(isbn, title);
			}
		}
		AddOthers(isbn, title, author);
	}

	// This method is used to adding category, copynumber for adding a new book into
	// library. Besides, user will decide whether add this book into library.
	public static void AddOthers(String isbn, String title, String author) {
		System.out.println("Enter category: ");
		String category = Category_Verification();
		System.out.println("Category： " + category + (" Entered."));
		System.out.println("Enter total copy number: ");
		int copynumber = CopyNumber_Verification();
		System.out.println("Total copy number " + copynumber + (" Entered."));
		System.out.println("Ready to add book: " + isbn + "; " + title + "; " + author + "; " + category + "; "
				+ copynumber + "; " + copynumber);
		System.out.println("Enter 'Y' to add new book. Anything else to quit: ");
		if (Input_Verification() == true) {
			Book new_book = new Book(isbn, title, author, category, copynumber, copynumber);
			Database.database.add(new_book);
			System.out.println("New book added successfully.");
			showMenu();
		} else {
			showMenu();
		}
	}

	// This method is used to update the information of existing book in library.
	public static void UpdateBook() {
		System.out.println("Enter ISBN "); // Ask user to input the ISBN of the book which would like to be updated.
		String isbn = ISBN_Verification();
		Database.ISBN_Search(isbn);
		if (Database.asslist.size() != 0) {
			System.out.println("Found book: ");
			Database.DescendingSort(Database.asslist);
		} else {
			System.out.println("There is no matched item.");
			showMenu();
		}

		System.out.println("Enter type of information you want to update");
		System.out.println("1. Update title");
		System.out.println("2. Update author");
		System.out.println("3. Update category");
		System.out.println("4. Update copy number");
		System.out.println("5. Update for availabe number");
		int command = getIntInput2();
		switch (command) {
		case 1:
			Database.Update_Title(); // Call the method in Database class to update specific information.
			Database.asslist.clear(); // Clear the asslist after every searching process.
			break;
		case 2:
			Database.Update_Author();
			Database.asslist.clear();
			break;
		case 3:
			Database.Update_Category();
			Database.asslist.clear();
			break;
		case 4:
			Database.Update_CopyNumber();
			Database.asslist.clear();
			break;
		case 5:
			Database.Update_Remain();
			Database.asslist.clear();
			break;
		default:
			UpdateBook();
			break;
		}
		showMenu();

	}

	// This method is used to search the existing books in the library.
	public static void SearchBook() {
		System.out.println("Please chooose one as your search field"); // Ask user to pick one specific field, or do the
																		// comprehensive search.
		System.out.println("1. ISBN");
		System.out.println("2. Title");
		System.out.println("3. Author");
		System.out.println("4. Category");
		System.out.println("5. Total copy of book");
		System.out.println("6. Copy of remaining book");
		System.out.println("7. Comprehensive Search");
		int command = getIntInput3();
		switch (command) {
		case 1:
			System.out.println("Enter your keyword: ");
			String keyword1 = ISBN_Verification();
			Database.ISBN_Search(keyword1); // It calls the method in Database class. This is used to verify whether
											// the book with input ISBN is in the library.
			if (Database.asslist.size() != 0) {
				System.out.println("Found book: ");
				Database.DescendingSort(Database.asslist);// Display the searched book in a descending order based on
															// the total copy number of the books.
			} else {
				System.out.println("There is no matched item.");// Show the warns if no matched books.
			}
			break;
		case 2:
			System.out.println("Enter your keyword: ");
			String keyword2 = Input_String();
			Database.Title_Search(keyword2);// It calls the method in Database class. This is used to verify whether
											// the book with input title is in the library.
			if (Database.asslist.size() != 0) {
				System.out.println("Found book: ");
				Database.DescendingSort(Database.asslist);
			} else {
				System.out.println("There is no matched item.");
			}
			break;
		case 3:
			System.out.println("Enter your keyword: ");
			String keyword3 = Input_String();// It calls the method in Database class. This is used to verify whether
												// the book with input author is in the library.
			Database.Author_Search(keyword3);
			if (Database.asslist.size() != 0) {
				System.out.println("Found book: ");
				Database.DescendingSort(Database.asslist);
			} else {
				System.out.println("There is no matched item.");
			}
			break;
		case 4:
			System.out.println("Enter your keyword: ");
			String keyword4 = Input_String();// It calls the method in Database class. This is used to verify whether
												// the book with input category is in the library.
			Database.Category_Search(keyword4);
			if (Database.asslist.size() != 0) {
				System.out.println("Found book: ");
				Database.DescendingSort(Database.asslist);
			} else {
				System.out.println("There is no matched item.");
			}
			break;
		case 5:
			System.out.println("Enter your keyword: ");
			int keyword5 = Int_Verification();
			Database.Copy_Search(keyword5); // It calls the method in Database class. This is used to verify whether
											// the book with input CopyNumber is in the library.
			if (Database.asslist.size() != 0) {
				System.out.println("Found book: ");
				Database.DescendingSort(Database.asslist);
			} else {
				System.out.println("There is no matched item.");
			}
			break;
		case 6:
			System.out.println("Enter your keyword: ");
			int keyword6 = Int_Verification();
			Database.Remain_Search(keyword6); // It calls the method in Database class. This is used to verify whether
												// the book with input available number is in the library.
			if (Database.asslist.size() != 0) {
				System.out.println("Found book: ");
				Database.DescendingSort(Database.asslist);
			} else {
				System.out.println("There is no matched item.");
			}
			break;
		case 7:
			System.out.println("Enter your keyword: ");
			String keyword7 = Input_String();
			Database.Comprehensive_Search(keyword7);// It calls the Comprehensive_Search method in the Database class.
			if (Database.asslist.size() != 0) {
				System.out.println("Found book: ");
				Database.DescendingSort(Database.asslist);
			} else {
				System.out.println("There is no matched item.");
			}
			break;

		default:
			SearchBook();
			break;
		}
		Database.asslist.clear();
		System.out.println("Enter 'Y' to search other books, anything else to quit: ");
		if (Input_Verification() == true) {
			SearchBook();
		} else {
			showMenu();
		}

	}

	// This method is used to delete the existing book in the library.
	public static void DeleteBook() {
		System.out.println("Enter the book's ISBN or title + author: ");
		String input = kb.nextLine();
		if (input.equalsIgnoreCase("quit")) {
			showMenu();
		} else {
			if (isNumeric(input) == true) { // Call the method in the Database class, TO determine whether
											// input is integer or string.
				Database.ISBN_Delete1(input);
				Database.asslist.clear();
			} else {
				Database.Title_Author_Delete(input);
				Database.asslist.clear();
			}
		}
	}

	// This methdo is used to display all books in the library according to the
	// user's instructionf.
	public static void DisplayBook() {
		System.out.println("Enter 'C' for displaying group by category, or 'A' for displaying group by author: ");
		String input = Input_String();
		switch (input) {
		case "C":
			Database.CategorySort(); // Call the method ini the Database class.
			break;
		case "c":
			Database.CategorySort();
			break;
		case "A":
			Database.AuthorSort();
			break;
		case "a":
			Database.AuthorSort();
			break;
		default:
			System.out.println("Please input correct letter.");
			DisplayBook();
			break;
		}
		showMenu();

	}

	// This methdo is used to quit the Library program.
	public static void QuitLibrary() {
		System.out.println("!!Quit the library management system!!");
		System.exit(0);// End the program.
	}

	// // This method is to input and test the input range (1-6).
	@SuppressWarnings("finally")
	public static int getIntInput1() {

		String input = kb.nextLine();
		int i = 0;
		if (input.equalsIgnoreCase("quit")) {
			showMenu();
			return 0;
		} else {
			try {
				i = Integer.parseInt(input);
			} catch (NumberFormatException e) {
				System.out.println("That is not an int; " + "please try again");
				i = getIntInput1();
			} finally {
				if (i > 6 || i < 1) {// Try to catch the range problem when user input the number out of the valid
										// range.
					System.out.println("The input is out of range. Please input again.");
					i = getIntInput1();
				}
				return i;
			}
		}
	}

	// This method is to input and test the input range (1-5).
	@SuppressWarnings("finally")
	public static int getIntInput2() {

		String input = kb.nextLine();
		int i = 0;
		if (input.equalsIgnoreCase("quit")) {
			showMenu();
			return 0;
		} else {
			try {
				i = Integer.parseInt(input);
			} catch (NumberFormatException e) {
				System.out.println("That is not an int; " + "please try again");
				i = getIntInput2();
			} finally {
				if (i > 5 || i < 1) {// Try to catch the range problem when user input the number out of the valid
										// range.
					System.out.println("The input is out of range. Please input again.");
					i = getIntInput2();
				}
				return i;
			}
		}
	}

	// This method is to input and test the input range (1-7).
	@SuppressWarnings("finally")
	public static int getIntInput3() {
		String input = kb.nextLine();
		int i = 0;
		if (input.equalsIgnoreCase("quit")) {
			showMenu();
			return 0;
		} else {
			try {
				i = Integer.parseInt(input);
			} catch (NumberFormatException e) {
				System.out.println("That is not an int; " + "please try again");
				i = getIntInput3();
			} finally {
				if (i > 7 || i < 1) {// Try to catch the range problem when user input the number out of the valid
										// range.
					System.out.println("The input is out of range. Please input again.");
					i = getIntInput3();
				}
				return i;
			}
		}

	}

	// This method is used to input String.
	public static String Input_String() {
		String s;
		s = kb.nextLine();
		if (s.equalsIgnoreCase("quit")) {
			showMenu();
			return "";
		}
		return s;
	}

	// This method is used to verify the validity of ISBN input (10 or 13).
	public static String ISBN_Verification() {
		String ISBN = null;
		ISBN = kb.nextLine();

		if (ISBN.equalsIgnoreCase("quit")) {
			showMenu();
			return "";
		} else {
			if (ISBN.length() != 10 & ISBN.length() != 13) { // Test whether ISBN is with valid range (10 or 13 digits).
				System.out.println("The input is out of range. Please input again.");
				ISBN = ISBN_Verification();
			}
			return ISBN;
		}
	}

	// This method is used to verify the validaty of category input.
	public static String Category_Verification() {
		System.out.println("Please choose one from categories below: ");
		System.out.println("1. Arts");
		System.out.println("2. Business");
		System.out.println("3. Comics");
		System.out.println("4. IT");
		System.out.println("5. Cooking");
		System.out.println("6. Sports");

		int command = getIntInput1();
		String category = null;
		switch (command) {
		case 1:
			category = "Arts";
			break;
		case 2:
			category = "Business";
			break;
		case 3:
			category = "Comics";
			break;
		case 4:
			category = "IT";
			break;
		case 5:
			category = "Cooking";
			break;
		case 6:
			category = "Sports";
			break;
		default:
			Category_Verification();
			break;
		}
		return category;
	}

	// This method is used to verify the validity of CopyNumber (0-20).
	@SuppressWarnings("finally")
	public static int CopyNumber_Verification() {
		String input = kb.nextLine();
		int i = 0;
		if (input.equalsIgnoreCase("quit")) {
			showMenu();
			return 0;
		} else {
			try {
				i = Integer.parseInt(input);
			} catch (NumberFormatException e) {
				System.out.println("That is not an int; " + "please try again");
				i = CopyNumber_Verification();
			} finally {
				if (i > 20 || i < 1) {// Try to catch the range problem when user input the number out of the valid
										// range.
					System.out.println("The input is out of range. Please input again.");
					i = CopyNumber_Verification();
				}
				return i;
			}
		}

	}

	// This method is used to input final decision.
	public static boolean Input_Verification() {
		String input = kb.nextLine();
		if (input.equalsIgnoreCase("quit")) {
			showMenu();
			return false;
		} else if (input.equalsIgnoreCase("Y")) {
			return true;
		} else {
			return false;
		}
	}

	@SuppressWarnings("finally")
	public static int Int_Verification() {
		String input = kb.nextLine();
		int i = 0;
		if (input.equalsIgnoreCase("quit")) {
			showMenu();
			return 0;
		} else {
			try {
				i = Integer.parseInt(input);
			} catch (NumberFormatException e) {
				System.out.println("That is not an int; " + "please try again");
				i = Int_Verification();
			} finally {
				return i;
			}

		}
	}

	// This method is used to determien whether input string is composed with
	// numbers.
	public static boolean isNumeric(String str) {
		for (int i = str.length(); --i >= 0;) {
			if (!Character.isDigit(str.charAt(i))) { // Using isDigit() method, it compares every digit in the string to
														// determine whether input string is composed with numbers.
				return false;
			}
		}
		return true;
	}
}
