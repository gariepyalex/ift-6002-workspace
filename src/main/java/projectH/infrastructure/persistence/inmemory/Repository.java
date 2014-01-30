package projectH.infrastructure.persistence.inmemory;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public abstract class Repository<E> {

	private Map<Integer, E> data = null;

	final protected Map<Integer, E> getData() {
		if (data == null) {
			mergeLoadedElementsToData();
		}

		return data;
	}

	private void mergeLoadedElementsToData() {
		Collection<E> loadedElements = loadData();
		data = new HashMap<>(loadedElements.size());

		for (E element : loadedElements) {
			int elementId = hashKeys(element);

			data.put(elementId, element);
		}
	}

	protected abstract Collection<E> loadData();

	protected abstract int hashKeys(E element);
}
