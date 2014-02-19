package ca.ulaval.ift6002.m2.infrastructure.persistence.inmemory;

import java.util.Collection;
import java.util.NoSuchElementException;

import ca.ulaval.ift6002.m2.infrastructure.persistence.filler.RepositoryFiller;

public abstract class InMemoryListingRepository<E> extends InMemoryRepository<E> {

    public InMemoryListingRepository(RepositoryFiller<E> filler) {
        super(filler);
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
