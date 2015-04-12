package nl.arthurvlug.genderClassification;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import nl.arthurvlug.genderClassification.domain.BuyEvent;
import nl.arthurvlug.genderClassification.domain.BuyLog;
import nl.arthurvlug.genderClassification.domain.BuyLogs;
import nl.arthurvlug.genderClassification.domain.Catalog;
import nl.arthurvlug.genderClassification.domain.Catalogs;
import nl.arthurvlug.genderClassification.domain.Category;
import nl.arthurvlug.genderClassification.domain.Gender;
import nl.arthurvlug.genderClassification.domain.User;
import nl.arthurvlug.genderClassification.model.CategorySupport;
import nl.arthurvlug.genderClassification.model.ClassifiedUser;
import nl.arthurvlug.genderClassification.model.GenderProbabilities;
import nl.arthurvlug.genderClassification.model.Model;

import com.atlassian.fugue.Function2;
import com.google.common.collect.ImmutableList;

public class Sort {
	private final NumberFormat nf = new DecimalFormat("#");
	private final Function2<Gender, Double, Double> similarityTransformer;
	private final Catalog catalog;

	private Sort(final Function2<Gender, Double, Double> similarityTransformer) {
		this.similarityTransformer = similarityTransformer;
		catalog = Catalogs.simpleCatalog();
		
		final BuyLog trainingBuyLog = BuyLogs.trainingBuyLog(catalog);
		final ImmutableList<BuyEvent> originalList = trainingBuyLog.getList();
		System.out.println(originalList);
		
		BuyLog buyLog = trainingBuyLog;
		while(true) {
			final Model model = determineModel(buyLog);
			System.out.println(model);

			final List<ClassifiedUser> newUsers = ImmutableList.<ClassifiedUser> builder()
					.add(classify(catalog, model))
					.add(classify(catalog, model, "1"))
					.add(classify(catalog, model, "2"))
					.add(classify(catalog, model, "3"))
					.add(classify(catalog, model, "1", "2"))
					.add(classify(catalog, model, "2", "3"))
					.add(classify(catalog, model, "1", "3"))
					.add(classify(catalog, model, "1", "2", "3"))
					.add(classify(catalog, model, "1", "2", "3", "1", "2", "3"))
					.add(classify(catalog, model, "1", "3", "3", "3", "3", "3", "3"))
					.build();

			final List<BuyEvent> newBuyLog = BuyLogs.buyEvents(newUsers, catalog);
	
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println(newBuyLog);
			
			buyLog = new BuyLog(originalList, newBuyLog);
		}
	}

	private ClassifiedUser classify(final Catalog catalog, final Model model, final String... productIds) {
		final User user = new User(productIds);
		
		System.out.println();
		System.out.println(Arrays.toString(productIds));
		final GenderProbabilities genderProbabilities = model.determineGender(user, catalog);
		for(final Entry<Gender, Double> entry : genderProbabilities.entrySet()) {
			System.out.println(entry.getKey() + "\t  " + nf.format(entry.getValue() * 100) + "%");
		}
		
		return new ClassifiedUser(user, genderProbabilities);
	}

	private Model determineModel(final BuyLog buyLog) {
		final Map<Gender, CategorySupport> mapByGender = new HashMap<>();
		mapByGender.put(Gender.MALE, categorySupport(buyLog, Gender.MALE));
		mapByGender.put(Gender.FEMALE, categorySupport(buyLog, Gender.FEMALE));
		mapByGender.put(Gender.UNKNOWN, categorySupport(buyLog, Gender.UNKNOWN));
		
		return new Model(mapByGender, similarityTransformer);
	}

	private CategorySupport categorySupport(final BuyLog buyLog, final Gender gender) {
		final CategorySupport categorySupport = emptyCategoryMap();
		for(final BuyEvent buyEvent : buyLog.getList()) {
			for(final Entry<Gender, Double> probForGender : buyEvent.getGenderProbabilities().entrySet()) {
				if(gender.isSame(probForGender.getKey())) {
					final Category category = buyEvent.getProduct().getCategory();
					final Double categoryCount = categorySupport.getOrDefault(category, 0.0);
					final double newCount = categoryCount + probForGender.getValue();
					categorySupport.put(category, newCount);
				}
			}
		}
		return categorySupport;
	}

	private static CategorySupport emptyCategoryMap() {
		final CategorySupport map = new CategorySupport();
		for(final Category category : Category.values()) {
			map.putIfAbsent(category, 0.0);
		}
		return map;
	}
	
	public static void main(final String[] args) {
		new Sort((final Gender g, final Double sim) -> sim*sim);
	}
}
