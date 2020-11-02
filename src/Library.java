import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Library implements Serializable {
	private static final int serialVersionUID = 136420;
	protected Book book;
	protected Movie movie;
	protected Customer customer;
	protected Product product;
	protected static InitializersAndSavers saveOrInit = new InitializersAndSavers();
//	protected static List<Movie> movies = new ArrayList<Movie>();
//	protected static List<Book> books = new ArrayList<Book>();
	protected static List<Product> products = new ArrayList<Product>();
	
	protected static List<Integer> saveid = new ArrayList<Integer>();
	protected static List<Integer> unAvailableProducts = new ArrayList<Integer>();

	public Library() {
	}
	
	// Dialogues
	public void registerDialogue(char c){
		try {
			Scanner userinput = new Scanner(System.in);
			if (c == 'm') {
				int id = 0;
				String idString = null;
				double rating;
				do	{
				System.out.print("(id for movie should start with\'5\' and be 3-5 numbers long) Enter id:\n>");
				id = Integer.parseInt(userinput.nextLine());
				if(saveid.contains(id)) {
					System.out.println("Product with id: \""+id+"\" already exists");
					return;
				}
				idString = Integer.toString(id);
				} while (idString.matches("^5\\d{2,5}") == false);
				System.out.print("Enter title:\n>");
				String title = userinput.nextLine();
				System.out.print("Enter value:\n>");
				int value = Integer.parseInt(userinput.nextLine());
				System.out.print("Enter duration: (minutes)\n>");
				int duration = Integer.parseInt(userinput.nextLine());
				System.out.print("Enter rating:\n>");
				do	{
					rating = Double.parseDouble(userinput.nextLine());
					if (rating > 10 || rating < 0) {
						System.out.println("Rating should be between 0.0 and 10.0\n>");
					}
				} while (rating < 0 && rating > 10);
				products.add(product = new Movie(id, title, value, duration, rating));
				Collections.sort(products, Comparator.comparing(Product::getId));
				saveid.add(id);
				saveOrInit.saveIdList();
				saveOrInit.saveMovieList();
			} else if (c == 'b') {
				int id = 0;
				String idString = null;
				do	{
				System.out.print("(Id for book should start with \'1\' and should be 3-5 numbers long) Enter id:\n>");
				id = Integer.parseInt(userinput.nextLine());
				if(saveid.contains(id)) {
					System.out.println("Product with id: \""+id+"\" already exists");
					return;
				}
				idString =Integer.toString(id);
				} while (idString.matches("^1\\d{2,5}") == false);
				System.out.print("title:\n>");
				String title = userinput.nextLine();
				System.out.print("Enter value:\n>");
				int value = Integer.parseInt(userinput.nextLine());
				System.out.print("Enter pages:\n>");
				int pages = Integer.parseInt(userinput.nextLine());
				System.out.print("Enter author:\n>");
				String publisher = userinput.nextLine();
				products.add(book = new Book(id, title, value, pages, publisher));
				Collections.sort(products, Comparator.comparing(Product::getId));
				saveid.add(id);
				saveOrInit.saveIdList();
				saveOrInit.saveBookList();
			} else {
				System.out.println("Invalid charinput, try again");
				return;
			}
		} catch (InputMismatchException e) {
			System.out.println("Invalid format, try again");
		} catch (NumberFormatException n)	{
			System.out.println("Invalid format, try again");
		}
	}

	// Search
	public void searchCustomer(String name) {
		if(Pattern.matches("[A-Za-z]{1,40}", name)) {
		Iterator<Customer> iterCustomer = customer.customerList.iterator();
		while(iterCustomer.hasNext()) {
			Customer target = iterCustomer.next();
			if(target.name.equalsIgnoreCase(name)) {
				System.out.println(target.getCustomerWithList());
				return;
			}
		}
		System.out.println("\""+name+"\" does not exist");
		}
		else {
			System.out.println("\""+name+"\" is not valid, try with letters");
		}
	}
	public void searchID(int id) {
		if (!(saveid.contains(id)))	{
			System.out.println("Product with ID: " + id + " does not exist, try again.");
			return;
		}
		for(Product product : products) {
			if(product.id == id) {
				if(product.equals(movie)) {
					System.out.println(product.getMoviesString());
					return;
				}else if(product.equals(book)) {
					System.out.println(product.getBooksString());
					return;
				}
			}
		}
		
		
//		String targetId = Integer.toString(id);
//		char firstInTarget = targetId.charAt(0);
//		if (firstInTarget == '1')	{
//			for (Book book : books)	{
//				if (book.id == id)	{
//					System.out.println(book.getBooksString());
//				}
//			}
//		} else if (firstInTarget == '5')	{
//			for (Movie movie : movies)	{
//				if (movie.id == id)	{
//					System.out.println(movie.getMoviesString());
//				}
//			}
//		}
	}
	public void productReturn(int id) {
		if (unAvailableProducts.contains(id)) {
			for (Integer product : unAvailableProducts) {
				if (product == id) {
					break;
				}
			}
			unAvailableProducts.remove(Integer.valueOf(id));
			saveOrInit.saveUnAvailableProductsList();
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
				if (target.borrowedProducts.isEmpty())	{
					customer.customerList.remove(target);
				}
				saveOrInit.saveCustomerList();
				String idtoString = Integer.valueOf(id).toString();
				for(Product product : products) {
					if(product.getBooksString().contains(idtoString) || product.getMoviesString().contains(idtoString)) {
						System.out.println("You have sucessfully returned " + product.title + "");
						return;
					}
				}
				System.out.println("you have successfully returned " + id + "");
				return;
			}
			if (!iter.hasNext()) {
				System.out.println("there is no such product in \"" + target.name + "\" list");
				break;
			}
				
				
				
//				for (Book book : books) {
//					if (book.getBooksString().contains(idtoString)) {
//						System.out.println("You have sucessfully returned " + book.title + "");
//						return;
//					}
//					for (Movie movie : movies) {
//						if (movie.getMoviesString().contains(idtoString)) {
//							System.out.println("You have sucessfully returned " + movie.title + "");
//							return;
//						}
//					}
//				}
//				System.out.println("you have successfully returned " + id + "");
//				return;
//			}
//			if (!iter.hasNext()) {
//				System.out.println("there is no such product in \"" + target.name + "\" list");
//				break;
//			}
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
				for (Product product : products) {
					if (product.id == id) {
						String name;
						Long number;
						if (!unAvailableProducts.contains(id)) {
							System.out.print("Enter name:\n>");
							name = inputScanner.nextLine();
							if (!(Pattern.matches("[A-Za-z]{1,40}", name)))	{
								System.out.println("Name should be letters only, try again");
								return;
							}
							System.out.print("Enter phonenumber:\n>");
							number = Long.parseLong(inputScanner.nextLine());	
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
							System.out.println("Added name: "+name+", number: " + number);
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
//			} else if (firstInTarget == '5') {
//				for (Movie movie : movies) {
//					if (movie.id == id) {
//						String name;
//						Long number;
//						if (!unAvailableProducts.contains(id)) {
//							System.out.print("Enter name:\n>");
//							name = inputScanner.nextLine();
//							if (!(Pattern.matches("[A-Za-z]{1,40}", name)))	{
//								System.out.println("Name should be letters only, try again");
//								return;
//							}
//							System.out.print("Enter phonenumber:\n>");
//							number = Long.parseLong(inputScanner.nextLine());
//							for (Customer customer : customer.customerList) {
//								if (customer.name.toLowerCase().equalsIgnoreCase(name)
//										&& customer.number.equals(number)) {
//									customer.borrowedProducts.add(id);
//									unAvailableProducts.add(id);
//									saveOrInit.saveCustomerList();
//									saveOrInit.saveUnAvailableProductsList();
//									System.out.println("Added to existing list");
//									return;
//								}
//							}
//
//							System.out.println("Added Name: "+name+",  number: " + number);
//							List<Integer> borrowed = new ArrayList<Integer>();
//							borrowed.add(id);
//							Customer c1 = new Customer(name, number, borrowed);
//							customer.customerList.add(c1);
//							unAvailableProducts.add(id);
//							saveOrInit.saveCustomerList();
//							saveOrInit.saveUnAvailableProductsList();
//						} else {
//							System.out.println("Product with ID: \"" + id + "\" is already borrowed out");
//						}
//					}
//				}
			}
		} catch (NumberFormatException n)	{
			System.out.println("Invalid format, try again asadadad");
		}
	}

	// Getter
	public void getBooksAndMovies() {
		List<String> printList = new ArrayList<String>();
		printList.clear();
		if (customer.customerList.isEmpty())	{
			for(Product product : products) {
					System.out.println(product.getBooksString());
					System.out.println(product.getMoviesString());
				}
			return;
			}
//			for (Book book : books)	{
//				System.out.println(book.getBooksString());
//			}
//			for (Movie movie : movies)	{
//				System.out.println(movie.getMoviesString());
//			}
		

		for(Product product : products) {
			for(Customer customer : customer.customerList) {
				if(customer.borrowedProducts.contains(product.id)) {
					if(product == book) {
						printList.add(product.getBooksString());
					}else if(product == movie) {
						printList.add(product.getMoviesString());
					}
					String borrowed = "\t\t is borrowed by " + customer.getCustomer();
					printList.add(borrowed);
				}
				
			}
			if (printList.contains(product.getBooksString()) || printList.contains(movie.getMoviesString()))	{
				continue;
			} else 	if(!printList.contains(product.getBooksString())){
				printList.add(product.getBooksString());
			} else if(!printList.contains(product.getBooksString())) {
				printList.add(product.getMoviesString());
			}
		}
		for (String str : printList)	{
			System.out.println(str);
		}
		if (printList.isEmpty())	{
			System.out.println("No books or movies available. Use command \'register\'");
		}
		
//		for (Book book : books)		{
//			for (Customer customer : customer.customerList)	{
//				if (customer.borrowedProducts.contains(book.id))	{
//					String borrowed = "\t\t is borrowed by " + customer.getCustomer();
//					printList.add(book.getBooksString());
//					printList.add(borrowed);
//				}  
//			}
//			if (printList.contains(book.getBooksString()))	{
//				continue;
//			} else 	{
//				printList.add(book.getBooksString());
//			}
//		}	
//		for (Movie movie : movies)	{
//			for (Customer customer : customer.customerList)	{
//				if (customer.borrowedProducts.contains(movie.id))	{
//					String borrowed = "\t\tis borrowed by " + customer.getCustomer();
//					printList.add(movie.getMoviesString());
//					printList.add(borrowed);
//				}
//			}
//			if (printList.contains(movie.getMoviesString()))	{
//				continue;
//			} else	{
//				printList.add(movie.getMoviesString());
//			}
//		}
//		for (String str : printList)	{
//			System.out.println(str);
//		}
//		if (printList.isEmpty())	{
//			System.out.println("No books or movies available. Use command \'register\'");
//		}
	}
	
	// Remove
	public void removeAtID(int id) {
		String id2 = Integer.toString(id);
		if (saveid.contains(id)) {
			// Movies Iterator
			Iterator<Product> iterproducts = products.iterator();
			while (iterproducts.hasNext()) {
				Product target = iterproducts.next();
				String targetSplitMovie = target.getMoviesString().split(",")[0];
				String targetSplitBook = target.getBooksString().split(",")[0];
				if (targetSplitMovie.equals(id2) || targetSplitBook.equals(id2)) {
					iterproducts.remove();
//					Integer removeId = Integer.parseInt(targetSplit);
					saveid.remove(id);
					System.out.println("You've successfully removed \"" + target.getTitle() + "\"");
					saveOrInit.saveMovieList();
					saveOrInit.saveIdList();
				}
			}

			// Books Iterator
//			Iterator<Book> iterBooks = books.iterator();
//			while (iterBooks.hasNext()) {
//				Book target2 = iterBooks.next();
//				String target2Split = target2.getBooksString().split(",")[0];
//				if (target2Split.equals(id2)) {
//					iterBooks.remove();
//					Integer removeId = Integer.parseInt(target2Split);
//					saveid.remove(removeId);
//					System.out.println("You've successfully removed \"" + target2.getTitle() + "\"");
//					saveOrInit.saveBookList();
//					saveOrInit.saveIdList();
//				}
//			}
		} else if (!(saveid.contains(id))) {
			System.out.println("Product with id: \""+id+"\" does not exist");
		}
		if (products.isEmpty()) {
			System.out.println("There are no products in the library to remove");
		}
	}
}