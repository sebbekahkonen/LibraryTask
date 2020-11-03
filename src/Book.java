import java.io.Serializable;

public class Book extends Product implements Serializable {
	private static final int serialVersionUID = 136420;
	protected int pages;
	protected String publisher;

	public Book(int id, String title, int value, int pages, String publisher) {
		super(id, title, value);
		this.pages = pages;
		this.publisher = publisher;
	}

	// Getters
	public String toString() {
		return String.format("%s, (book)title: %s, value: %skr, pages: %s, author: %s", id, title, value, pages,
				publisher);
	}

	public String getTitle() {
		return String.format("%s", title);
	}

	public int getId() {
		return id;
	}
}