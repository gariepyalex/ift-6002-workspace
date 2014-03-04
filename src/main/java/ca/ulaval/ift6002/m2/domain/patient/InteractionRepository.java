package ca.ulaval.ift6002.m2.domain.patient;

import java.util.List;

public interface InteractionRepository {

    void store(List<Interaction> interactions);

}
