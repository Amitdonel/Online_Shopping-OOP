package id_206863185;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
import id_206863185.Clothing.Gender;

public class Main {

	public static Product[] getProductsFromCsvToStockArray() throws IOException {
		Product[] stockProducts = new Product[30];
		File csvFile = new File("products_list2.csv");
		BufferedReader br = new BufferedReader(new FileReader(csvFile));
		String line = "";
		int productsId = 0;
		br.readLine();
		while ((line = br.readLine()) != null) {
			String[] excelLine = line.split(",");
			LocalDateTime timeStamp = LocalDateTime.now();
			if (excelLine[0].equals("Books")) {
				Product p1 = new Book(productsId, excelLine[1], Integer.parseInt(excelLine[2]), excelLine[3],
						Integer.parseInt(excelLine[4]), line, timeStamp);
				stockProducts[productsId] = p1;
				productsId++;
			}
			if (excelLine[0].equals("Clothing")) {
				Gender gender = Gender.valueOf(excelLine[5]);
				Product p1 = new Clothing(productsId, excelLine[1], Integer.parseInt(excelLine[2]), excelLine[3],
						excelLine[4], gender, line, timeStamp);
				stockProducts[productsId] = p1;
				productsId++;
			}
			if (excelLine[0].equals("Electronics")) {
				Product p1 = new Electronics(productsId, excelLine[1], Integer.parseInt(excelLine[2]), excelLine[3],
						excelLine[4], line, timeStamp);
				stockProducts[productsId] = p1;
				productsId++;
			}
		}
		br.close();
		return stockProducts;
	}

	public static Product[] getProductsFromCsvToCartArray() throws IOException {
		Product[] productsInCart = new Product[30];
		File csvFile = new File("products_list2.csv");
		BufferedReader br = new BufferedReader(new FileReader(csvFile));
		String line = "";
		int productsId = 0;
		br.readLine();
		while ((line = br.readLine()) != null) {
			String[] excelLine = line.split(",");
			LocalDateTime timestamp = LocalDateTime.now();
			if (excelLine[0].equals("Books")) {
				Product p1 = new Book(productsId, excelLine[1], 0, excelLine[3], Integer.parseInt(excelLine[4]), line,
						timestamp);
				productsInCart[productsId] = p1;
				productsId++;
			}
			if (excelLine[0].equals("Clothing")) {
				Gender gender = Gender.valueOf(excelLine[5]);
				Product p1 = new Clothing(productsId, excelLine[1], 0, excelLine[3], excelLine[4], gender, line,
						timestamp);
				productsInCart[productsId] = p1;
				productsId++;
			}
			if (excelLine[0].equals("Electronics")) {
				Product p1 = new Electronics(productsId, excelLine[1], 0, excelLine[3], excelLine[4], line, timestamp);
				productsInCart[productsId] = p1;
				productsId++;
			}
		}
		br.close();
		return productsInCart;
	}

