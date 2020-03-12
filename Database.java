
package library_cse104;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Comparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Comparator;

public class Database {
	public static ArrayList<Book> database = new ArrayList<Book>(); // This ArrayList is used to store the
																	// book-instances added by user.
	public static ArrayList<Book> asslist = new ArrayList<Book>();// This ArrayLisit is used to store the
																	// book-instances which assist the Search and Delete
																	// functions of library.

	public static void test() {

		Book book1 = new Book("7302061866", "Data Structure", "Michael Main", "IT", 20, 18);  
		Book book2 = new Book("4102061866", "Interaction of colour", "Josef Albers", "Arts", 15, 8);
		Book book3 = new Book("7302061967", "Data Structure II", "Michael Main", "IT", 20, 20);
		Book book4 = new Book("7302061000", "Algorithm and Data Structure", "Paul Ernest", "IT", 18, 8);
		Book book5 = new Book("7302061566", "Arts Class", "Micheal Main", "Arts", 20, 19);

		database.add(book1);
		database.add(book2);
		database.add(book3);
		database.add(book4);
		database.add(book5);

	}

	// This method is used to sort the book arraylist in descending order, and print
	// the list.
	public static void DescendingSort(ArrayList<Book> list) {
		Collections.sort(list, new Comp1());
		printLibrary(list);
	}

	// This method is used to sort the book arraylist according to the category, and
	// print the list.
	public static void CategorySort() {
		Collections.sort(database, new Comp2());
		printLibrary(database);
	}

	// This method is used to sort the book arraylist according to the author, and
	// print the list.
	public static void AuthorSort() {
		Collections.sort(database, new Comp3());
		printLibrary(database);
	}

	// This method is used to print the books in the particular list, with
	// Table-Format.
	public static void printLibrary(ArrayList<Book> list) {
		System.out.println(String.format("%-20s", "ISBN") + String.format("%-35s", "Title")
				+ String.format("%-25s", "Author") + String.format("%-20s", "Category")
				+ String.format("%-20s", "CopyNumber") + String.format("%-20s", "CopyforLending"));

		Iterator<Book> itr = list.iterator();// Create iterator, to iterate the book list.
		while (itr.hasNext()) {
			System.out.println(itr.next().toString());// It calls the toString() method in Book class. It organize the
														// basic information of book instance in Table-Format
		}
	}

	// This static class is used to create a sort class, to sort the
	// list in descending order.
	static class Comp1 implements Comparator<Object> { // Create a comparator to sort the objects.

		@Override
		public int compare(Object o1, Object o2) { // Create a method specially for sorting books.
			Book book1 = (Book) o1;
			Book book2 = (Book) o2;

			if (book1.getCopyNumber() < book2.getCopyNumber()) {
				return 1;// If 1st one smaller than 2nd one, return 1, and swtich the position of the 1st
							// one and 2nd one.
			} else if (book1.getCopyNumber() > book2.getCopyNumber()) {
				return -1;// If 1st one larger than 2nd one, return -1, and do not switch position.
			} else {// If 1st==2nd, compare the value of AVAILABLE BOOKS.
				if (book1.getCopyforLending() < book2.getCopyforLending()) {
					return 1;
				} else {
					return -1;
				}
			}

		}

	}

	// This static class is used to create a sort class, to sort the list according
	// to the category.
	static class Comp2 implements Comparator<Object> {
		public int compare(Object object1, Object object2) {
			Book b1 = (Book) object1;
			Book b2 = (Book) object2;
			return b1.getCategory().compareTo(b2.getCategory()); // Use .compareTo method to compare the relationship
																	// between different books on category.
		}
	}

	// This static class is used to create a sort class, to sort the list according
	// to the author.
	static class Comp3 implements Comparator<Object> {
		public int compare(Object object1, Object object2) {
			Book b1 = (Book) object1;
			Book b2 = (Book) object2;
			return b1.getAuthor().compareTo(b2.getAuthor()); // Use .compareTo method to compare the relationship
																// between different books on author.
		}

	}

	// This method is used to determine whether input ISBN is in the database.
	// return void
	public static void ISBN_Search(String keyword) {
		for (int i = 0; i < database.size(); i++) {
			if (database.get(i).getISBN().equals(keyword)) {
				asslist.add(database.get(i));
			}
		}
	}

	// This method is used to determine whether input title is in the database.
	public static void Title_Search(String keyword) {
		for (int i = 0; i < database.size(); i++) {
			if (database.get(i).getTitle().toUpperCase().contains(keyword.toUpperCase())) {
				asslist.add(database.get(i));
			}
		}
	}

