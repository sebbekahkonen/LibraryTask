public class Book{
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
		return String.format("%s, title: %s, value: %s, duration: %s, raiting: %s", 
				id, title, value, pages, publisher);
	} 
	public String getBooks() {
		return id+title+value+pages+publisher;
	}
}
