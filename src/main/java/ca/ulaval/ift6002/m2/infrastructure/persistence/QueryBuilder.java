package ca.ulaval.ift6002.m2.infrastructure.persistence;

import java.util.List;

public abstract class QueryBuilder<E> {

    public abstract QueryBuilder<E> query(String query);

    public abstract QueryBuilder<E> parameter(String name, Object value);

    public abstract int perform();

    public abstract E get();

    public abstract List<E> list();

    public List<E> list(int limit) {
        return list(0, limit);
    }

    public List<E> list(int startIndex, int limit) {
        int toIndex = startIndex + limit;
        return list().subList(startIndex, toIndex);
    }
}
