package nl.arthurvlug.genderSearchRank;

import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

public class SortedMapByValue implements SortedMap<String, Integer> {
	private TreeMap<String, Integer> map = new TreeMap<String, Integer>(comparator());

	private static Comparator<? super String> comparator(final Map<String, Integer> map) {
		return new Comparator<String>() {
			@Override
			public int compare(final String id1, final String id2) {
				return map.get(id2).compareTo(map.get(id1));
			}
		};
	}

	@Override
	public int size() {
		return map.size();
	}

	@Override
	public boolean isEmpty() {
		return map.isEmpty();
	}

	@Override
	public boolean containsKey(Object key) {
		return map.containsKey(key);
	}

	@Override
	public boolean containsValue(Object value) {
		return map.containsValue(value);
	}

	@Override
	public Integer get(Object key) {
		return map.get(key);
	}

	@Override
	public Integer put(String key, Integer value) {
		return map.put(key, value);
	}

	@Override
	public Integer remove(Object key) {
		return map.remove(key);
	}

	@Override
	public void putAll(Map<? extends String, ? extends Integer> m) {
		map.putAll(m);
	}

	@Override
	public void clear() {
		map.clear();
	}

	@Override
	public Comparator<? super String> comparator() {
		return comparator(map);
	}

	@Override
	public SortedMap<String, Integer> headMap(String toKey) {
		return map.headMap(toKey);
	}

	@Override
	public SortedMap<String, Integer> tailMap(String fromKey) {
		return map.tailMap(fromKey);
	}

	@Override
	public String firstKey() {
		return map.firstKey();
	}

	@Override
	public String lastKey() {
		return map.lastKey();
	}

	@Override
	public Set<String> keySet() {
		return map.keySet();
	}

	@Override
	public Collection<Integer> values() {
		return map.values();
	}

	@Override
	public Set<java.util.Map.Entry<String, Integer>> entrySet() {
		return map.entrySet();
	}

	@Override
	public SortedMap<String, Integer> subMap(String fromKey, String toKey) {
		return map.subMap(fromKey, toKey);
	}
	
	@Override
	public String toString() {
		return map.toString();
	}
}
