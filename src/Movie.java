import java.io.Serializable;

public class Movie extends Product implements Serializable {
	private static final int serialVersionUID = 136420;
	protected int duration;
	protected double rating;

	public Movie(int id, String title, int value, int duration, double raiting) {
		super(id, title, value);
		this.duration = duration;
		this.rating = raiting;
	}

	public String toString() {
		return String.format("%s, (Movie)title: %s, value: %skr, duration: %s minutes, rating: %.1f", id, title, value,
				duration, rating);
	}

	public String getTitle() {
		return String.format("%s", title);
	}

	public int getId() {
		return id;
	}
}//hej
