import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;

public class InitializersAndSavers extends Library implements Serializable {
	private static final int serialVersionUID = 136420;

	// Initializers
	public static void initializeProductList() {
		try {
			File file = new File("product_list.bin");
			FileInputStream fin = new FileInputStream(file);
			ObjectInputStream oin = new ObjectInputStream(fin);
			products = (List<Product>) oin.readObject();
			oin.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void initializeUnAvailableProductsList() {
		try {
			File file = new File("unavailableproducts_list.bin");
			FileInputStream fin = new FileInputStream(file);
			ObjectInputStream oin = new ObjectInputStream(fin);
			unAvailableProducts = (List<Integer>) oin.readObject();
			oin.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void initializeCustomerList() {
		try {
			File file = new File("customer_list.bin");
			FileInputStream fin = new FileInputStream(file);
			ObjectInputStream oin = new ObjectInputStream(fin);
			customer.customerList = (List<Customer>) oin.readObject();
			oin.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	// Savers
	public void saveCustomerList() {
		File file = new File("customer_list.bin");
		FileOutputStream fout;
		try {
			fout = new FileOutputStream(file);
			ObjectOutputStream out = new ObjectOutputStream(fout);
			out.writeObject(customer.customerList);
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void saveUnAvailableProductsList() {
		File file = new File("unavailableproducts_list.bin");
		FileOutputStream fout;
		try {
			fout = new FileOutputStream(file);
			ObjectOutputStream out = new ObjectOutputStream(fout);
			out.writeObject(unAvailableProducts);
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void saveProductList() {
		File file = new File("product_list.bin");
		FileOutputStream fout;
		try {
			fout = new FileOutputStream(file);
			ObjectOutputStream out = new ObjectOutputStream(fout);
			out.writeObject(products);
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
