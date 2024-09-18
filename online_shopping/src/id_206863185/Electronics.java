package id_206863185;

import java.time.LocalDateTime;

public class Electronics extends Product {
	private String companyName;
	private String model;

	public Electronics(int id, String productName, int quantity, String companyName, String model, String category,
			LocalDateTime timeStamp) {
		super(id, productName, quantity, category, timeStamp);
		this.companyName = companyName;
		this.model = model;
	}

	@Override
	public String toString() {

		return super.toString() + ", company name: " + companyName + ", model: " + model;
	}
}
