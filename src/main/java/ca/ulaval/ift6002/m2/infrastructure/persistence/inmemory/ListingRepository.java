package ca.ulaval.ift6002.m2.infrastructure.persistence.inmemory;

import java.util.Collection;
import java.util.NoSuchElementException;

public abstract class ListingRepository<E> extends Repository<E> {

    public ListingRepository() {
        super();
    }

    public ListingRepository(DataAdapter<E> adapter) {
        super(adapter);
    }

    protected final Collection<E> getCollection() {
        return getData().values();
    }

    protected final E get(int hashedId) {
        E elementRetrieved = getData().get(hashedId);

        if (elementRetrieved != null) {
            return elementRetrieved;
        }

        throw new NoSuchElementException("There is no element found with this id");
    }
}
