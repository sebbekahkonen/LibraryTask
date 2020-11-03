import java.io.Serializable;

abstract class Product implements Serializable {
	private static final int serialVersionUID = 136420;
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

	public int getId() {
		return id;
	}

	public String getTitle() {
		return String.format("%s", title);
	}
}//hej