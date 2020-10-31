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
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Product implements Serializable {
	private static final int serialVersionUID = 136420;
	protected Book book;
	protected Movie movie;
	protected Customer customer;
	protected static InitializersAndSavers saveOrInit = new InitializersAndSavers();
	protected static List<Movie> movies = new ArrayList<Movie>();
	protected static List<Book> books = new ArrayList<Book>();
	protected static List<Integer> saveid = new ArrayList<Integer>();
	protected static List<Integer> unAvailableProducts = new ArrayList<Integer>();

	public Product() {
	}
	
	// Dialogues
	public void registerDialogue(char c){
		try {
			Scanner userinput = new Scanner(System.in);
			if (c == 'm') {
				int id = 0;
				String idString = null;
				char firstInt = 0;
				do	{
				System.out.print("(id for movie should start with\'5\') Enter id: ");
				id = userinput.nextInt();
				if(saveid.contains(id)) {
					System.out.println("Product with id: \""+id+"\" already exists");
					return;
				}
				idString = Integer.toString(id);
				firstInt = idString.charAt(0);
				} while (firstInt != '5');
				System.out.print("Enter title: ");
				userinput.nextLine();
				String title = userinput.nextLine();
				System.out.print("Enter value: ");
				int value = userinput.nextInt();
				System.out.print("Enter duration: ");
				int duration = userinput.nextInt();
				System.out.print("Enter raiting: ");
				double raiting = userinput.nextDouble();
				if (raiting > 10 || raiting < 0) {

				}
				movies.add(movie = new Movie(id, title, value, duration, raiting));
				saveid.add(id);
				saveOrInit.saveIdList();
				saveOrInit.saveMovieList();
			} else if (c == 'b') {
				int id = 0;
				String idString = null;
				char firstInt = 0;
				do	{
				System.out.print("(Id for book should start with \'1\') Enter id: ");
				id = userinput.nextInt();
				if(saveid.contains(id)) {
					System.out.println("Product with id: \""+id+"\" already exists");
					return;
				}
				idString =Integer.toString(id);
				firstInt = idString.charAt(0);
				} while (firstInt != '1');
				System.out.print("title: ");
				userinput.nextLine();
				String title = userinput.nextLine();
				System.out.print("Enter value: ");
				int value = userinput.nextInt();
				System.out.print("Enter pages: ");
				int pages = userinput.nextInt();
				System.out.print("Enter author: ");
				userinput.nextLine();
				String publisher = userinput.next();
				books.add(book = new Book(id, title, value, pages, publisher));
				saveid.add(id);
				saveOrInit.saveIdList();
				saveOrInit.saveBookList();
			} else {
				System.out.println("Invalid charinput, try again");
				return;
			}
		} catch (InputMismatchException e) {
			System.out.println("Invalid format, try again");
		}
	}

	// Search
	public void searchID(int id) {
		if (!(saveid.contains(id)))	{
			System.out.println("Product with ID: " + id + " does not exist, try again.");
			return;
		}
		String targetId = Integer.toString(id);
		char firstInTarget = targetId.charAt(0);
		if (firstInTarget == '1')	{
			for (Book book : books)	{
				if (book.id == id)	{
					System.out.println(book.getBooksString());
				}
			}
		} else if (firstInTarget == '5')	{
			for (Movie movie : movies)	{
				if (movie.id == id)	{
					System.out.println(movie.getMoviesString());
				}
			}
		}
	}
	public void productReturn(int id) {
		if (unAvailableProducts.contains(id)) {
			for (Integer product : unAvailableProducts) {
				if (product == id) {
					break;
				}
			}
			unAvailableProducts.remove(Integer.valueOf(id));
		} else if (!saveid.contains(id)) {
			System.out.println("Product with id: \"" + id + "\" does not exist");
		} else {
			System.out.println("Product with id: \"" + id + "\" isnt borrowed out");
			return;
		}
		Iterator<Customer> iter = customer.customerList.iterator();
		while (iter.hasNext()) {
			Customer target = iter.next();
			if (target.borrowedProducts.contains(id)) {
				target.borrowedProducts.remove(Integer.valueOf(id));
				String idtoString = Integer.valueOf(id).toString();
				for (Book book : books) {
					if (book.getBooksString().contains(idtoString)) {
						System.out.println("You have sucessfully returned " + book.title + "");
						return;
					}
					for (Movie movie : movies) {
						if (movie.getMoviesString().contains(idtoString)) {
							System.out.println("You have sucessfully returned " + movie.title + "");
							return;
						}
					}
				}
				System.out.println("you have successfully returned " + id + "");
				return;
			}
			if (!iter.hasNext()) {
				System.out.println("there is no such product in \"" + target.name + "\" list");
				break;
			}
		}

	}
	public void productBorrow(int id) {
		try {
			Scanner inputScanner = new Scanner(System.in);
			String idtoString = Integer.valueOf(id).toString();
			if (!(saveid.contains(id))) {
				System.out.println("Product with ID: " + id + " does not exist, try again.");
				return;
			}
			String targetId = Integer.toString(id);
			char firstInTarget = targetId.charAt(0);
			if (firstInTarget == '1') {
				for (Book book : books) {
					if (book.id == id) {
						String name;
						String number;
						if (!unAvailableProducts.contains(id)) {
							System.out.print("Enter name: ");
							name = inputScanner.nextLine();
							System.out.print("Enter phonenumber: ");
							number = inputScanner.nextLine();
							for (Customer customer : customer.customerList) {
								if (customer.name.toLowerCase().equalsIgnoreCase(name)
										&& customer.number.equals(number)) {
									customer.borrowedProducts.add(id);
									unAvailableProducts.add(id);
									saveOrInit.saveCustomerList();
									saveOrInit.saveUnAvailableProductsList();
									System.out.println("Added to existing list");
									return;
								}
							}
							System.out.println("Added name:	" + name + " number: " + number);
							List<Integer> borrowed = new ArrayList<Integer>();
							borrowed.add(id);
							Customer c1 = new Customer(name, number, borrowed);
							customer.customerList.add(c1);
							unAvailableProducts.add(id);
							saveOrInit.saveCustomerList();
							saveOrInit.saveUnAvailableProductsList();
						} else {
							System.out.println("Product with ID: \"" + id + "\" is already borrowed out");
						}
					}
				}
			} else if (firstInTarget == '5') {
				for (Movie movie : movies) {
					if (movie.id == id) {
						String name;
						String number;
						if (!unAvailableProducts.contains(id)) {
							System.out.print("Enter name: ");
							name = inputScanner.nextLine();
							System.out.print("Enter phonenumber: ");
							number = inputScanner.nextLine();
							for (Customer customer : customer.customerList) {
								if (customer.name.toLowerCase().equalsIgnoreCase(name)
										&& customer.number.equals(number)) {
									customer.borrowedProducts.add(id);
									unAvailableProducts.add(id);
									saveOrInit.saveCustomerList();
									saveOrInit.saveUnAvailableProductsList();
									System.out.println("Added to existing list");
									return;
								}
							}

							System.out.println("Added Name: " + name + "  number: " + number);
							List<Integer> borrowed = new ArrayList<Integer>();
							borrowed.add(id);
							Customer c1 = new Customer(name, number, borrowed);
							customer.customerList.add(c1);
							unAvailableProducts.add(id);
							saveOrInit.saveCustomerList();
							saveOrInit.saveUnAvailableProductsList();
						} else {
							System.out.println("Product with ID: \"" + id + "\" is already borrowed out");
						}
					}
				}
			}
		} catch (InputMismatchException e) {
			System.out.println("Invalid format, try again");
		}
	}

	// Getter
	public void getBooksAndMovies() {
		List<String> printList = new ArrayList<String>();
		printList.clear();
		if (customer.customerList.isEmpty())	{
			for (Book book : books)	{
				System.out.println(book.getBooksString());
			}
			for (Movie movie : movies)	{
				System.out.println(movie.getMoviesString());
			}
		}
		for (Book book : books)		{
			for (Customer customer : customer.customerList)	{
				if (customer.borrowedProducts.contains(book.id))	{
					String bookBorrowed = book.getBooksString() + "\n\t\tis borrowed by " + customer.getCustomer();
					printList.add(bookBorrowed);
				} else	{
					printList.add(book.getBooksString());
				}
			}
		}	
		for (Movie movie : movies)	{
			for (Customer customer : customer.customerList)	{
				if (customer.borrowedProducts.contains(movie.id))	{
					String movieBorrowed = movie.getMoviesString() + "\n\t\tis borrowed by " + customer.getCustomer();
					printList.add(movieBorrowed);
				} else	{
					printList.add(movie.getMoviesString());
				}
			}
		}
		for (String str : printList)	{
			System.out.println(str);
		}
	}
	
	// Remove
	public void removeAtID(int id) {
		String id2 = Integer.toString(id);
		if (saveid.contains(id)) {
			// Movies Iterator
			Iterator<Movie> iterMovies = movies.iterator();
			while (iterMovies.hasNext()) {
				Movie target = iterMovies.next();
				String targetSplit = target.getMoviesString().split(",")[0];
				if (targetSplit.equals(id2)) {
					iterMovies.remove();
					Integer removeId = Integer.parseInt(targetSplit);
					saveid.remove(removeId);
					System.out.println("You've successfully removed \"" + target.getTitle() + "\"");
					saveOrInit.saveMovieList();
					saveOrInit.saveIdList();
				}
			}

			// Books Iterator
			Iterator<Book> iterBooks = books.iterator();
			while (iterBooks.hasNext()) {
				Book target2 = iterBooks.next();
				String target2Split = target2.getBooksString().split(",")[0];
				if (target2Split.equals(id2)) {
					iterBooks.remove();
					Integer removeId = Integer.parseInt(target2Split);
					saveid.remove(removeId);
					System.out.println("You've successfully removed \"" + target2.getTitle() + "\"");
					saveOrInit.saveBookList();
					saveOrInit.saveIdList();
				}
			}
		} else if (saveid.contains(id) == false) {
			System.out.println("Product with id: \""+id+"\" does not exist");
		}
		if (movies.isEmpty() == true && books.isEmpty() == true) {
			System.out.println("There are no \"products\" in the library to remove");
		}
	}
}
	
