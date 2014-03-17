package ca.ulaval.ift6002.m2.domain.patient;

import java.util.List;

import ca.ulaval.ift6002.m2.domain.drug.Din;

public interface InteractionRepository {

    void store(List<Interaction> interactions);

    Interaction get(Din dinFromWhichInteractionAreChecked);

}
