package id_206863185;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Product implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -721292297511823577L;
	protected final int id;
	protected String productName;
	protected int quantity;
	protected LocalDateTime timeStamp;
	protected String category;

	public Product(int id, String productName, int quantity, String category, LocalDateTime timeStamp) {
		this.id = id;
		this.productName = productName;
		this.quantity = quantity;
		this.timeStamp = timeStamp;
		this.category = category;
	}

	public String getCategory() {
		return category;
	}

	public String getTimeStamp() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		return timeStamp.format(formatter);
	}

	public void setTimeStamp(LocalDateTime timeStamp) {
		this.timeStamp = timeStamp;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getProductName() {
		return productName;
	}

	@Override
	public String toString() {
		return "Product id= " + id + " " + getClass().getSimpleName() + " name= " + productName + ", quantity = "
				+ quantity;
	}
}
