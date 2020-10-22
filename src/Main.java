import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	static BookLibrary newBook;
	static boolean runprogram = true;
	static Scanner scanner = new Scanner(System.in);

	
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
	switch(options) {
	case "list":
		usercommand = Commands.LIST;
		newBook.getList();
		break;
	case "checkout":
		usercommand = Commands.CHECKOUT;
		break;
	case "checkin":
		usercommand = Commands.CHECKIN;
		break;
	case "register":
		usercommand = Commands.REGISTER;
		break;
	case "deregister":
		usercommand = Commands.DEREGISTER;
		break;
	case "info":
		usercommand = Commands.INFO;
		int id = scanner.nextInt();
		newBook.searchID(id);
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
		//TEST
		newBook = new BookLibrary('b', 12346, "BookName1", 100, 250, "Author");
		newBook = new BookLibrary('m', 12345, "BookName2", 300, 500, "Author2");
		newBook = new BookLibrary('m', 12344, "BookName3", 400, 150, "Author3");
		newBook = new BookLibrary('m', 12333, "BookName4", 200, 100, "Author4");
		//
		
		while(runprogram) {
			//TEST
			System.out.println("enter command");
			String useroption = scanner.next();
			option(useroption);
			//
			
		}
	}

}
