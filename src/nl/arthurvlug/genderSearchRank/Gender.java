package nl.arthurvlug.genderSearchRank;

import com.google.common.base.Predicate;

public enum Gender {
	MALE((final Gender g) -> g == Gender.valueOf("MALE")),
	UNKNOWN((final Gender g) -> true),
	FEMALE((final Gender g) -> g == Gender.valueOf("FEMALE"));
	
	private Predicate<Gender> pred;

	private Gender(final Predicate<Gender> pred) {
		this.pred = pred;
	}

	public boolean isSame(final Gender gender) {
		return pred.apply(gender);
	}
}
