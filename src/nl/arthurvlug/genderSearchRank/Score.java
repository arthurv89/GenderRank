package nl.arthurvlug.genderSearchRank;

public class Score {
	private final int maleScore;
	private final int femaleScore;
	
	public Score(final int maleScore, final int femaleScore) {
		this.maleScore = maleScore;
		this.femaleScore = femaleScore;
	}

	public int getMaleScore() {
		return maleScore;
	}

	public int getFemaleScore() {
		return femaleScore;
	}
}