	// This method is used to determine whether input author is in the database.
	public static void Author_Search(String keyword) {
		for (int i = 0; i < database.size(); i++) {
			if (database.get(i).getAuthor().toUpperCase().contains(keyword.toUpperCase())) {
				asslist.add(database.get(i));
			}
		}
	}

	// This method is used to determine whether input category is in the database.
	public static void Category_Search(String keyword) {
		for (int i = 0; i < database.size(); i++) {
			if (database.get(i).getCategory().toUpperCase().contains(keyword.toUpperCase())) {
				asslist.add(database.get(i));
			}
		}
	}

	// This method is used to determine whether input CopyNumber is in the database.
	public static void Copy_Search(int keyword) {
		for (int i = 0; i < database.size(); i++) {
			if (database.get(i).getCopyNumber() == keyword) {
				asslist.add(database.get(i));
			}
		}
	}

	// This method is used to determine whether input available number is in the
	// database.
	public static void Remain_Search(int keyword) {
		for (int i = 0; i < database.size(); i++) {
			if (database.get(i).getCopyforLending() == keyword) {
				asslist.add(database.get(i));
			}
		}
	}

	// This method is used to do the comprehensive search. Other methods used for
	// specific search are called in this method.
	public static void Comprehensive_Search(String keyword) {
		ISBN_Search(keyword);
		Title_Search(keyword);
		Author_Search(keyword);
		Category_Search(keyword);
		if (UserInterface.isNumeric(keyword)) {
			int k = Integer.valueOf(keyword);
			Copy_Search(k);
			Remain_Search(k);
		}
	}

	// This methood is used for figuring out whether the book with INPUT ISBN has
	// existed in the database.
	// return Boolean
	public static boolean Search_isbn(String ISBN) {
		for (int i = 0; i < database.size(); i++) {
			if (database.get(i).getISBN().equals(ISBN)) {
				return true;
			}
		}
		return false;
	}

	// This method is used to verify whether the book with INPUT TITLE and AUTHOR
	// has existed in the database.
	public static boolean Search_Title_Author(String title, String author) {
		for (int i = 0; i < database.size(); i++) {
			if (database.get(i).getTitle().equalsIgnoreCase(title)) {
				if (database.get(i).getAuthor().equalsIgnoreCase(author)) {
					return true;
				}
			}
		}
		return false;
	}

	// This method is used to delete the existing book in the library according to
	// the input ISBN.
	public static void ISBN_Delete1(String input) {
		if (input.length() != 10 & input.length() != 13) { // Verify the validy of the ISBN
			System.out.println("The input is out of range. Please input again.");
			input = UserInterface.ISBN_Verification();
		}
		ISBN_Delete2(input);
		System.out.println("Enter 'Y' to delete the book. Anything else to quit");
		if (UserInterface.Input_Verification() == true) {
			for (int i = 0; i < database.size(); i++) {
				if (database.get(i).getISBN().equals(input)) {
					database.remove(i);// Delete the books from the database.
				}
			}
			System.out.println("Book with ISBN " + input + " has been deleted successfully.");
			asslist.clear();
			UserInterface.showMenu();
		} else {
			UserInterface.showMenu();
		}
	}

	// This methood is used for assisting deleting books.
	public static void ISBN_Delete2(String ISBN) {
		int a = 0;
		int sub = 0;
		for (int i = 0; i < database.size(); i++) {
			if (database.get(i).getISBN().equals(ISBN)) {
				asslist.add(database.get(i));
				a = i;
			}
		}
		if (asslist.size() != 0) {
			if (database.get(a).getCopyNumber() != database.get(a).getCopyforLending()) {
				sub = database.get(a).getCopyNumber() - database.get(a).getCopyforLending();
				DescendingSort(asslist);
				System.out.println(
						"Sorry this book cannot be deleted. " + "There are " + sub + " copies have been lent out.");// To
																													// determine
																													// the
																													// lending
																													// siuation
																													// of
																													// book
																													// which
																													// want
																													// to
																													// delete
				Database.asslist.clear();
				UserInterface.showMenu();
			} else {
				DescendingSort(asslist);
			}
		} else {
			System.out.println("There is no matched item.");
			Database.asslist.clear();
			UserInterface.DeleteBook();
		}
	}

