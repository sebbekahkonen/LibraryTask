import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.InputMismatchException;
import java.util.Scanner;
public class Main implements Serializable {
	private static final int serialVersionUID = 136420;
	static boolean runprogram = true;
	static Scanner scanner = new Scanner(System.in);
	static Product newProduct = new Product();
	static Initializers init = new Initializers();
	static Savers save = new Savers();
	enum Commands {
		LIST,
		CHECKOUT,
		CHECKIN,
		REGISTER,
		DEREGISTER,
		INFO,
		QUIT;
	}
	
	static Commands option(String options) {
		Commands usercommand = null;
		switch (options) {
		case "list": //See all products
			usercommand = Commands.LIST;
			newProduct.getBooksAndMovies();
			break;
		case "checkout": // Borrow book
			usercommand = Commands.CHECKOUT;
			System.out.print("Enter ID of product to checkout:");
			int checkoutID = Integer.parseInt(scanner.nextLine());
			newProduct.searchAndBorrow(checkoutID);
			break;
		case "checkin": // return borrowed book
			usercommand = Commands.CHECKIN;
			try {
				System.out.println("Enter ID of product to return:");
				int id = scanner.nextInt();
				newProduct.searchAndReturn(id);
			} catch (InputMismatchException e) {
				System.out.println("Invalid input, try again");
			}
			break;
		case "register": // add book
			usercommand = Commands.REGISTER;
			System.out.println("Enter \"b\" for book or \"m\" for movie");
			try {
			char c = scanner.next(".").charAt(0);
				newProduct.dialogue(c);
				break;
			}catch(InputMismatchException e) {
				break;
			}
		case "deregister": // remove book
			usercommand = Commands.DEREGISTER;
			try {
				System.out.println("Enter ID of product to remove:");
				int id = scanner.nextInt();
				newProduct.removeAtID(id);
			} catch (InputMismatchException e) {
				System.out.println("Invalid input, try again");
			}
			break;
		case "info": //Info for product
			usercommand = Commands.INFO;
			try	{
				System.out.println("Enter ID of product to search for:");
				int id = scanner.nextInt();
				newProduct.searchID(id);
			} catch (InputMismatchException e)	{
				System.out.println("Invalid input, try again");
			}
			break;
		case "quit": //Quit program
			usercommand = Commands.QUIT;
			System.exit(0);
			break;
		default:
			System.out.println("Invalid Command, try again");
			break;
		}
		return usercommand;
	}
	

	public static void main(String[] args) {
		
		//Test
		init.initializeUnAvailableProductsList();
		init.initializeCustomerList();
		init.initializeIdList();
		init.initializeMovieList();
		init.initializeBookList();
		
		save.saveCustomerList();
		save.saveUnAvailableProductsList();
		
		
		//Original
//		newProduct.initializeUnAvailableProductsList();
//		
//		newProduct.initializeCustomerList();
		
		newProduct.customer.customerList.clear();
//		newProduct.saveCustomerList();
		newProduct.unAvailableProducts.clear();
//		newProduct.saveUnAvailableProductsList();
		
//		newProduct.initializeIdList();
//
//		newProduct.initializeMovieList();
//		
//		newProduct.initializeBookList();
		
		while (runprogram) {
			// TEST
			String useroption;
			System.out.println("enter command");
			useroption = scanner.nextLine();
			option(useroption);
			//	
		}
		
		
	}

}
