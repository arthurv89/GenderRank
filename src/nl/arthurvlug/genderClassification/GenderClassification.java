package nl.arthurvlug.genderClassification;

import java.util.List;
import java.util.Map;

import nl.arthurvlug.genderClassification.domain.BuyEvent;
import nl.arthurvlug.genderClassification.domain.BuyEvents;
import nl.arthurvlug.genderClassification.domain.BuyLog;
import nl.arthurvlug.genderClassification.domain.Catalog;
import nl.arthurvlug.genderClassification.domain.Category;
import nl.arthurvlug.genderClassification.domain.Gender;
import nl.arthurvlug.genderClassification.domain.User;
import nl.arthurvlug.genderClassification.model.CategorySupport;
import nl.arthurvlug.genderClassification.model.ClassifiedUser;
import nl.arthurvlug.genderClassification.model.GenderProbabilities;
import nl.arthurvlug.genderClassification.model.Model;

import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

public class GenderClassification {
	private static final Double EPSILON = 0.001;

	public List<ClassifiedUser> classifyBuylog(final BuyLog trainingBuyLog, final List<User> unclassifiedUsers, final Catalog catalog) {
		final ImmutableList<BuyEvent> originalList = trainingBuyLog.getBuyEvents();
		
		BuyLog buyLog = trainingBuyLog;
		while(true) {
			final Model model = determineModel(buyLog);
			System.out.println("Model:\n" + model);
			
			final List<ClassifiedUser> classifiedUsers = FluentIterable.from(unclassifiedUsers)
				.transform(u -> classify(catalog, model, u.getProductIds()))
				.toList();
			
			final ImmutableList<BuyEvent> newBuyEvents = FluentIterable.from(classifiedUsers)
				.transformAndConcat(classifiedUser -> BuyEvents.buyEvent(classifiedUser, catalog))
				.toList();
	
			final BuyLog newBuyLog = new BuyLog(originalList, newBuyEvents);
			if(isSame(newBuyLog, buyLog)) {
				return classifiedUsers;
			}
			
			buyLog = newBuyLog;
		}
	}

	private boolean isSame(final BuyLog newBuyLog, final BuyLog buyLog) {
		final ImmutableList<BuyEvent> newBuyEvents = newBuyLog.getBuyEvents();
		final ImmutableList<BuyEvent> oldBuyEvents = buyLog.getBuyEvents();
		if(newBuyEvents.size() != oldBuyEvents.size()) {
			return false;
		}
		
		for (int i = 0; i < newBuyEvents.size(); i++) {
			final List<Double> newGenderProbabilities = newBuyEvents.get(i).getGenderProbabilities().values().asList();
			final List<Double> oldGenderProbabilities = oldBuyEvents.get(i).getGenderProbabilities().values().asList();
			for(int j = 0; j < newGenderProbabilities.size(); j++) {
				final int newProb = (int) (newGenderProbabilities.get(j) / EPSILON);
				final int oldProb = (int) (oldGenderProbabilities.get(j) / EPSILON);
				if(newProb != oldProb) {
					return false;
				}
			}
		}
		return true;
	}

	private ClassifiedUser classify(final Catalog catalog, final Model model, final List<String> list) {
		final User user = new User(list);
		final GenderProbabilities genderProbabilities = model.determineGender(user, catalog);
		return new ClassifiedUser(user, genderProbabilities);
	}

	private Model determineModel(final BuyLog buyLog) {
		final Map<Gender, CategorySupport> mapByGender = ImmutableMap.<Gender, CategorySupport> builder()
			.put(Gender.MALE, categorySupport(buyLog, Gender.MALE))
			.put(Gender.FEMALE, categorySupport(buyLog, Gender.FEMALE))
			.put(Gender.UNKNOWN, categorySupport(buyLog, Gender.UNKNOWN))
			.build();
		
		return new Model(mapByGender);
	}

	private CategorySupport categorySupport(final BuyLog buyLog, final Gender gender) {
		final CategorySupport categorySupport = new CategorySupport();
		buyLog.forEach(buyEvent ->
			buyEvent.getGenderProbabilities().entrySet().forEach(probForGender -> {
				if(gender.isSame(probForGender.getKey())) {
					final Category category = buyEvent.getProduct().getCategory();
					final Double categoryCount = categorySupport.getOrDefault(category, 0.0);
					categorySupport.put(category, categoryCount + probForGender.getValue());
				}
			})
		);
		return categorySupport;
	}
}