	public static void main(String[] args) throws IOException, OnlineStoreGeneralException, ClassNotFoundException {
//		Product[] stockProducts = getProductsFromCsvToStockArray();
		Product[] stockProducts = readStockProducts("data.dat");	// this line reads from a binary file :)
		Product[] productsInCart = getProductsFromCsvToCartArray();
		Stock stock = new Stock(stockProducts);
		Cart cart = new Cart(productsInCart);
		Customer customer = new Customer(cart);
		Manager manager = new Manager(stock, customer);

		Scanner sc = new Scanner(System.in);

		final int showStock = 1;
		final int showCart = 2;
		final int addProduct = 3;
		final int removeProduct = 4;
		final int updateProduct = 5;
		final int sortProducts = 6;
		final int EXIT = 7;
		int choice;

		do {
			System.out.println("\n ONLINE SHOPINNG ");
			System.out.println("For showing the stock of the shop press 1");
			System.out.println("For showing your cart press 2");
			System.out.println("For adding a product to cart press 3");
			System.out.println("For removing a product from cart press 4");
			System.out.println("For updating a product from your cart press 5");
			System.out.println("For sorting the stock's products press 6");
			System.out.println("For Exit press 7");

			choice = sc.nextInt();
			switch (choice) {

			case showStock:
				manager.printStockForManager();
				break;

			case showCart:
				System.out.println("My cart: ");
				customer.getCustomerCart().printCart();
				break;

			case addProduct:
				System.out.println("Enter product id you would like to add: ");
				int id = sc.nextInt();
				System.out.println("Enter the quantity of it: ");
				int quantity = sc.nextInt();
				try {
					manager.addProductToCartAndRemoveFromStock(id, quantity);
				} catch (CartProductNotExistException e) {
					System.out.println(e.getMessage());
				} catch (ReachedMaxAmountException e) {
					System.out.println(e.getMessage());
				} catch (CartProductAlreadyExistException e) {
					System.out.println(e.getMessage());
				} catch (OnlineStoreGeneralException e) {
					System.out.println(e.getMessage());
				}

				break;

			case removeProduct:
				if (customer.getCustomerCart().checkIfCartIsEmpty()) {
					System.out.println("You can not remove a product because your cart is empty");
					break;
				}
				System.out.println("This is your cart: ");
				customer.getCustomerCart().printCart();
				System.out.println("\nEnter product id you would like to remove: ");
				id = sc.nextInt();
				try {
					manager.removeAllUnitsOfProductFromCartAndAddToStock(id);
				} catch (CartProductNotExistException e) {
					System.out.println(e.getMessage());
				} catch (OnlineStoreGeneralException e) {
					System.out.println(e.getMessage());
				}
				break;

			case updateProduct:
				if (customer.getCustomerCart().checkIfCartIsEmpty()) {
					System.out.println("You can not update a product because your cart is empty");
					break;
				}
				System.out.println("This is your cart: ");
				customer.getCustomerCart().printCart();
				System.out.println("\nEnter product id you would like to update: ");
				id = sc.nextInt();
				System.out.println(
						"Would you like to add or remove units from this product: (press--> 1 to add or 2 to remove) ");
				int pickAchoice;
				pickAchoice = sc.nextInt();
				if (pickAchoice == 1) {
					System.out.println("You chose to add, please enter the amount of units you want to add: ");
					quantity = sc.nextInt();
					try {
						manager.updateUpProductToCartAndRemoveFromStock(id, quantity);
					} catch (CartProductNotExistException e) {
						System.out.println(e.getMessage());
					} catch (ProductQuantityNotAvailableException e) {
						System.out.println(e.getMessage());
					} catch (ReachedMaxAmountException e) {
						System.out.println(e.getMessage());
					} catch (OnlineStoreGeneralException e) {
						System.out.println(e.getMessage());
					}
				}
				if (pickAchoice == 2) {
					System.out.println("You chose to remove, please enter the amount of units you want to remove: ");
					quantity = sc.nextInt();
					try {
						manager.updateDownProductUnitFromCartAndAddToStock(id, quantity);
					} catch (CartProductNotExistException e) {
						System.out.println(e.getMessage());
					} catch (ProductQuantityNotAvailableException e) {
						System.out.println(e.getMessage());
					} catch (OnlineStoreGeneralException e) {
						System.out.println(e.getMessage());
					}
				}
				if (pickAchoice != 1 && pickAchoice != 2) {
					System.out.println("Wrong input, please try again :)");
				}
				break;

			case sortProducts:
				System.out.println(
						"Choose how to sort the products list: \n1- Sort by category\n2- Sort by name\n3- Sort by available quantity");
				int pickSort;
				pickSort = sc.nextInt();
				Product[] productsToSort = Arrays.copyOf(stock.getProducts(), stock.getProducts().length);

				if (pickSort == 1) {
					Arrays.sort(productsToSort, new Comparator<Product>() {
						@Override
						public int compare(Product o1, Product o2) {
							return o1.getCategory().compareTo(o2.getCategory());
						}
					});
				} else if (pickSort == 2) {
					Arrays.sort(productsToSort, new Comparator<Product>() {
						@Override
						public int compare(Product p1, Product p2) {
							return p1.getProductName().compareToIgnoreCase(p2.getProductName());
						}
					});
				} else if (pickSort == 3) {
					Arrays.sort(productsToSort, new Comparator<Product>() {
						@Override
						public int compare(Product p1, Product p2) {
							return p1.getQuantity() - p2.getQuantity();
						}
					});
				} else {
					System.out.println("Wrong input, please try again :)");
				}

				manager.printProducts(productsToSort);
				break;

			case EXIT:
				saveStockProducts(stock.getProducts(), "data.dat");
				System.out.println("Bye Bye, see you soon :)");
				break;
			default:
				System.out.println("Invalid input\n");
				break;
			}
		} while (choice != EXIT);
		sc.close();
	}

	private static void saveStockProducts(Product[] products, String filename) throws IOException {
		ObjectOutputStream file = new ObjectOutputStream(new FileOutputStream(filename));
		file.writeObject(products);
		file.close();
	}

	private static Product[] readStockProducts(String filename) throws IOException, ClassNotFoundException {
		ObjectInputStream file = new ObjectInputStream(new FileInputStream(filename));
		Product[] products = (Product[]) file.readObject();
		file.close();
		return products;
	}
}
