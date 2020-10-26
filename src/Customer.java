import java.util.ArrayList;
import java.util.List;

public class Customer {
	protected static List<Customer> customer = new ArrayList<Customer>();
	protected String name;
	protected int number;
	
	public Customer(String name, int number, List Integer) {
		
	}
	public String getCustomer() {
		return String.format("Name: %s, number: %s", name, number);
	}

	public Object contains(String name) {
		String[] splitCustomer = null;
		for(Customer customer : customer) {
			splitCustomer = customer.getCustomer().split(",");
			if(splitCustomer[0].contains(name)) {
				return customer;
			}
		}
		return false;
		
	}
}	
