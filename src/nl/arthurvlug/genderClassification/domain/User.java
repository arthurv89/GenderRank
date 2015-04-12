package nl.arthurvlug.genderClassification.domain;

import java.util.Arrays;
import java.util.List;

public class User {
	private final List<String> products;

	public User(final String... products) {
		this.products = Arrays.asList(products);
	}

	public List<String> getProductIds() {
		return products;
	}
}
