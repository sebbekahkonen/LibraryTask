import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Customer implements Serializable {
	private static final int serialVersionUID = 136420;
	protected static List<Customer> customerList = new ArrayList<Customer>();
	protected String name;
	protected String number;
	protected List<Integer> borrowedProducts = new ArrayList<Integer>();
	protected Product newProduct;
	
	public Customer(String name, String number, List list) {
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
