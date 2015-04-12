package nl.arthurvlug.genderSearchRank;

import static com.google.common.collect.FluentIterable.from;

import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableList;

public class BuyLogs {


	public static List<BuyEvent> buyEvents(final List<ClassifiedUser> newUsers, final Catalog catalog) {
		ImmutableList<BuyEvent> events = from(
				from(newUsers).index((final ClassifiedUser u) -> u.getUser()).entries()
		).transformAndConcat((final Map.Entry<User, ClassifiedUser> entry) ->
			from(entry.getKey().getProductIds())
			.transform((final String pId) -> {
				final Product p = catalog.get(pId).get();
				return new BuyEvent(p, entry.getValue().getGenderProbabilities());
			})
			.toList()
		).toList();
		
		return events;
	}

	public static BuyLog trainingBuyLog(final Catalog catalog) {
		return new BuyLog(ImmutableList.<BuyEvent> builder()
				// Electronics
				.add(new BuyEvent(Gender.MALE, catalog.get("1").get()))
				.add(new BuyEvent(Gender.MALE, catalog.get("1").get()))
				.add(new BuyEvent(Gender.MALE, catalog.get("1").get()))
				
				// Generic (books)
				.add(new BuyEvent(Gender.MALE, catalog.get("2").get()))
				.add(new BuyEvent(Gender.FEMALE, catalog.get("2").get()))
				
				// Beauty
				.add(new BuyEvent(Gender.FEMALE, catalog.get("3").get()))
				
			// Electronics
			.add(new BuyEvent(Gender.MALE, catalog.get("1").get()))
			.add(new BuyEvent(Gender.MALE, catalog.get("1").get()))
			.add(new BuyEvent(Gender.MALE, catalog.get("1").get()))
			
			// Generic (books)
			.add(new BuyEvent(Gender.MALE, catalog.get("2").get()))
			.add(new BuyEvent(Gender.FEMALE, catalog.get("2").get()))
			
			// Beauty
			.add(new BuyEvent(Gender.FEMALE, catalog.get("3").get()))
			
			// Electronics
			.add(new BuyEvent(Gender.MALE, catalog.get("1").get()))
			.add(new BuyEvent(Gender.MALE, catalog.get("1").get()))
			.add(new BuyEvent(Gender.MALE, catalog.get("1").get()))
			
			// Generic (books)
			.add(new BuyEvent(Gender.MALE, catalog.get("2").get()))
			.add(new BuyEvent(Gender.FEMALE, catalog.get("2").get()))
			
			// Beauty
			.add(new BuyEvent(Gender.FEMALE, catalog.get("3").get()))
			
			// Electronics
			.add(new BuyEvent(Gender.MALE, catalog.get("1").get()))
			.add(new BuyEvent(Gender.MALE, catalog.get("1").get()))
			.add(new BuyEvent(Gender.MALE, catalog.get("1").get()))
			
			// Generic (books)
			.add(new BuyEvent(Gender.MALE, catalog.get("2").get()))
			.add(new BuyEvent(Gender.FEMALE, catalog.get("2").get()))
			
			// Beauty
			.add(new BuyEvent(Gender.FEMALE, catalog.get("3").get()))
			
			// Electronics
			.add(new BuyEvent(Gender.MALE, catalog.get("1").get()))
			.add(new BuyEvent(Gender.MALE, catalog.get("1").get()))
			.add(new BuyEvent(Gender.MALE, catalog.get("1").get()))
			
			// Generic (books)
			.add(new BuyEvent(Gender.MALE, catalog.get("2").get()))
			.add(new BuyEvent(Gender.FEMALE, catalog.get("2").get()))
			
			// Beauty
			.add(new BuyEvent(Gender.FEMALE, catalog.get("3").get()))
			
			// Electronics
			.add(new BuyEvent(Gender.MALE, catalog.get("1").get()))
			.add(new BuyEvent(Gender.MALE, catalog.get("1").get()))
			.add(new BuyEvent(Gender.MALE, catalog.get("1").get()))
			
			// Generic (books)
			.add(new BuyEvent(Gender.MALE, catalog.get("2").get()))
			.add(new BuyEvent(Gender.FEMALE, catalog.get("2").get()))
			
			// Beauty
			.add(new BuyEvent(Gender.FEMALE, catalog.get("3").get()))
			
			// Electronics
			.add(new BuyEvent(Gender.MALE, catalog.get("1").get()))
			.add(new BuyEvent(Gender.MALE, catalog.get("1").get()))
			.add(new BuyEvent(Gender.MALE, catalog.get("1").get()))
			
			// Generic (books)
			.add(new BuyEvent(Gender.MALE, catalog.get("2").get()))
			.add(new BuyEvent(Gender.FEMALE, catalog.get("2").get()))
			
			// Beauty
			.add(new BuyEvent(Gender.FEMALE, catalog.get("3").get()))
			
			// Electronics
			.add(new BuyEvent(Gender.MALE, catalog.get("1").get()))
			.add(new BuyEvent(Gender.MALE, catalog.get("1").get()))
			.add(new BuyEvent(Gender.MALE, catalog.get("1").get()))
			
			// Generic (books)
			.add(new BuyEvent(Gender.MALE, catalog.get("2").get()))
			.add(new BuyEvent(Gender.FEMALE, catalog.get("2").get()))
			
			// Beauty
			.add(new BuyEvent(Gender.FEMALE, catalog.get("3").get()))
			
			// Electronics
			.add(new BuyEvent(Gender.MALE, catalog.get("1").get()))
			.add(new BuyEvent(Gender.MALE, catalog.get("1").get()))
			.add(new BuyEvent(Gender.MALE, catalog.get("1").get()))
			
			// Generic (books)
			.add(new BuyEvent(Gender.MALE, catalog.get("2").get()))
			.add(new BuyEvent(Gender.FEMALE, catalog.get("2").get()))
			
			// Beauty
			.add(new BuyEvent(Gender.FEMALE, catalog.get("3").get()))
			
			// Electronics
			.add(new BuyEvent(Gender.MALE, catalog.get("1").get()))
			.add(new BuyEvent(Gender.MALE, catalog.get("1").get()))
			.add(new BuyEvent(Gender.MALE, catalog.get("1").get()))
			
			// Generic (books)
			.add(new BuyEvent(Gender.MALE, catalog.get("2").get()))
			.add(new BuyEvent(Gender.FEMALE, catalog.get("2").get()))
			
			// Beauty
			.add(new BuyEvent(Gender.FEMALE, catalog.get("3").get()))
			
			.build()
		);
	}
}
