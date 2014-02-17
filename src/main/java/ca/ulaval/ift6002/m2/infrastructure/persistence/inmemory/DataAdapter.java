package ca.ulaval.ift6002.m2.infrastructure.persistence.inmemory;

import java.util.List;

public interface DataAdapter<E> {

    List<E> retrieveData();
}
