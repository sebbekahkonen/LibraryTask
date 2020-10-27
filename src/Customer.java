import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Customer implements Serializable {
	private static final int serialVersionUID = 136420;
	protected static List<Customer> customerList = new ArrayList<Customer>();
	protected String name;
	protected String number;
	protected static List<Integer> borrowedProducts = new ArrayList<Integer>();
	
	public Customer(String name, String number, List list) {
		this.name = name;
		this.number = number;
		this.borrowedProducts = list;
	}
	public String getCustomer() {
		return String.format("Name: %s, Number: %s",name, number);
	}
	
	
}	
