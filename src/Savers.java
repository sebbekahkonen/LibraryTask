import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Savers extends Product{
	private static final int serialVersionUID = 136420;
	
	public void saveCustomerList() {
		File file = new File("customer_list.bin");
		FileOutputStream fout;
		try {
			fout = new FileOutputStream(file);
			ObjectOutputStream out = new ObjectOutputStream(fout);
			out.writeObject(customer.customerList);
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void saveBookList() {
		File file = new File("book_list.bin");
		FileOutputStream fout;
		try {
			fout = new FileOutputStream(file);
			ObjectOutputStream out = new ObjectOutputStream(fout);
			out.writeObject(books);
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void saveIdList() {
		File file = new File("id_list.bin");
		FileOutputStream fout;
		try {
			fout = new FileOutputStream(file);
			ObjectOutputStream out = new ObjectOutputStream(fout);
			out.writeObject(saveid);
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void saveMovieList() {
		File file = new File("movie_list.bin");
		FileOutputStream fout;
		try {
			fout = new FileOutputStream(file);
			ObjectOutputStream out = new ObjectOutputStream(fout);
			out.writeObject(movies);
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
