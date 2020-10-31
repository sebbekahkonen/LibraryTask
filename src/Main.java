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
import java.util.regex.Pattern;
public class Main implements Serializable {
	private static final int serialVersionUID = 136420;
	static boolean runprogram = true;
	static Product newProduct = new Product();
	
	
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
		try {
		Scanner scanner = new Scanner(System.in);
		switch (options) {
		case "list": //See all products
			usercommand = Commands.LIST;
			newProduct.getBooksAndMovies();
			break;
		case "checkout": // Borrow book
			usercommand = Commands.CHECKOUT;
			System.out.print("Enter ID of product to checkout: ");
			int checkoutID = scanner.nextInt();
			newProduct.productBorrow(checkoutID);
			break;
		case "checkin": // return borrowed book
			usercommand = Commands.CHECKIN;
			System.out.print("Enter ID of product to return: ");
			int checkinID = scanner.nextInt();
			newProduct.productReturn(checkinID);
			break;
		case "register": // add book
			usercommand = Commands.REGISTER;
			System.out.print("Enter \"b\" for book or \"m\" for movie: ");
			char c = scanner.next(".").charAt(0);
			newProduct.registerDialogue(c);
				break;
		case "deregister": // remove book
			usercommand = Commands.DEREGISTER;
			System.out.print("Enter ID of product to remove: ");
			int deregisterID = scanner.nextInt();
			newProduct.removeAtID(deregisterID);
			break;
		case "info": //Info for product
			usercommand = Commands.INFO;
			System.out.print("Enter ID of product to search for: ");
			int infoID = scanner.nextInt();
			newProduct.searchID(infoID);
			break;
		case "quit": //Quit program
			usercommand = Commands.QUIT;
			System.out.println("Stopping program...");
			System.exit(0);
			break;
		}
		}catch(InputMismatchException e) {
			System.out.println("Invalid format, try again");
		}
		return usercommand;
	}

	public static void main(String[] args) {
		newProduct.saveOrInit.initializeUnAvailableProductsList();
		newProduct.saveOrInit.initializeCustomerList();
		newProduct.saveOrInit.initializeIdList();
		newProduct.saveOrInit.initializeMovieList();
		newProduct.saveOrInit.initializeBookList();
		newProduct.saveOrInit.saveCustomerList();
		newProduct.saveOrInit.saveUnAvailableProductsList();
		newProduct.customer.customerList.clear();
		newProduct.unAvailableProducts.clear();

		while (runprogram) {
			Scanner useroption = new Scanner(System.in);
			String usercommand;
			System.out.println("enter command");
			usercommand = useroption.next();
			if(Pattern.matches("[a-z]{4,10}", usercommand)){
				runprogram = true;
				option(usercommand);
			}else {
				System.out.println("Sorry that's not a command");
			}
		}
		
		
	}

}
