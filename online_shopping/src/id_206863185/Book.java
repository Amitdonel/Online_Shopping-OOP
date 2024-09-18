package id_206863185;

import java.time.LocalDateTime;

public class Book extends Product {

	private String author;
	private int numOfPages;

	public Book(int id, String productName, int quantity, String author, int numOfPages, String category,
			LocalDateTime timeStamp) {
		super(id, productName, quantity, category, timeStamp);
		this.author = author;
		this.numOfPages = numOfPages;
	}

	@Override
	public String toString() {
		return super.toString() + ", Author name: " + author + ", The number of pages: " + numOfPages;
	}
}