	// This method is used to delete the existing book in the library according to
	// the input Title and Author.
	public static void Title_Author_Delete(String input) {
		int a = 0;
		int sub = 0;
		for (int i = 0; i < database.size(); i++) {
			String book = database.get(i).getTitle() + "+" + database.get(i).getAuthor();
			if (input.equalsIgnoreCase(book)) {
				asslist.add(database.get(i));
				a = i;
			}
		}

		if (asslist.size() != 0) {
			if (database.get(a).getCopyNumber() != database.get(a).getCopyforLending()) {// To determine the lending
																							// siuation of book which
																							// want to delete
				sub = database.get(a).getCopyNumber() - database.get(a).getCopyforLending();
				DescendingSort(asslist);
				System.out.println(
						"Sorry this book cannot be deleted. " + "There are " + sub + " copies have been lent out.");
				Database.asslist.clear();
				UserInterface.showMenu();
			} else {
				DescendingSort(asslist);
				System.out.println("Enter 'Y' to delete the book. Anything else to quit");
				if (UserInterface.Input_Verification() == true) {
					database.remove(a);
					System.out.println("Book has been deleted successfully.");
					asslist.clear();
					UserInterface.showMenu();
				} else {
					UserInterface.showMenu();
				}
			}
		} else {
			System.out.println("There is no matched item.");
			Database.asslist.clear();
			UserInterface.DeleteBook();
		}
	}

	// This method is used to delete book (by ISBN).
	public static void Delete_Book(String ISBN) {
		for (int i = 0; i < database.size(); i++) {
			if (database.get(i).getISBN().equals(ISBN)) {
				database.remove(i);
			}
		}
	}

	// This method is used to update the title of existing book in the library.
	public static void Update_Title() {
		System.out.println("Enter the new title: ");
		String input = UserInterface.Input_String();
		String oldtitle = asslist.get(0).getTitle();
		String isbn = asslist.get(0).getISBN();
		if (oldtitle.equalsIgnoreCase(input)) { // If the input title is same as the old one, ask user to input again.
			System.out.println("Please enter the new title.");
			Update_Title();
		} else {
			asslist.get(0).setTitle(input);
			System.out.println("Book " + isbn + "'s title has been updated from " + oldtitle + " to " + input);

		}
		;
	}

	// This method is used to update the author of existing book in the library.
	public static void Update_Author() {
		System.out.println("Enter the new author: ");
		String input = UserInterface.Input_String();
		String oldauthor = asslist.get(0).getAuthor();
		String isbn = asslist.get(0).getISBN();
		if (oldauthor.equalsIgnoreCase(input)) {// If the input author is same as the old one, ask user to input again.
			System.out.println("Please enter the new author.");
			Update_Author();
		} else {
			asslist.get(0).setAuthor(input);
			System.out.println("Book " + isbn + "'s author has been updated from " + oldauthor + " to " + input);

		}
		;
	}

	// This method is used to update the category of existing book in the library.
	public static void Update_Category() {
		String input = UserInterface.Category_Verification();
		String oldcategory = asslist.get(0).getCategory();
		String isbn = asslist.get(0).getISBN();
		if (oldcategory.equalsIgnoreCase(input)) {// If the input category is same as the old one, ask user to input
													// again.
			System.out.println("Please enter the new category.");
			Update_Category();
		} else {
			asslist.get(0).setCategory(input);
			System.out.println("Book " + isbn + "'s category has been updated from " + oldcategory + " to " + input);

		}
		;
	}

	// This method is used to update the CopyNumber of existing book in the library.
	public static void Update_CopyNumber() {
		System.out.println("Please enter new copy number.");
		int input = UserInterface.CopyNumber_Verification();
		int oldCN = asslist.get(0).getCopyNumber();
		String isbn = asslist.get(0).getISBN();
		if (input == oldCN) {// If the input CopyNumber is same as the old one, ask user to input again.
			System.out.println("Please enter the new copynumber (0-20).");
			Update_CopyNumber();
		} else {
			asslist.get(0).setCopyNumber(input);
			System.out.println("Book " + isbn + "'s copy number has been updated from " + oldCN + " to " + input);

		}
		;
	}

	// This method is used to update the available number of existing book in the
	// library.
	public static void Update_Remain() {
		System.out.println("Please enter new number of available copy.");
		int input = UserInterface.CopyNumber_Verification();
		int oldCN = asslist.get(0).getCopyNumber();
		int oldRM = asslist.get(0).getCopyforLending();
		String isbn = asslist.get(0).getISBN();
		if (input == oldRM) {// If the input available number is same as the old one, ask user to input
								// again.
			System.out.println("Please enter the new remain.");
			Update_Remain();
		} else {
			if (input > oldCN) { // If the input RM is large than the CopyNumber, ask user to input again.
				System.out.println("Please enter the availabel numebr in the correct remain.");
				Update_Remain();
			} else {
				asslist.get(0).setCopyforLending(input);
				System.out
						.println("Book " + isbn + "'s available copy has been updated from " + oldRM + " to " + input);
			}
		}
	}
}
