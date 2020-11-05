import java.io.Serializable;

abstract class Product implements Serializable {
	private static final long serialVersionUID = 136420;
	int id;
	String title;
	int value;

	public Product(int id, String title, int value) {
		this.id = id;
		this.title = title;
		this.value = value;
	}

	public int getId() {
		return id;
	}

	public String getTitle() {
		return String.format("%s", title);
	}

}
//Moved Book here	
class Book extends Product implements Serializable {
		private static final long serialVersionUID = 136420;
		protected int pages;
		protected String publisher;

		public Book(int id, String title, int value, int pages, String publisher) {
			super(id, title, value);
			this.pages = pages;
			this.publisher = publisher;
			
		}
//		public Book(int id, String title, int value, int pages, String publisher) {
//			super(id, title, value);
//			this.pages = pages;
//			this.publisher = publisher;
//		}

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
//}

//Moved movie here
class Movie extends Product implements Serializable {
	private static final long serialVersionUID = 136420;
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
}

