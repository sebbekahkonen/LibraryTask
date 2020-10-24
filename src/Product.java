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
	protected final Scanner scanner = new Scanner(System.in);
	protected static List<Movie> movies = new ArrayList<Movie>();
	protected static List<Book> books = new ArrayList<Book>();
	protected static List<Integer> saveid = new ArrayList<Integer>();

	public Product() {

	}

	// Dialogues
	public void dialogue(char c) {
		try {
			if (c == 'm') {
				int id;
				String idString;
				char firstInt;
				do	{
				System.out.print("(id for movie should start with\'5\') Enter id: ");
				id = scanner.nextInt();
				idString = Integer.toString(id);
				firstInt = idString.charAt(0);
				} while (firstInt != '5');
				System.out.print("Enter title: ");
				scanner.nextLine();
				String title = scanner.nextLine();
				System.out.print("Enter value: ");
				int value = scanner.nextInt();
				System.out.print("Enter duration: ");
				int duration = scanner.nextInt();
				System.out.print("Enter raiting: ");
				double raiting = scanner.nextDouble();
				if (raiting > 10 || raiting < 0) {

				}
				movies.add(movie = new Movie(id, title, value, duration, raiting));
				saveid.add(id);
			} else if (c == 'b') {
				System.out.print("Enter id: ");
				int id = scanner.nextInt();
				System.out.print("title: ");
				scanner.nextLine();
				String title = scanner.nextLine();
				System.out.print("Enter value: ");
				int value = scanner.nextInt();
				System.out.print("Enter pages: ");
				int pages = scanner.nextInt();
				System.out.print("Enter author: ");
				scanner.nextLine();
				String publisher = scanner.next();
				books.add(book = new Book(id, title, value, pages, publisher));
				saveid.add(id);
			} else {
				System.out.println("sorry that char isnt valid");
			}
		} catch (InputMismatchException e) {
			System.out.println("Invalid format, try again");
		}
	}

	// Search
	public void searchID(int id) {

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
	public void removeAtID(int id) {

	}

	public static void initializeBookList() {
		try {
			File file = new File("book_list.bin");
			FileInputStream fin = new FileInputStream(file);
			ObjectInputStream oin = new ObjectInputStream(fin);
			books = (List<Book>) oin.readObject();
			oin.close();
//			for (Book book : books) {
//				System.out.println(book.getBooksString());
//			}
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
	
	public static void initializeMovieList()	{
		try {
			File file = new File("movie_list.bin");
			FileInputStream fin = new FileInputStream(file);
			ObjectInputStream oin = new ObjectInputStream(fin);
			movies = (List<Movie>) oin.readObject();
			oin.close();
//			for (Movie movie : movies) {
//				System.out.println(movie.getMoviesString());
//			}
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
	
	public static void initializeIdList()	{
		try {
			File file = new File("id_list.bin");
			FileInputStream fin = new FileInputStream(file);
			ObjectInputStream oin = new ObjectInputStream(fin);
			saveid = (List<Integer>) oin.readObject();
			oin.close();
//			System.out.println(saveid);
			
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
	

	public static void saveBookList() {
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
	
