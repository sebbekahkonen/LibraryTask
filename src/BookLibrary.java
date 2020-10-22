import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BookLibrary {
	protected char b;
	protected int id;
	protected String title;
	protected int value;
	protected int pages;
	protected String publisher;
	private static List<String> book = new ArrayList<String>();
	private static List<Integer> ID = new ArrayList<Integer>();

	public BookLibrary(char b, int id, String title, int value, int pages, String publisher) {
		book.add(id+" Title: "+title+", Value: "+value+"kr, Pages: "+ pages+", Author: "+publisher);
		Collections.sort(book);
	}
	public void getList() {
		for(String str : book) {
			System.out.println(str);
		}
	}
	public void searchID(int id) {
		String[] splitString = null;
		for(String search : book) {
			splitString = search.split(" ");
			if(Integer.valueOf(splitString[0]).equals(id)) {
				System.out.println(search);
			}
		}
		if(!Integer.valueOf(splitString[0]).equals(id)) {
			System.out.println("doesnt exist");
		}
	}
}
