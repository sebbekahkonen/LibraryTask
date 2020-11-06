import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.Scanner;

	/*Class Main holds Enum commands, initializes state and holds main method
	 * 
	 *@author Niklas Kullberg
	 *@author Sebastian Kahkonen
	 */
public class Main implements Serializable {
	private static final long serialVersionUID = 136420;
	
	/*newProduct -- used to reach functionality in class Product 
	 */
	static Library newProduct = new Library();
	static private Scanner userinput;
	
	/*Available Commands, enum
	 * list -- prints inventory
	 * checkout -- check out product to Customer
	 * checkin -- check in product from Customer
	 * register -- add product to inventory
	 * deregister -- remove product from inventory
	 * info -- print info about specific product using product ID
	 * customerinfo -- print info about Customer using customers name
	 * quit -- end program
	 * view -- view available commands while program is running
	 */
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
	
	/*Sends user through program according to users entered command 
	 */
	static Commands option(String options) {
		Commands usercommand = null;
		try {
			userinput = new Scanner(System.in);
			switch (options) {
			case "list": 
				usercommand = Commands.LIST;
				newProduct.getProducts();
				break;
			case "checkout": 
				usercommand = Commands.CHECKOUT;
				System.out.print("Enter ID of product to checkout:\n>");
				int checkoutID = Integer.parseInt(userinput.nextLine());
				newProduct.productBorrow(checkoutID);
				break;
			case "checkin": 
				usercommand = Commands.CHECKIN;
				System.out.print("Enter ID of product to return:\n>");
				int checkinID = Integer.parseInt(userinput.nextLine());
				newProduct.productReturn(checkinID);
				break;
			case "register": 
				usercommand = Commands.REGISTER;
				System.out.print("Enter \"b\" for book or \"m\" for movie:\n>");
				char c = userinput.next(".").charAt(0);
				newProduct.registerDialogue(c);
				break;
			case "deregister": 
				usercommand = Commands.DEREGISTER;
				System.out.print("Enter ID of product to remove:\n>");
				int deregisterID = Integer.parseInt(userinput.nextLine());
				newProduct.removeAtID(deregisterID);
				break;
			case "info":
				usercommand = Commands.INFO;
				if(Library.products.isEmpty()) {
					System.out.println("There are no products registered");
				}else {
					System.out.print("Enter ID of product to search for:\n>");
					int infoID = Integer.parseInt(userinput.nextLine());
					newProduct.getInfo(infoID);	
				}
				break;
			case "customerinfo":
				usercommand = Commands.CUSTOMERINFO;
				if (Customer.customerList.isEmpty()) {
					System.out.println("There are no customers registered");
				} else {
					System.out.print("Enter name of the customer to search for:\n>");
					String name = userinput.nextLine();
					newProduct.getCustomerinfo(name);
				}
				break;
			case "quit": 
				usercommand = Commands.QUIT;
				System.out.println("Exiting program...");
				System.exit(0);
				break;
			case "view": 
				usercommand = Commands.VIEW;
				welcomeUser(0);
				break;
			default:
				System.out.println("That is not a valid command, try again");
				break;
			}
		} catch (NumberFormatException e) {
			System.out.println("Invalid format, try using numbers");
		} catch (InputMismatchException e) {
			System.out.println("Invalid format, try using a char");
		}
		return usercommand;
	}
	
	/*method used to initialize state of program
	 */
	public static void initializeState() {
		Library.saveOrInit.initializeUnAvailableProductsList();
		Library.saveOrInit.initializeCustomerList();
		InitializersAndSavers.initializeProductList();
	}
	
	public static void welcomeUser(int x) {
		if (x == 1) {
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

		while (true) {
			userinput = new Scanner(System.in);
			String usercommand;
			System.out.print("Enter a command\n>");
			usercommand = userinput.next().toLowerCase();
			option(usercommand);
		}

	}

}