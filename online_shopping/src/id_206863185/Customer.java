package id_206863185;

import java.time.LocalDateTime;

public class Customer {

	private Cart customerCart;

	public Customer(Cart customerCart) {
		this.customerCart = customerCart;
	}

	public Cart getCustomerCart() {
		return customerCart;
	}

	public void addingProduct(int productId, int quantity) {
		int currentQuantity = this.customerCart.getProductsInCart()[productId].getQuantity();
		Product product = this.customerCart.getProductsInCart()[productId];
		product.setQuantity(currentQuantity + quantity);
		product.setTimeStamp(LocalDateTime.now());
	}

	public void removingProduct(int productId, int quantity) {
		int currentQuantity = this.customerCart.getProductsInCart()[productId].getQuantity();
		Product product = this.customerCart.getProductsInCart()[productId];
		product.setQuantity(currentQuantity - quantity);
		product.setTimeStamp(LocalDateTime.now());
	}

	public void removingAllUnitsOfProduct(int productId, int quantity) {
		Product product = this.customerCart.getProductsInCart()[productId];
		product.setQuantity(0);
		product.setTimeStamp(LocalDateTime.now());
	}
}
