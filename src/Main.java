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
	static boolean runProgram = true;
	static Library newProduct = new Library();
	
	
	enum Commands {
		LIST,
		CHECKOUT,
		CHECKIN,
		REGISTER,
		DEREGISTER,
		INFO,
		CUSTOMERINFO,
		QUIT,
		VIEW;
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
			System.out.print("Enter ID of product to checkout:\n>");
			int checkoutID = Integer.parseInt(scanner.nextLine());
			newProduct.productBorrow(checkoutID);
			break;
		case "checkin": // return borrowed book
			usercommand = Commands.CHECKIN;
			System.out.print("Enter ID of product to return:\n>");
			int checkinID = Integer.parseInt(scanner.nextLine());
			newProduct.productReturn(checkinID);
			break;
		case "register": // add book
			usercommand = Commands.REGISTER;
			System.out.print("Enter \"b\" for book or \"m\" for movie:\n>");
			char c = scanner.next(".").charAt(0);
			newProduct.registerDialogue(c);
				break;
		case "deregister": // remove book
			usercommand = Commands.DEREGISTER;
			System.out.print("Enter ID of product to remove:\n>");
			int deregisterID = Integer.parseInt(scanner.nextLine());
			newProduct.removeAtID(deregisterID);
			break;
		case "info": //Info for product
			usercommand = Commands.INFO;
			System.out.print("Enter ID of product to search for:\n>");
			int infoID = Integer.parseInt(scanner.nextLine());
			newProduct.searchID(infoID);
			break;
		case "customerinfo":
			usercommand = Commands.CUSTOMERINFO;
			System.out.print("Enter name of the customer to search for:\n>");
			String name = scanner.nextLine();
			newProduct.searchCustomer(name);
			break;
		case "quit": //Quit program
			usercommand = Commands.QUIT;
			System.out.println("Exiting program...");
			System.exit(0);
			break;
		case "view": //View commands
			usercommand = Commands.VIEW;
			welcomeUser(0);
			break;
		default:
			System.out.println("That is not a valid command, try again");
			break;
		}
		}catch(InputMismatchException e) {
			System.out.println("Invalid format, try again");
		}
		return usercommand;
	}
	
	public static void initializeState()	{
//		newProduct.saveOrInit.initializeUnAvailableProductsList();
//		newProduct.saveOrInit.initializeCustomerList();
//		newProduct.saveOrInit.initializeIdList();
//		newProduct.saveOrInit.initializeMovieList();
//		newProduct.saveOrInit.initializeBookList();
	}
	
	public static void welcomeUser(int x)	{
		if (x == 1)	{
			System.out.println("\t\tLibrary System");
		}
		System.out.println("\t\t  COMMANDS:");
		System.out.println("\t List:           View inventory");
		System.out.println("\t Checkout:       Checkout product to customer");
		System.out.println("\t Checkin:        Checkin product from customer");
		System.out.println("\t Register:       Register new product to inventory");
		System.out.println("\t Deregister:     Remove product from inventory");
		System.out.println("\t Info:           Search info for product via product ID");
		System.out.println("\t Customerinfo:   Get info about customer");
		System.out.println("\t View:           View available commands again");
		System.out.println("\t Quit:           Exit program");
		
	}

	public static void main(String[] args) {
		initializeState();
		welcomeUser(1);

		while (runProgram) {
			Scanner useroption = new Scanner(System.in);
			String usercommand;
			System.out.print("Enter a command\n>");
			usercommand = useroption.next().toLowerCase();
			option(usercommand);
		}//Hejhej
		
		
		
	}

}