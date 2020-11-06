import java.io.Serializable;

	/*Product is an abstract class that holds values for subclasses common value-parameters 
	 * product has constructor used by subclasses
	 * 
	 * @author Niklas Kullberg
	 * @author Sebastian Kahkonen
	 */
abstract class Product implements Serializable {
	private static final long serialVersionUID = 136420;
	
	/*id -- holds product id
	*title -- holds title of product
	*value -- holds value of product
	*/
	int id;
	String title;
	int value;
	
	/*constructor for product
	 * @param id sets this instance id
	 * @param title sets this instance title
	 * @param value sets this instance value
	 */
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
	/*Class Book extends class Product. 
	 * 	Construct Book via superclass Product adding further attributes pages & publisher
	 */
class Book extends Product implements Serializable {
		private static final long serialVersionUID = 136420;
		
		/*pages -- holds number of pages for this instance of Book
		 * publisher -- holds name of publisher for this instance of Book
		 */
		protected int pages;
		protected String publisher;
		
		/*Constructor for Book via superclass
		 * @param pages, holds unique parameter for instance of Book
		 * @param publisher, holds unique parameter for instance of Book
		 */
		public Book(int id, String title, int value, int pages, String publisher) {
			super(id, title, value);
			this.pages = pages;
			this.publisher = publisher;
			
		}

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

	/*Class Movie extends class Product
	 * Construct Movie via superclass Product adding further attributes duration & rating
	 */
class Movie extends Product implements Serializable {
	private static final long serialVersionUID = 136420;
	
	/*duration -- holds value duration for this instance of Movie
	 * rating -- holds value rating for this instance of Movie
	 */
	protected int duration;
	protected double rating;
	
	/*Construct Movie via superclass
	 *@param duration, holds unique parameter for instance of Movie
	 *@param rating, holds unique parameter for instance of Movie
	 */
	public Movie(int id, String title, int value, int duration, double rating) {
		super(id, title, value);
		this.duration = duration;
		this.rating = rating;
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

