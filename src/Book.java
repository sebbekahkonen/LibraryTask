import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.List;

public class Book implements Serializable{
	protected int id;
	protected String title;
	protected int value;
	protected int pages;
	protected String publisher;
	
	public Book(int id, String title, int value, int pages, String publisher) {
		this.id = id;
		this.title = title;
		this.value = value;
		this.pages = pages;
		this.publisher = publisher;
	}
	
	public String getBooksString() {
		return String.format("%s, (book)title: %s, value: %skr, pages: %s, author: %s", 
				id, title, value, pages, publisher);

	} 
	public String getBooks() {
		return id+title+value+pages+publisher;
	}
	
}
