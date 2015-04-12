package nl.arthurvlug.genderClassification;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

import nl.arthurvlug.genderClassification.domain.BuyLog;
import nl.arthurvlug.genderClassification.domain.BuyLogs;
import nl.arthurvlug.genderClassification.domain.Catalog;
import nl.arthurvlug.genderClassification.domain.Catalogs;
import nl.arthurvlug.genderClassification.domain.User;
import nl.arthurvlug.genderClassification.model.ClassifiedUser;

import com.google.common.collect.ImmutableList;

public class Main {
	private static final NumberFormat nf = new DecimalFormat("#");
	
	public static void main(final String[] args) {
		final Catalog catalog = Catalogs.simpleCatalog();
		final BuyLog trainingBuyLog = BuyLogs.trainingBuyLog(catalog);
		
		final List<User> unclassifiedUsers = ImmutableList.<User> builder()
				.add(new User())
				.add(new User("1"))
				.add(new User("2"))
				.add(new User("3"))
				.add(new User("1", "2"))
				.add(new User("2", "3"))
				.add(new User("1", "3"))
				.add(new User("1", "2", "3"))
				.add(new User("1", "2", "3", "1", "2", "3"))
				.add(new User("1", "3", "3", "3", "3", "3", "3"))
				.build();
		
		final GenderClassification genderClassificatin = new GenderClassification();
		final List<ClassifiedUser> classification = genderClassificatin.classifyBuylog(trainingBuyLog, unclassifiedUsers, catalog);
		
		classification.forEach(classifiedUser -> {
			System.out.println();
			System.out.println(classifiedUser.getUser().getProductIds());
			classifiedUser.getGenderProbabilities().entrySet().forEach(entry -> 
				System.out.println(entry.getKey() + "\t  " + nf.format(entry.getValue() * 100) + "%")
			);
		});
	}
}
