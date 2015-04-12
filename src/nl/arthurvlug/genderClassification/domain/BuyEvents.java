package nl.arthurvlug.genderClassification.domain;

import static com.google.common.collect.FluentIterable.from;

import java.util.List;

import nl.arthurvlug.genderClassification.model.ClassifiedUser;

public class BuyEvents {
	public static List<BuyEvent> buyEvent(final ClassifiedUser classifiedUser, final Catalog catalog) {
		return from(classifiedUser.getUser().getProductIds())
			.transform(productId -> {
				final Product product = catalog.get(productId).get();
				return new BuyEvent(product, classifiedUser.getGenderProbabilities());
			})
			.toList();
	}
}
