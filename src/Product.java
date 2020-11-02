import java.util.ArrayList;
import java.util.List;

abstract class Product {
	int id;
	String title;
	int value;
	Book book;
	Movie movie;
	public Product(int id, String title, int value) {
		this.id = id;
		this.title = title;
		this.value = value;
	}
	public int getId()	{
		return id;
	}
	public String getBooksString() {
		return String.format("%s, (book)title: %s, value: %skr, pages: %s, author: %s", 
				id, title, value, book.pages, book.publisher);
	}
	public String getMoviesString() {
		return String.format("%s, (Movie)title: %s, value: %skr, duration: %s minutes, rating: %.1f", 
				id, title, value, movie.duration, movie.rating);
	}
	public String getTitle() {
		return String.format("%s", title);
	}
}