
package library_cse104;
	// This class is used to generate Book instance.
public class Book {


	// Initialize the basic parameters for a book.
	private String ISBN;
	private String Title;
	private String Author;
	private String Category;
	private int CopyNumber;
	private int CopyforLending;

	// This is the constructor of Book class. It is used to distribute the value of
	// input parameter to initialize an instance.
	public Book(String ISBN, String Title, String Author, String Category, int CopyNumber, int CopyforLending) {
		this.ISBN = ISBN;
		this.Title = Title;
		this.Author = Author;
		this.Category = Category;
		this.CopyNumber = CopyNumber;
		this.CopyforLending = CopyforLending;
	}

	
	// These getXXXX() methods are used to be return the particular parameter of
	// Book instance. It could be called in other classes.
	public String getISBN() {
		return ISBN;
	}

	public String getTitle() {
		return Title;
	}

	public String getAuthor() {
		return Author;
	}

	public String getCategory() {
		return Category;
	}

	public int getCopyNumber() {
		return CopyNumber;
	}

	public int getCopyforLending() {
		return CopyforLending;
	}
	
	
	// These setXXXX() methods is used to update the values of Book's parameters.
	public void setTitle(String Title) {
		this.Title = Title; 
	}

	public void setAuthor(String Author) {
		this.Author = Author;
	}

	public void setCategory(String Category) {
		this.Category = Category;
	}

	public void setCopyNumber(int CopyNumber) {
		this.CopyNumber = CopyNumber;
	}

	public void setCopyforLending(int CopyforLending) {
		this.CopyforLending = CopyforLending;
	}

	
	// This method is used to organize the Book's information in table-format, which is used for further output.
	@Override
	public String toString() {
		return String.format("%-20s", ISBN) + String.format("%-35s", Title) + String.format("%-25s", Author)
				+ String.format("%-20s", Category) + String.format("%-20s", CopyNumber)
				+ String.format("%-20s", CopyforLending);
	}

}

