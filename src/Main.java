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
		
//		Movie m1 = new Movie(5566, "Made in Senegal", 249, 72, 7.8f);
//		Movie m2 = new Movie(5567, "Joker", 210, 122, 8.5f);
//		
//		newProduct.movies.add(m1);
//		newProduct.movies.add(m2);
//		
//		File file = new File("movie_list.bin");
//		try {
//			FileOutputStream fout = new FileOutputStream(file);
//			ObjectOutputStream out = new ObjectOutputStream(fout);
//			out.writeObject();
//			out.close();
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		newProduct.initializeMovieList();
		
//		newProduct.initializeBookList();
//		
//		while (runprogram) {
//			// TEST
//			System.out.println("enter command");
//			String useroption = scanner.next();
//			option(useroption);
//			//	
//		}
	}

}
