package ca.ulaval.ift6002.m2.infrastructure.persistence.inmemory;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import ca.ulaval.ift6002.m2.infrastructure.persistence.filler.RepositoryFiller;

public abstract class InMemoryRepository<E> {

    private final Map<Integer, E> data = new HashMap<>();
    private final RepositoryFiller<E> filler;

    public InMemoryRepository(RepositoryFiller<E> filler) {
        this.filler = filler;
        convertFilledElementsToData();
    }

    private void convertFilledElementsToData() {
        Collection<E> filledElements = filler.fill();

        for (E element : filledElements) {
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

}
