package projectH.infrastructure.persistence.inmemory;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public abstract class Repository<E> {

	private final Map<Integer, E> data = new HashMap<>();

	public Repository() {
		convertLoadedElementsToData();
	}

	private void convertLoadedElementsToData() {
		Collection<E> loadedElements = loadData();

		for (E element : loadedElements) {
			int elementId = hashKeys(element);

			data.put(elementId, element);
		}
	}

	final public boolean isEmpty() {
		return getData().isEmpty();
	}

	final public int size() {
		return getData().size();
	}

	final protected Map<Integer, E> getData() {
		return data;
	}

	protected abstract Collection<E> loadData();

	protected abstract int hashKeys(E element);
}
