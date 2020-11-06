import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

	/*Class Library is a class implementing all functionality to make Library such as registering new products to inventory
	 * function to borrow out products, check in products, search info about products, view full inventory, keep track of
	 * products available for borrowing, find info about customers and remove products from inventory
	 * 
	 *@author Niklas Kullberg
	 *@author Sebastian Kahkonen
	 */
public class Library implements Serializable {
	private static final long serialVersionUID = 136420;
	
	/*customer -- used to reach functionality from class Customer
	 * product -- used to reach functionality from class Product
	 * saveOrInit -- used to reach class InitializersAndSavers for serialization of classes
	 * products -- list of inventory
	 * unAvailableProducts -- used to keep track of products borrowed out to customer
	 */
	protected Customer customer;
	protected Product product;
	protected static InitializersAndSavers saveOrInit = new InitializersAndSavers();
	protected static List<Product> products = new ArrayList<Product>();
	protected static List<Integer> unAvailableProducts = new ArrayList<Integer>();
	private Scanner userinput;

	public Library() {
	}

	/*Register new products to product inventory
	 * @param c used to decide what instance of product is being registered
	 */
	public void registerDialogue(char c) {
		
		try {
			if (c == 'm' || c == 'b') {
				userinput = new Scanner(System.in);
				int id = 0;
				String idString = null;
				double rating;
				do {
					System.out.print("(ID has to be 4 numbers) Enter id:\n>");
					id = Integer.parseInt(userinput.nextLine());
					for (Product product : products) {
						if (product.id == id) {
							System.out.println("Product with id: \"" + id + "\" already exists");
							return;
						}
					}
					idString = Integer.toString(id);
				} while (idString.matches("\\d{4}") == false);
				System.out.print("Enter title:\n>");
				String title = userinput.nextLine();
				for (Product product : products) {
					if (title.equalsIgnoreCase(product.title)) {
						System.out.println("\"" + title + "\" already exists in products, type 'list' to see it");
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
					products.add(product = new Book(id, title, value, pages, publisher));
					Collections.sort(products, Comparator.comparing(Product::getId));
				}
				InitializersAndSavers.saveProductList();
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

	/*Search info about customer
	 * @param name, search customer using customers registered name
	 * @return name, phoneNumber and list of ID of this customers borrowed products
	 */
	public void getCustomerinfo(String name) {
			if (Pattern.matches("[A-Za-z ]{1,40}", name)) {
				Iterator<Customer> iterCustomer = Customer.customerList.iterator();
				while (iterCustomer.hasNext()) {
					Customer target = iterCustomer.next();
					if (target.name.equalsIgnoreCase(name)) {
						System.out.println(target.getCustomerWithList());
						return;
					}
				}
				System.out.println("customer with name: \"" + name + "\" does not exist");
			} else {
				System.out.println("\"" + name + "\" is not valid, try with letters");
			}
		}

	/*Search info about product
	 * @param id, search info about product using this products ID
	 * @return prints info about searched product or message if product does not exist
	 */
	public void getInfo(int id) {
		for (Product p : products) {
			if (p.id == id) {
				System.out.println(p);
				return;
			}
		}
		System.out.println("Product with ID: " + id + " does not exist, try again.");
	}
	
	/*Return borrowed product
	 * @param id, return product using product ID
	 */
	public void productReturn(int id) {
		boolean idExists = false;
		for (Product p : products) {
			if (p.id == id) {
				idExists = true;
			}
		}
		if (!(idExists)) {
			System.out.println("Product with id: \"" + id + "\" does not exist");
			return;
		}
		if (!(unAvailableProducts.contains(Integer.valueOf(id)))) {
			System.out.println("Product with id: \"" + id + "\" isnt borrowed out");
			return;
		}
		unAvailableProducts.remove(Integer.valueOf(id));
		saveOrInit.saveUnAvailableProductsList();
		Iterator<Customer> iter = Customer.customerList.iterator();
		while (iter.hasNext()) {
			Customer target = iter.next();
			if (target.borrowedProducts.contains(id)) {
				target.borrowedProducts.remove(Integer.valueOf(id));
				if (target.borrowedProducts.isEmpty()) {
					Customer.customerList.remove(target);
				}
				saveOrInit.saveCustomerList();
				String idtoString = Integer.valueOf(id).toString();
				for (Product product : products) {
					if (product.toString().contains(idtoString)) {
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
	
	/*Check out product to customer
	 * @param id, check out product using products unique ID
	 */
	public void productBorrow(int id) {
		try {
			userinput = new Scanner(System.in);
			for (Product product : products) {
				if (product.id == id) {
					String name;
					String number;
					if (!unAvailableProducts.contains(id)) {
						System.out.print("Enter name:\n>");
						name = userinput.nextLine();
						if (!(Pattern.matches("(?i)[A-Za-z ]{1,40}", name))) {
							System.out.println("Name should be letters only, try again");
							return;
						}
						System.out.print("Enter phonenumber:\n>");
						number = userinput.nextLine();
						if (!(Pattern.matches("\\d+", number))) {
							System.out.println("Number should be digits only, try again");
							return;
						}
						for (Customer customer : Customer.customerList) {
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
						Customer.customerList.add(c1);
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

	/*getProducts is used to print inventory and also view which products are borrowed by which customers
	 * @return prints list of inventory
	 */
	public void getProducts() {
		List<String> printList = new ArrayList<String>();
		printList.clear();
		if (products.isEmpty()) {
			System.out.println("List is empty, try 'register' command to register products");
		} else if (!(products.isEmpty())) {
			if (Customer.customerList.isEmpty()) {
				for (Product product : products) {
					System.out.println(product);
				}
				return;
			} else {
				for (Product product1 : products) {
					for (Customer customer : Customer.customerList) {
						if (customer.borrowedProducts.contains(product1.id)) {
							printList.add(product1.toString());
							String borrowed = "\t\t is borrowed by " + customer.getCustomer();
							printList.add(borrowed);
						}
					}
					if (printList.contains(product1.toString())) {
						continue;
					} else if (!printList.contains(product1.toString())) {
						printList.add(product1.toString());
					}
				}
				for (String str : printList) {
					System.out.println(str);
				}
				if (printList.isEmpty()) {
					System.out.println("No books or movies available. Use command \'register\'");
				}
			}
		}

	}

	/*Remove product from inventory
	 * @param id, remove product using products unique ID
	 */
	public void removeAtID(int id) {
		if (!unAvailableProducts.contains(id)) {
			String id2 = Integer.toString(id);
			for (Product product : products) {
				if (product.id == id) {
					Iterator<Product> iterproducts = products.iterator();
					while (iterproducts.hasNext()) {
						Product target = iterproducts.next();
						String targetSplit = target.toString().split(",")[0];
						if (targetSplit.equals(id2)) {
							iterproducts.remove();
							System.out.println("You've successfully removed \"" + target.getTitle() + "\"");
							InitializersAndSavers.saveProductList();
							return;
						}
					}
					return;
				}
			}
		} else if (unAvailableProducts.contains(id)) {
			System.out.println("You cant remove product with ID: \"" + id + "\" because its borrowed out");
			return;
		} else if (products.isEmpty()) {
			System.out.println("There are no products in the library to remove");
			return;
		}
		System.out.println("Product with id: \"" + id + "\" does not exist");
		return;
	}
}