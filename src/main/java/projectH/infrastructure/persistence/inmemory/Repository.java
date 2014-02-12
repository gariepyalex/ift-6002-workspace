package projectH.infrastructure.persistence.inmemory;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public abstract class Repository<E> {

    private final Map<Integer, E> data = new HashMap<>();

    public Repository() {
        convertLoadedElementsToData();
    }

    private void convertLoadedElementsToData() {
        Collection<E> loadedElements = loadData();

        for (E element : loadedElements) {
            Object[] keys = getKeys(element);
            int elementId = hashKeys(keys);

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

    final protected int hashKeys(Object[] keys) {
        return Objects.hash(keys);
    }

    protected abstract Object[] getKeys(E element);

    protected abstract Collection<E> loadData();

}
