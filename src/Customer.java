import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

	/*Class Customer is used to represent customers borrowing products from Library
	 * Constructor for customer and list of current customers
	 * 
	 *@author Niklas Kullberg
	 *@author Sebastian Kahkonen
	 */
public class Customer implements Serializable {
	private static final long serialVersionUID = 136420;
	
	/*customerList -- list of customers that currently have product borrowed from library
	 * name -- holds name of customer
	 * number -- holds phoneNumber to customer
	 * borrowedProducts -- list of this instance of customer's currently borrowed products
	 */
	protected static List<Customer> customerList = new ArrayList<Customer>();
	protected String name;
	protected String number;
	protected List<Integer> borrowedProducts = new ArrayList<Integer>();
	
	/*Constructor for Customer
	 * @param name, sets name for this instance of Customer
	 * @param number, sets phoneNumber for this instance of Customer
	 * @param list, holds list of borrowed products for this instance of Customer
	 */
	public Customer(String name, String number, List<Integer> list) {
		this.name = name;
		this.number = number;
		this.borrowedProducts = list;
	}
	
	public String getCustomer() {
		return String.format("Name: %s, Number: %s",name, number);
	}
	
	public String getCustomerWithList() {
		return String.format("Name: %s, Number: %s, Borrowed: %s",name, number, borrowedProducts);
	}
	
	
}	
