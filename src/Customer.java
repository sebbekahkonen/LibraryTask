import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Customer implements Serializable {
	private static final int serialVersionUID = 136420;
	protected static List<Customer> customerList = new ArrayList<Customer>();
	protected String name;
	protected int number;
	protected List<Integer> borrowedProducts = new ArrayList<Integer>();
	
	public Customer(String name, int number, List list) {
		this.name = name;
		this.number = number;
		this.borrowedProducts = list;
	}
	public String getCustomer() {
		String customerString = "Name: " + this.name + " Number: " + this.number;
		return customerString;
	}

	public Object contains(String name) {						
		String[] splitCustomer = null;							
		for(Customer customer : customerList) {
			splitCustomer = customer.getCustomer().split(",");
			if(splitCustomer[0].contains(name)) {
				return customer;
			}
		}
		return false;
		
	}
	
	
}	
