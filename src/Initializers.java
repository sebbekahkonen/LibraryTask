import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

public class Initializers extends Product{
	private static final int serialVersionUID = 136420;
	
	public static void initializeBookList() {
		try {
			File file = new File("book_list.bin");
			FileInputStream fin = new FileInputStream(file);
			ObjectInputStream oin = new ObjectInputStream(fin);
			books = (List<Book>) oin.readObject();
			oin.close();
			for (Book book : books) {
				System.out.println(book.getBooksString());
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void initializeMovieList()	{
		try {
			File file = new File("movie_list.bin");
			FileInputStream fin = new FileInputStream(file);
			ObjectInputStream oin = new ObjectInputStream(fin);
			movies = (List<Movie>) oin.readObject();
			oin.close();
			for (Movie movie : movies) {
				System.out.println(movie.getMoviesString());
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void initializeIdList()	{
		try {
			File file = new File("id_list.bin");
			FileInputStream fin = new FileInputStream(file);
			ObjectInputStream oin = new ObjectInputStream(fin);
			saveid = (List<Integer>) oin.readObject();
			oin.close();
			System.out.println(saveid);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void initializeUnAvailableProductsList()	{
		try {
			File file = new File("unavailableproducts_list.bin");
			FileInputStream fin = new FileInputStream(file);
			ObjectInputStream oin = new ObjectInputStream(fin);
			unAvailableProducts = (List<Integer>) oin.readObject();
			oin.close();
			System.out.println(unAvailableProducts);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void initializeCustomerList()	{
		try {
			File file = new File("customer_list.bin");
			FileInputStream fin = new FileInputStream(file);
			ObjectInputStream oin = new ObjectInputStream(fin);
			customer.customerList = (List<Customer>) oin.readObject();
			oin.close();
			for (Customer customer : customer.customerList)	{
				System.out.println(customer.getCustomer());
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
