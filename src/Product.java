import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Product implements Serializable {
	protected Book book;
	protected Movie movie;
	protected Scanner scanner = new Scanner(System.in);
	protected static List<Movie> movies = new ArrayList<Movie>();
	protected static List<Book> books = new ArrayList<Book>();
	protected static List<Integer> saveid = new ArrayList<Integer>();

	public Product(char c) {
		if (c == 'b') {
			dialogueBook();
		} else if (c == 'm') {
			dialogueMovie();
		} else {
			System.out.println("sorry that char isnt valid");
		}
	}
	
	//Dialogues
	public void dialogueMovie() {
		try {
		System.out.println("id");
		int id = scanner.nextInt();
		System.out.println("title:");
		String title = scanner.next();
		System.out.println("value:");
		int value = scanner.nextInt();
		System.out.println("pages:");
		int duration = scanner.nextInt();
		System.out.println("raiting:");
		float raiting = scanner.nextFloat();
		if(raiting > 10 || raiting < 0) {
			
		}
		movies.add(movie = new Movie(id, title, value, duration, raiting));
		saveid.add(id);
		}catch(InputMismatchException e) {
			System.out.println("Invalid format, try again");
		}
	}
	public void dialogueBook() {
		try {
		System.out.println("id");
		int id = scanner.nextInt();
		System.out.println("title:");
		String title = scanner.next();
		System.out.println("value:");
		int value = scanner.nextInt();
		System.out.println("pages:");
		int pages = scanner.nextInt();
		System.out.println("author:");
		String publisher = scanner.next();
		books.add(book = new Book(id, title, value, pages, publisher));
		saveid.add(id);
		//saveBookList();
		}catch(InputMismatchException e) {
			System.out.println("Invalid format, try again");
		}
	}

	public void searchID(int id) {
		int index = movies.indexOf(id);
		String idToString = Integer.toString(id);
		System.out.println(index);	
	}

	// Getters
	public void getID() {
		for (int id : saveid) {
			System.out.println(id);
		}
	}
	public void getBooksAndMovies() {
		for (Book book : books)
			System.out.println(book.getBooksString());
		for (Movie movie : movies)
			System.out.println(movie.getMoviesString());
		}
	
	
	// Remove
	public void removeatID(int id) {
		
	}
	
	public static void initializeBookList()	{
		try {
			File file = new File("book_list.bin");
			FileInputStream fin = new FileInputStream(file);
			ObjectInputStream oin = new ObjectInputStream(fin);
			books = (List<Book>) oin.readObject();
			oin.close();
			System.out.println(books.get(0).getBooksString());
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void saveBookList()	{
		File file = new File("book_list.bin");
		FileOutputStream fout;
		try {
			fout = new FileOutputStream(file);
			ObjectOutputStream out = new ObjectOutputStream(fout);
			out.writeObject(books);
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
	
	
