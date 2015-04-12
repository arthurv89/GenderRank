package nl.arthurvlug.genderClassification.domain;

import java.util.Iterator;
import java.util.List;

import com.google.common.collect.ImmutableList;

public class BuyLog implements Iterable<BuyEvent> {
	private final ImmutableList<BuyEvent> buyEvents;

	BuyLog(final ImmutableList<BuyEvent> buyEvents) {
		this.buyEvents = buyEvents;
	}
	
	public BuyLog(final List<BuyEvent> originalBuyEvents, final List<BuyEvent> newBuyEvents) {
		this.buyEvents = ImmutableList.<BuyEvent> builder()
				.addAll(originalBuyEvents)
				.addAll(newBuyEvents)
				.build();
	}

	@Override
	public Iterator<BuyEvent> iterator() {
		return buyEvents.iterator();
	}

	public ImmutableList<BuyEvent> getBuyEvents() {
		return buyEvents;
	}
}
