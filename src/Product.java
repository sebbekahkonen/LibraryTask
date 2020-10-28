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
	protected Savers save;
	protected final Scanner scanner = new Scanner(System.in);
	protected static List<Movie> movies = new ArrayList<Movie>();
	protected static List<Book> books = new ArrayList<Book>();
	protected static List<Integer> saveid = new ArrayList<Integer>();
	protected static List<Integer> unAvailableProducts = new ArrayList<Integer>();

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
				save.saveIdList();
				save.saveMovieList();
			} else if (c == 'b') {
				int id;
				String idString;
				char firstInt;
				do	{
				System.out.print("(Id for book should start with \'1\') Enter id: ");
				id = scanner.nextInt();
				idString =Integer.toString(id);
				firstInt = idString.charAt(0);
				} while (firstInt != '1');
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
				save.saveIdList();
				save.saveBookList();
			} else {
				System.out.println("sorry that char isnt valid");
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
	public void searchAndReturn(int id) {
//		String targetId = Integer.toString(id);
		if(unAvailableProducts.contains(id)) {
			for(Integer product : unAvailableProducts) {
				if(product == id) {
				break;
				}
			}
			unAvailableProducts.remove(Integer.valueOf(id));
		}
		else {
			System.out.println(""+id+" doesnt exist");
			return;
		}
		
		Iterator<Customer> iter = customer.customerList.iterator();
		while(iter.hasNext()) {
			Customer target = iter.next();
			if(target.borrowedProducts.contains(id)) {
				target.borrowedProducts.remove(Integer.valueOf(id));
				System.out.println("you have successfully returned "+id+"");
				return;
			}
			if(!iter.hasNext()) {
				System.out.println("there is no such product in \""+target.name+"\" list");
				break;
			}
		}
			
	}
	public void searchAndBorrow(int id) {
		if (!(saveid.contains(id)))	{
			System.out.println("Product with ID: " + id + " does not exist, try again.");
			return;
		}
		String targetId = Integer.toString(id);
		char firstInTarget = targetId.charAt(0);
		if (firstInTarget == '1')	{
			for (Book book : books)	{
				if (book.id == id)	{
					String name;
					String number;
					if(!unAvailableProducts.contains(id)){
						System.out.print("Enter name: ");
						name = scanner.nextLine();
						System.out.print("Enter phonenumber: ");
						number = scanner.nextLine();
						for (Customer customer : customer.customerList)	{
							if (customer.name.toLowerCase().equalsIgnoreCase(name) && customer.number.equals(number))	{
								customer.borrowedProducts.add(id);
								unAvailableProducts.add(id);
								save.saveCustomerList();
								save.saveUnAvailableProductsList();
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
							save.saveCustomerList();
							save.saveUnAvailableProductsList();
						}
					else {
						System.out.println("Product with ID: \""+id+"\" is already borrowed out");
					}
				}
			}
		} else if (firstInTarget == '5')	{
			for (Movie movie : movies)	{
				if (movie.id == id)	{
					String name;
					String number;
					if(!unAvailableProducts.contains(id)){
						System.out.print("Enter name: ");
						name = scanner.nextLine();
						System.out.print("Enter phonenumber: ");
						number = scanner.nextLine();
						for (Customer customer : customer.customerList)	{
							if (customer.name.toLowerCase().equalsIgnoreCase(name) && customer.number.equals(number))	{
								customer.borrowedProducts.add(id);
								unAvailableProducts.add(id);
								save.saveCustomerList();
								save.saveUnAvailableProductsList();
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
							save.saveCustomerList();
							save.saveUnAvailableProductsList();
						}
					else {
						System.out.println("Product with ID: \""+id+"\" is already borrowed out");
					}
				}
			}
		}
	}
	// Getters
	public void getID() {
		for (int id : saveid) {
			System.out.println(id);
		}
	}
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
					save.saveMovieList();
					save.saveIdList();
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
					save.saveBookList();
					save.saveIdList();
				}
			}
		} else if (saveid.contains(id) == false) {
			System.out.println("Book or Movie with id \"" + id + "\" does not exist");
		}
		if (movies.isEmpty() == true && books.isEmpty() == true) {
			System.out.println("There are no \"books\" or \"movies\" in the library to remove");
		}
	}
	
	
	
	
	
	
	
	//Initializers
//	public static void initializeBookList() {
//		try {
//			File file = new File("book_list.bin");
//			FileInputStream fin = new FileInputStream(file);
//			ObjectInputStream oin = new ObjectInputStream(fin);
//			books = (List<Book>) oin.readObject();
//			oin.close();
//			for (Book book : books) {
//				System.out.println(book.getBooksString());
//			}
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	public static void initializeMovieList()	{
//		try {
//			File file = new File("movie_list.bin");
//			FileInputStream fin = new FileInputStream(file);
//			ObjectInputStream oin = new ObjectInputStream(fin);
//			movies = (List<Movie>) oin.readObject();
//			oin.close();
//			for (Movie movie : movies) {
//				System.out.println(movie.getMoviesString());
//			}
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	public static void initializeIdList()	{
//		try {
//			File file = new File("id_list.bin");
//			FileInputStream fin = new FileInputStream(file);
//			ObjectInputStream oin = new ObjectInputStream(fin);
//			saveid = (List<Integer>) oin.readObject();
//			oin.close();
//			System.out.println(saveid);
//			
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	public void initializeUnAvailableProductsList()	{
//		try {
//			File file = new File("unavailableproducts_list.bin");
//			FileInputStream fin = new FileInputStream(file);
//			ObjectInputStream oin = new ObjectInputStream(fin);
//			unAvailableProducts = (List<Integer>) oin.readObject();
//			oin.close();
//			System.out.println(unAvailableProducts);
//			
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	public void initializeCustomerList()	{
//		try {
//			File file = new File("customer_list.bin");
//			FileInputStream fin = new FileInputStream(file);
//			ObjectInputStream oin = new ObjectInputStream(fin);
//			customer.customerList = (List<Customer>) oin.readObject();
//			oin.close();
//			for (Customer customer : customer.customerList)	{
//				System.out.println(customer.getCustomer());
//			}
//			
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	//SaveAll
//	public void saveCustomerList() {
//		File file = new File("customer_list.bin");
//		FileOutputStream fout;
//		try {
//			fout = new FileOutputStream(file);
//			ObjectOutputStream out = new ObjectOutputStream(fout);
//			out.writeObject(customer.customerList);
//			out.close();
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	public void saveUnAvailableProductsList() {
//		File file = new File("unavailableproducts_list.bin");
//		FileOutputStream fout;
//		try {
//			fout = new FileOutputStream(file);
//			ObjectOutputStream out = new ObjectOutputStream(fout);
//			out.writeObject(unAvailableProducts);
//			out.close();
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	public static void saveBookList() {
//		File file = new File("book_list.bin");
//		FileOutputStream fout;
//		try {
//			fout = new FileOutputStream(file);
//			ObjectOutputStream out = new ObjectOutputStream(fout);
//			out.writeObject(books);
//			out.close();
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	public static void saveIdList() {
//		File file = new File("id_list.bin");
//		FileOutputStream fout;
//		try {
//			fout = new FileOutputStream(file);
//			ObjectOutputStream out = new ObjectOutputStream(fout);
//			out.writeObject(saveid);
//			out.close();
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	public static void saveMovieList() {
//		File file = new File("movie_list.bin");
//		FileOutputStream fout;
//		try {
//			fout = new FileOutputStream(file);
//			ObjectOutputStream out = new ObjectOutputStream(fout);
//			out.writeObject(movies);
//			out.close();
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

}
	
