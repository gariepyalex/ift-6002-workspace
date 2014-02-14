package ca.ulaval.ift6002.m2.infrastructure.persistence.inmemory;

import java.util.Collection;

public interface DataAdapter<E> {
    Collection<E> getDataList();
}
