package nl.arthurvlug.genderClassification.model;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import nl.arthurvlug.genderClassification.domain.Gender;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;

public class GenderProbabilities {
	private final ImmutableMap<Gender, Double> genderProbabilityMap;

	public GenderProbabilities(final Map<Gender, Double> genderProbabilityMap) {
		this.genderProbabilityMap = ImmutableMap.copyOf(genderProbabilityMap);
	}
	
	public ImmutableList<Entry<Gender, Double>> entrySet() {
		List<Entry<Gender, Double>> entrySet = Lists.newArrayList(genderProbabilityMap.entrySet());
		Collections.sort(entrySet, (Entry<Gender, Double> o1, Entry<Gender, Double> o2) ->
			o1.getKey().compareTo(o2.getKey()));
		return ImmutableList.copyOf(entrySet);
	}
	
	@Override
	public String toString() {
		return genderProbabilityMap.toString();
	}
}
