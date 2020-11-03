//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.ObjectInputStream;
//import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
//import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Library implements Serializable {
	private static final int serialVersionUID = 136420;
	protected Book book;
	protected Movie movie;
	protected Customer customer;
	protected Product product;
	protected static InitializersAndSavers saveOrInit = new InitializersAndSavers();
	protected static List<Product> products = new ArrayList<Product>();
	protected static List<Integer> saveid = new ArrayList<Integer>();
	protected static List<Integer> unAvailableProducts = new ArrayList<Integer>();

	public Library() {
	}
	
	// Dialogues
	public void registerDialogue(char c){//KLAR
		try {
			if (c == 'm' || c == 'b') {
				Scanner userinput = new Scanner(System.in);
				int id = 0;
				String idString = null;
				double rating;
				do {
					System.out.print("(ID has to be 4 numbers) Enter id:\n>");
					id = Integer.parseInt(userinput.nextLine());
					for(Product product : products) {
						if(product.id == id) {
							System.out.println("Product with id: \"" + id + "\" already exists");
							return;
						}
					}
				idString = Integer.toString(id);
				} while (idString.matches("\\d{4}") == false);
				System.out.print("Enter title:\n>");
				String title = userinput.nextLine();
				for(Product product : products) {
				if(title.equalsIgnoreCase(product.title)) {
					System.out.println("\""+title+"\" already exists in products, type 'list' to see it");
					return;
				}
				}
				System.out.print("Enter value:\n>");
				int value = Integer.parseInt(userinput.nextLine());
				if (c == 'm') {
					System.out.print("Enter duration: (minutes)\n>");
					int duration = Integer.parseInt(userinput.nextLine());
					System.out.print("Enter rating:\n>");
					do {
						rating = Double.parseDouble(userinput.nextLine());
						if (rating > 10 || rating < 0) {
							System.out.println("Rating should be between 0.0 and 10.0\n>");
						}
					} while (rating < 0 && rating > 10);
					products.add(product = new Movie(id, title, value, duration, rating));
					Collections.sort(products, Comparator.comparing(Product::getId));
				} else if (c == 'b') {
					System.out.print("Enter pages:\n>");
					int pages = Integer.parseInt(userinput.nextLine());
					System.out.print("Enter author:\n>");
					String publisher = userinput.nextLine();
					products.add(book = new Book(id, title, value, pages, publisher));
					Collections.sort(products, Comparator.comparing(Product::getId));
				}
//				saveOrInit.saveIdList();
				saveOrInit.saveProductList();
				return;
			} else {
				System.out.println("Invalid charinput, try again");
				return;
			}
		} catch (InputMismatchException e) {
			System.out.println("Invalid format, try again");
		} catch (NumberFormatException n) {
			System.out.println("Invalid format, try again");
		}
	}


	// Search
	public void searchCustomer(String name) {// KLAR
		if (Pattern.matches("[A-Za-z ]{1,40}", name)) {
			Iterator<Customer> iterCustomer = customer.customerList.iterator();
			while (iterCustomer.hasNext()) {
				Customer target = iterCustomer.next();
				if (target.name.equalsIgnoreCase(name)) {
					System.out.println(target.getCustomerWithList());
					return;
				}
			}
			System.out.println("customer with name: \""+name+"\" does not exist");
		} else {
			System.out.println("\""+name+"\" is not valid, try with letters");
		}
	}
	
	public void searchID(int id) {//KLAR?
		for (Product p : products)	{
			if (p.id == id)	{
				System.out.println(p);
				return;
			}
		}
		System.out.println("Product with ID: " + id + " does not exist, try again.");
	}
	
	public void productReturn(int id) {//KLAR?
		boolean idExists = false;
		for (Product p : products)	{
			if (p.id == id)	{
				idExists = true;
			}
		}
		if (!(idExists))	{
			System.out.println("Product with id: \"" + id + "\" does not exist");
			return;
		}
		if (!(unAvailableProducts.contains(Integer.valueOf(id))))	{
			System.out.println("Product with id: \"" + id + "\" isnt borrowed out");
			return;
		}
		unAvailableProducts.remove(Integer.valueOf(id));
		saveOrInit.saveUnAvailableProductsList();
		
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
					if(product.toString().contains(idtoString)) {
						System.out.println("You have sucessfully returned " + product.title + "");
						return;
					}
				}
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
			String targetId = Integer.toString(id);
			char firstInTarget = targetId.charAt(0);
			for (Product product : products) {
				if (product.id == id) {
					String name;
					Long number;
					if (!unAvailableProducts.contains(id)) {
						System.out.print("Enter name:\n>");
						name = inputScanner.nextLine();
						if (!(Pattern.matches("(?i)[A-Za-z ]{1,40}", name))) {
							System.out.println("Name should be letters only, try again");
							return;
						}
						System.out.print("Enter phonenumber:\n>");
						number = Long.parseLong(inputScanner.nextLine());
						for (Customer customer : customer.customerList) {
							if (customer.name.toLowerCase().equalsIgnoreCase(name) && customer.number.equals(number)) {
								customer.borrowedProducts.add(id);
								unAvailableProducts.add(id);
								saveOrInit.saveCustomerList();
								saveOrInit.saveUnAvailableProductsList();
								System.out.println("Added to existing list");
								return;
							}
						}
						System.out.println("Added name: " + name + ", number: " + number);
						List<Integer> borrowed = new ArrayList<Integer>();
						borrowed.add(id);
						Customer c1 = new Customer(name, number, borrowed);
						customer.customerList.add(c1);
						unAvailableProducts.add(id);
						saveOrInit.saveCustomerList();
						saveOrInit.saveUnAvailableProductsList();
					} else {
						System.out.println("Product with ID: \"" + id + "\" is already borrowed out");
						return;
					}
					return;
				}
			}
			System.out.println("Product with ID: " + id + " does not exist, see 'list' for all registered products");			

		} catch (NumberFormatException n) {
			System.out.println("Invalid format, try again");
		}
	}

	// Getter
	public void getBooksAndMovies() {//KLAR
		List<String> printList = new ArrayList<String>();
		printList.clear();
		if (customer.customerList.isEmpty())	{
			for(Product product : products) {
					System.out.println(product);
			}
			return;
		}else {
			for(Product product1 : products) {
				for(Customer customer : customer.customerList) {
					if(customer.borrowedProducts.contains(product1.id)) {
							printList.add(product1.toString());
						String borrowed = "\t\t is borrowed by " + customer.getCustomer();
						printList.add(borrowed);
					}
				}
				if (printList.contains(product1.toString()))	{
					continue;
				} else 	if(!printList.contains(product1.toString())){
					printList.add(product1.toString());
				}
			}
			for (String str : printList)	{
				System.out.println(str);
			}
			if (printList.isEmpty())	{
				System.out.println("No books or movies available. Use command \'register\'");
			}
		}
		
	}
	
	// Remove
	public void removeAtID(int id) { //KLAR
		if(!unAvailableProducts.contains(id)) {
			String id2 = Integer.toString(id);
			for(Product product : products) {
			if (product.id == id) {
				// Movies Iterator
				Iterator<Product> iterproducts = products.iterator();
				while (iterproducts.hasNext()) {
					Product target = iterproducts.next();
					String targetSplit = target.toString().split(",")[0];
					if (targetSplit.equals(id2)) {
						iterproducts.remove();
						Integer removeId = Integer.parseInt(targetSplit);
						System.out.println("You've successfully removed \"" + target.getTitle() + "\"");
						saveOrInit.saveProductList();
//						saveOrInit.saveIdList();
						return;
					}
				}
				return;
				}
			}
		}else if(unAvailableProducts.contains(id)){
			System.out.println("You cant remove product with ID: \""+id+"\" because its borrowed out");
			return;
		}else if(products.isEmpty()) {
			System.out.println("There are no products in the library to remove");
			return;
		}
			System.out.println("Product with id: \""+id+"\" does not exist");
			return;
	}
}