package nl.arthurvlug.genderClassification.domain;

import com.google.common.base.Predicate;

public enum Gender {
	MALE(g -> g == Gender.valueOf("MALE")),
	UNKNOWN(g -> true),
	FEMALE(g -> g == Gender.valueOf("FEMALE"));
	
	private Predicate<Gender> pred;

	private Gender(final Predicate<Gender> pred) {
		this.pred = pred;
	}

	public boolean isSame(final Gender gender) {
		return pred.apply(gender);
	}
}
