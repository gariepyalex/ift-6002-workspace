package ca.ulaval.ift6002.m2.infrastructure.persistence.inmemory;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public abstract class Repository<E> {

    private final Map<Integer, E> data = new HashMap<>();

    protected DataAdapter<E> adapter;

    public Repository() {

    }

    public Repository(DataAdapter<E> adapter) {
        this.adapter = adapter;
        convertLoadedElementsToData();
    }

    protected void convertLoadedElementsToData() {
        Collection<E> loadedElements = loadData();

        for (E element : loadedElements) {
            Object[] keys = getKeys(element);
            int elementId = hashKeys(keys);

            data.put(elementId, element);
        }
    }

    public final boolean isEmpty() {
        return getData().isEmpty();
    }

    public final int size() {
        return getData().size();
    }

    protected final Map<Integer, E> getData() {
        return data;
    }

    protected final int hashKeys(Object[] keys) {
        return Objects.hash(keys);
    }

    protected abstract Object[] getKeys(E element);

    protected abstract Collection<E> loadData();

}
