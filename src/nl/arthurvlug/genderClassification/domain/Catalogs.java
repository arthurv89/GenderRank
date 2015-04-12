package nl.arthurvlug.genderClassification.domain;

public class Catalogs {
	public static Catalog simpleCatalog() {
		final Catalog catalog = new Catalog();
		catalog.list.add(new Product("1", "TV", Category.ELECTRONICS));
		catalog.list.add(new Product("2", "Boekje", Category.BOOKS));
		catalog.list.add(new Product("3", "Make-up", Category.BEAUTY));
		return catalog;
	}
}
