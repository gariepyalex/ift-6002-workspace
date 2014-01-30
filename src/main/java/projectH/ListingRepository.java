package projectH;

import java.util.Collection;

public abstract class ListingRepository<E> extends Repository<E> {

	final protected Collection<E> getCollection() {
		return getData().values();
	}

	final protected E get(int hashedId) {
		return getData().get(hashedId);
	}
}
