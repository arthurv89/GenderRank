package nl.arthurvlug.genderClassification.domain;

import java.util.List;

import com.google.common.collect.ImmutableList;

public class BuyLog {
	private final ImmutableList<BuyEvent> totalList;

	BuyLog(final ImmutableList<BuyEvent> newList) {
		this.totalList = newList;
	}
	
	public BuyLog(final List<BuyEvent> originalList, final List<BuyEvent> newList) {
		this.totalList = ImmutableList.<BuyEvent> builder()
				.addAll(originalList)
				.addAll(newList)
				.build();
	}

	public ImmutableList<BuyEvent> getList() {
		return totalList;
	}
}
