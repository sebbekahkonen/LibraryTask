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
	
	static boolean runprogram = true;
	static Scanner scanner = new Scanner(System.in);
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
		switch (options) {
		case "list":
			usercommand = Commands.LIST;
			newProduct.getBooksAndMovies();
			break;
		case "checkout": // Borrow book
			usercommand = Commands.CHECKOUT;
			int checkoutID = scanner.nextInt();
			break;
		case "checkin": // return borrowed book

			usercommand = Commands.CHECKIN;
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

			break;
		case "info":
			usercommand = Commands.INFO;
			int id = scanner.nextInt();
			newProduct.searchID(id);
			break;
		case "quit":
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
		
		
//		newProduct.initializeIdList();
		
//		newProduct.initializeMovieList();
//		
//		newProduct.initializeBookList();
//		
		while (runprogram) {
			// TEST
			System.out.println("enter command");
			String useroption = scanner.next();
			option(useroption);
			//	
		}
	}

}
