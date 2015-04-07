package nl.arthurvlug.genderSearchRank;


public class ClassifiedUser {
	private User user;
	private GenderProbabilities genderProbabilities;

	public ClassifiedUser(User user, GenderProbabilities genderProbabilities) {
		this.user = user;
		this.genderProbabilities = genderProbabilities;
	}

	public User getUser() {
		return user;
	}

	public GenderProbabilities getGenderProbabilities() {
		return genderProbabilities;
	}
}
