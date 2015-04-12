package nl.arthurvlug.genderClassification.domain;

import com.google.common.collect.ImmutableList;

public class BuyLogs {
	public static BuyLog trainingBuyLog(final Catalog catalog) {
		return new BuyLog(ImmutableList.<BuyEvent> builder()
			// Electronics
			.add(new BuyEvent(catalog.get("1").get(), Gender.MALE))
			.add(new BuyEvent(catalog.get("1").get(), Gender.MALE))
			.add(new BuyEvent(catalog.get("1").get(), Gender.MALE))
			
			// Generic (books)
			.add(new BuyEvent(catalog.get("2").get(), Gender.MALE))
			.add(new BuyEvent(catalog.get("2").get(), Gender.FEMALE))
			
			// Beauty
			.add(new BuyEvent(catalog.get("3").get(), Gender.FEMALE))
				
			// Electronics
			.add(new BuyEvent(catalog.get("1").get(), Gender.MALE))
			.add(new BuyEvent(catalog.get("1").get(), Gender.MALE))
			.add(new BuyEvent(catalog.get("1").get(), Gender.MALE))
			
			// Generic (books)
			.add(new BuyEvent(catalog.get("2").get(), Gender.MALE))
			.add(new BuyEvent(catalog.get("2").get(), Gender.FEMALE))
			
			// Beauty
			.add(new BuyEvent(catalog.get("3").get(), Gender.FEMALE))
			
			.build()
		);
	}
}
