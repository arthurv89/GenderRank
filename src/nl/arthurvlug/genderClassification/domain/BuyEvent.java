package nl.arthurvlug.genderClassification.domain;

import nl.arthurvlug.genderClassification.model.GenderProbabilities;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;

public class BuyEvent {
	private final GenderProbabilities genderProbabilities;
	private final Product product;

	public BuyEvent(final Product product, final GenderProbabilities genderProbabilities) {
		Preconditions.checkArgument(product != null);
		Preconditions.checkArgument(product != null);

		this.genderProbabilities = genderProbabilities;
		this.product = product;
	}

	public BuyEvent(final Product product, final Gender gender) {
		this(product, new GenderProbabilities(ImmutableMap.<Gender, Double> builder()
			.put(gender, 1.0)
			.build()
		));
	}

	public GenderProbabilities getGenderProbabilities() {
		return genderProbabilities;
	}

	public Product getProduct() {
		return product;
	}
	
	@Override
	public String toString() {
		return genderProbabilities.toString() + "=" + product.getId();
	}
}
