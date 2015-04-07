package nl.arthurvlug.genderSearchRank;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.google.common.base.Preconditions;

public class Dataset {
//	private final Map<Gender, Map<String, Integer>> order = new HashMap<Gender, Map<String, Integer>>();
//
//	public Dataset() {
//		putOrderPerGender(scores());
//	}
//
//	private void putOrderPerGender(final List<Score> scores) {
//		HashMap<String, Integer> maleScores = new HashMap<String, Integer>();
//		HashMap<String, Integer> femaleScores = new HashMap<String, Integer>();
//		for (int i = 0; i < scores.size(); i++) {
//			final String id = i + "";
//			final Score score = scores.get(i);
//			maleScores.put(id, score.getMaleScore());
//			femaleScores.put(id, score.getFemaleScore());
//		}
//		
//		Map<String, Integer> maleOrder = new TreeMap<String, Integer>(comparator(maleScores));
//		maleOrder.putAll(maleScores);
//		order.put(Gender.MALE, maleOrder);
//		
//		Map<String, Integer> femaleOrder = new TreeMap<String, Integer>(comparator(femaleScores));
//		femaleOrder.putAll(femaleScores);
//		order.put(Gender.FEMALE, femaleOrder);
//	}
//
//	private List<Score> scores() {
//		final List<Score> scores = new ArrayList<Score>();
//		scores.add(new Score(10, 20));
//		scores.add(new Score(40, 15));
//		scores.add(new Score(30, 11));
//		scores.add(new Score(11, 50));
//		return scores;
//	}
//
//	private static Comparator<String> comparator(final Map<String, Integer> map) {
//		return new Comparator<String>() {
//			@Override
//			public int compare(final String id1, final String id2) {
//				return map.get(id2).compareTo(map.get(id1));
//			}
//		};
//	}
//	
//	public Map<String, Integer> order(final Gender gender) {
//		Preconditions.checkArgument(gender != null);
//		return order.get(gender);
//	}
}
