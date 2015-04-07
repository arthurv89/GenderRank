package nl.arthurvlug.genderSearchRank;

import java.util.ArrayList;
import java.util.List;

import com.atlassian.fugue.Iterables;
import com.atlassian.fugue.Option;

public class Catalog {
	private final List<Product> list = new ArrayList<>();
	
	private Catalog() {}
	
	public static Catalog simpleCatalog() {
		final Catalog catalog = new Catalog();
		catalog.list.add(new Product("1", "TV", Category.ELECTRONICS));
		catalog.list.add(new Product("2", "Boekje", Category.BOOKS));
		catalog.list.add(new Product("3", "Make-up", Category.BEAUTY));
		return catalog;
	}

	public Option<Product> get(final String id) {
		return Iterables.findFirst(list, (final Product p) -> p.getId().equals(id));
	}
}
