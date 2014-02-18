package ca.ulaval.ift6002.m2.infrastructure.persistence.filler;

import java.util.List;

public interface RepositoryFiller<E> {

    List<E> fill();
}
