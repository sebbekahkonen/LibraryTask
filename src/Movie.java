import java.io.Serializable;
import java.util.Iterator;

public class Movie implements Serializable {
	private static final int serialVersionUID = 136420;
	protected int id;
	protected String title;
	protected int value;
	protected int duration;
	protected double rating;

	public Movie(int id, String title, int value, int duration, double raiting) {
		this.id = id;
		this.title = title;
		this.value = value;
		this.duration = duration;
		this.rating = raiting;
	}
	public String getMoviesString() {
		return String.format("%s, (Movie)title: %s, value: %skr, duration: %s minutes, rating: %.1f", 
				id, title, value, duration, rating);
	}
	public String getTitle() {
		return String.format("%s", title);
	}
}
