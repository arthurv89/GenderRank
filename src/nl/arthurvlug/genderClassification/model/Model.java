package nl.arthurvlug.genderClassification.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import nl.arthurvlug.genderClassification.domain.Catalog;
import nl.arthurvlug.genderClassification.domain.Category;
import nl.arthurvlug.genderClassification.domain.Gender;
import nl.arthurvlug.genderClassification.domain.Product;
import nl.arthurvlug.genderClassification.domain.User;

import com.atlassian.fugue.Function2;
import com.google.common.collect.ImmutableMap;

public class Model {
	private final Map<Gender, CategorySupport> mapByGender;
	private final Function2<Gender, Double, Double> similarityTransformer;

	public Model(final Map<Gender, CategorySupport> mapByGender, final Function2<Gender, Double, Double> similarityTransformer) {
		this.mapByGender = mapByGender;
		this.similarityTransformer = similarityTransformer;
	}

	public GenderProbabilities determineGender(final User user, final Catalog catalog) {
		if(user.getProductIds().isEmpty()) {
			return new GenderProbabilities(ImmutableMap.<Gender, Double> builder()
					.put(Gender.MALE, 0.0)
					.put(Gender.FEMALE, 0.0)
					.put(Gender.UNKNOWN, 1.0)
					.build());
		}
		
		final CategorySupport userCatSupport = new CategorySupport();
		for(final String productIds : user.getProductIds()) {
			final Product product = catalog.get(productIds).get();
			final Double count = userCatSupport.getOrDefault(product.getCategory(), 0.0);
			userCatSupport.put(product.getCategory(), count+1);
		}

		Map<Gender, Double> similarityByGender = new HashMap<>();
		for(final Entry<Gender, CategorySupport> entry : mapByGender.entrySet()) {
			final Gender gender = entry.getKey();
			final CategorySupport modelCatSupport = entry.getValue();
			double genderSimilarity = cosineSimilarity(modelCatSupport, userCatSupport);
			genderSimilarity = similarityTransformer.apply(gender, genderSimilarity);
			similarityByGender.put(gender, genderSimilarity);
		}

		return probabilities(similarityByGender);
	}

	private GenderProbabilities probabilities(final Map<Gender, Double> similarityByGender) {
		double sum = 0;
		for(final Entry<Gender, Double> entry : similarityByGender.entrySet()) {
			sum += entry.getValue();
		}
		
		final Map<Gender, Double> probabilities = new HashMap<>();
		for(final Entry<Gender, Double> entry : similarityByGender.entrySet()) {
			probabilities.put(entry.getKey(), entry.getValue() / sum);
		}
		return new GenderProbabilities(probabilities);
	}
	
	private double cosineSimilarity(final CategorySupport modelCatSupport, final CategorySupport userCatSupport) {
		double dotProduct = 0;
		for(final Category cat : modelCatSupport.keySet()) {
			if(userCatSupport.containsKey(cat)) {
				dotProduct += userCatSupport.get(cat) * modelCatSupport.get(cat);
			}
		}
		final double length = length(modelCatSupport) * length(userCatSupport);
		final double distance = dotProduct / length;
		return distance;
	}

	private double length(final CategorySupport categorySupport) {
		double length = 0;
		for(final Double a : categorySupport.values()) {
			length += a*a;
		}
		return Math.sqrt(length);
	}

	@Override
	public String toString() {
		return mapByGender.toString();
	}
}
