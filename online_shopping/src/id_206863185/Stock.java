package id_206863185;

import java.util.Arrays;

public class Stock {

	private Product[] products;

	public Stock(Product[] products) {
		this.products = products;
	}

	public Product[] getProducts() {
		return products;
	}

	@Override
	public String toString() {
		return "Stock [products=" + Arrays.toString(products) + "]";
	}

	public void removeProductUnits(int id, int quantity) {
		int currentQuantity = this.products[id].getQuantity();
		this.products[id].setQuantity(currentQuantity - quantity);
	}

	public void addProductUnits(int id, int quantity) {
		int currentQuantity = this.products[id].getQuantity();
		this.products[id].setQuantity(currentQuantity + quantity);
	}
}