package nl.arthurvlug.genderSearchRank;

public class Product {
	private final String id;
	private final String name;
	private final Category category;

	public Product(final String id, final String name, final Category category) {
		this.id = id;
		this.name = name;
		this.category = category;
	}
	
	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Category getCategory() {
		return category;
	}
}