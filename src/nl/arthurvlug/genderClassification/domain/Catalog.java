package nl.arthurvlug.genderClassification.domain;

import java.util.ArrayList;
import java.util.List;

import com.atlassian.fugue.Iterables;
import com.atlassian.fugue.Option;

public class Catalog {
	final List<Product> list = new ArrayList<>();
	
	public Option<Product> get(final String id) {
		return Iterables.findFirst(list, (final Product p) -> p.getId().equals(id));
	}
}
