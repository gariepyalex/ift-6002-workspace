package ca.ulaval.ift6002.m2.infrastructure.persistence.assemblers;

import java.util.ArrayList;
import java.util.Collection;

public abstract class DTOAssembler<E, T> {

    public abstract E fromDTO(T dto);

    public abstract T toDTO(E element);

    public Collection<E> fromDTOs(Collection<T> dtos) {
        Collection<E> elements = new ArrayList<>(dtos.size());

        for (T dto : dtos) {
            E element = fromDTO(dto);

            elements.add(element);
        }

        return elements;
    }

    public Collection<T> toDTOs(Collection<E> elements) {
        Collection<T> dtos = new ArrayList<>(elements.size());

        for (E element : elements) {
            T dto = toDTO(element);

            dtos.add(dto);
        }

        return dtos;
    }
}
