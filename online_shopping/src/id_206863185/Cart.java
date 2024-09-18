package id_206863185;

import java.time.LocalDateTime;

public class Cart {

	private Product[] productsInCart;
	private final static int maximumNumberOfProductsInCart = 5;

	public Cart(Product[] productsInCart) {
		this.productsInCart = productsInCart;

	}

	public Product[] getProductsInCart() {
		return productsInCart;
	}

	public void setProductsInCart(Product[] productsInCart) {
		this.productsInCart = productsInCart;
	}

	public int numOfProducts() {
		int productsQuantity = 0;
		for (int i = 0; i < this.productsInCart.length; i++) {
			productsQuantity += this.productsInCart[i].getQuantity();
		}
		return productsQuantity;
	}

	public boolean checkIfCartIsEmpty() {
		for (int i = 0; i < this.productsInCart.length; i++) {
			if (this.productsInCart[i].getQuantity() > 0) {
				return false;
			}
		}
		return true;
	}

	public void printCart() {
		if (checkIfCartIsEmpty()) {
			System.out.println("Your cart is empty");
		}
		for (int i = 0; i < this.productsInCart.length; i++) {
			if (this.productsInCart[i].getQuantity() > 0) {
				Product product = this.productsInCart[i];
				System.out.println(product + " " + product.getTimeStamp());
			}
		}
	}

	public void addProductToCart(int productId, int quantity) throws ReachedMaxAmountException {
		int currentQuantityOfSpecificProduct = this.productsInCart[productId].getQuantity();
		int currentQuantityOfWholeCart = numOfProducts();
		int futureTotalNumberOfProductsInCart = currentQuantityOfWholeCart + quantity;
		if (futureTotalNumberOfProductsInCart > maximumNumberOfProductsInCart) {
			throw new ReachedMaxAmountException(
					"Maximum products you can add to your cart are: " + maximumNumberOfProductsInCart);
		}
		this.productsInCart[productId].setQuantity(currentQuantityOfSpecificProduct + quantity);
		this.productsInCart[productId].setTimeStamp(LocalDateTime.now());
	}
}
