package nl.arthurvlug.genderSearchRank;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;

public class BuyEvent {
	private GenderProbabilities genderProbabilities;
	private Product product;

	public BuyEvent(Product product, GenderProbabilities genderProbabilities) {
		Preconditions.checkArgument(product != null);
		Preconditions.checkArgument(product != null);

		this.genderProbabilities = genderProbabilities;
		this.product = product;
	}

	public BuyEvent(Gender gender, Product product) {
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
