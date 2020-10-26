import java.util.ArrayList;
import java.util.List;

public class Customer {
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
		return String.format("Name: %s, number: %s", name, number);
	}

	public Object contains(String name) {						//Vad gör denna metod?
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
