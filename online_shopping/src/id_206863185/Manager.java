package id_206863185;

public class Manager implements Reservable {
	private Stock stock;
	private Customer customer;

	public Manager(Stock stock, Customer costumer) {
		this.stock = stock;
		this.customer = costumer;
	}

	public void addProductToCartAndRemoveFromStock(int id, int quantity) throws OnlineStoreGeneralException {
		if (id > this.stock.getProducts().length - 1) {
			throw new CartProductNotExistException("There is no product with id=" + id);
		}
		if (quantity > this.stock.getProducts()[id].getQuantity()) {
			throw new ProductQuantityNotAvailableException(
					"Maximum units we have are: " + this.stock.getProducts()[id].getQuantity());
		}
		if (this.customer.getCustomerCart().getProductsInCart()[id].getQuantity() != 0) {
			throw new CartProductAlreadyExistException("You already have this product in your cart");
		}
//		this.customer.addingProduct(id, quantity);
//		this.customer.getCustomerCart().addProductToCart(id, quantity);
//		this.stock.removeProductUnits(id, quantity);
		reserve(quantity, id, "add");
	}

	public void updateUpProductToCartAndRemoveFromStock(int id, int quantity) throws OnlineStoreGeneralException {
		if (id > this.stock.getProducts().length - 1) {
			throw new CartProductNotExistException("There is no product with id= " + id);
		}
		if (this.customer.getCustomerCart().getProductsInCart()[id].getQuantity() == 0) {
			throw new CartProductNotExistException("The product is not in your cart");
		}
		if (quantity > this.stock.getProducts()[id].getQuantity()) {
			throw new ProductQuantityNotAvailableException(
					"Maximum units we have are: " + this.stock.getProducts()[id].getQuantity());

		}
//		this.customer.addingProduct(id, quantity);
//		this.customer.getCustomerCart().addProductToCart(id, quantity);
//		this.stock.removeProductUnits(id, quantity);
		reserve(quantity, id, "add");

	}

	public void updateDownProductUnitFromCartAndAddToStock(int id, int quantity) throws OnlineStoreGeneralException {
		if (id > this.stock.getProducts().length - 1) {
			throw new CartProductNotExistException("There is no product with id= " + id);
		}
		if (this.customer.getCustomerCart().getProductsInCart()[id].getQuantity() == 0) {
			throw new CartProductNotExistException("The product is not in your cart");
		}
		if (this.customer.getCustomerCart().getProductsInCart()[id].getQuantity() < quantity) {
			throw new ProductQuantityNotAvailableException("You can not remove more units than the quantity you have");
		}
//		this.customer.removingProduct(id, quantity);
//		this.stock.addProductUnits(id, quantity);
		reserve(quantity, id, "remove");

	}

	public void removeAllUnitsOfProductFromCartAndAddToStock(int id) throws OnlineStoreGeneralException {
		if (id > this.stock.getProducts().length - 1) {
			throw new CartProductNotExistException("There is no product with id= " + id);
		}
		if (this.customer.getCustomerCart().getProductsInCart()[id].getQuantity() == 0) {
			throw new CartProductNotExistException("The product is not in your cart");
		}
		int quantityToReturnToStock = this.customer.getCustomerCart().getProductsInCart()[id].getQuantity();
		this.customer.removingAllUnitsOfProduct(id, quantityToReturnToStock);
		this.stock.addProductUnits(id, quantityToReturnToStock);
	}

	public void printStockForManager() {
		for (int i = 0; i < stock.getProducts().length; i++) {
			if (stock.getProducts()[i].getQuantity() > 0 && stock.getProducts()[i] instanceof Book) {
				if (customer.getCustomerCart().getProductsInCart()[i].getQuantity() == 0) {
					System.out.println(stock.getProducts()[i]);
				}
			}
		}
		for (int i = 0; i < stock.getProducts().length; i++) {
			if (stock.getProducts()[i].getQuantity() > 0 && stock.getProducts()[i] instanceof Clothing) {
				if (customer.getCustomerCart().getProductsInCart()[i].getQuantity() == 0) {
					System.out.println(stock.getProducts()[i]);
				}
			}
		}
		for (int i = 0; i < stock.getProducts().length; i++) {
			if (stock.getProducts()[i].getQuantity() > 0 && stock.getProducts()[i] instanceof Electronics) {
				if (customer.getCustomerCart().getProductsInCart()[i].getQuantity() == 0) {
					System.out.println(stock.getProducts()[i]);
				}
			}
		}
	}

	public void printProducts(Product[] products) {
		for (int i = 0; i < products.length; i++) {
			System.out.println(products[i]);
		}
	}

	@Override
	public String toString() {
		return "Manager [stock=" + stock + ", customer=" + customer + "]";
	}

	@Override
	public void reserve(int quantity, int id, String action) throws ReachedMaxAmountException {
		Product product = this.customer.getCustomerCart().getProductsInCart()[id];
		int futureQuantityOfProductInCart = product.getQuantity() + quantity;
		if (product instanceof Electronics && action == "add" && futureQuantityOfProductInCart > 3) {
			System.out.println("You can not add more than 3 units from electronics items");
		} else {
			if (action == "add") {
				this.customer.getCustomerCart().addProductToCart(id, quantity);
				this.stock.removeProductUnits(id, quantity);
			}
		}
		if (action == "remove") {
			this.customer.removingProduct(id, quantity);
			this.stock.addProductUnits(id, quantity);
		}
	}
}
