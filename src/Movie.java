import java.io.Serializable;

public class Movie implements Serializable{
	protected int id;
	protected String title;
	protected int value;
	protected int duration;
	protected double raiting;

	public Movie(int id, String title, int value, int duration, float raiting) {
		this.id = id;
		this.title = title;
		this.value = value;
		this.duration = duration;
		this.raiting = raiting;
	}
	
	public String getMoviesString() {
		return String.format("%s, title: %s, value: %s, duration: %s, raiting: %s", 
				id, title, value, duration, raiting);
	}
	public String getMovies() {
		return id+title+value+duration+raiting;
	}
}
