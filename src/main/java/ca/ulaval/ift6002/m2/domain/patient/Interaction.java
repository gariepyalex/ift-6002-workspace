package ca.ulaval.ift6002.m2.domain.patient;

import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import ca.ulaval.ift6002.m2.domain.drug.Din;

public class Interaction {

    private final Din dinFromWhichInteractionsAreChecked;
    private final List<Din> interactingDins;

    public Interaction(Din din, List<Din> interactingDins) {
        this.dinFromWhichInteractionsAreChecked = din;
        this.interactingDins = interactingDins;
    }

    @Override
    public String toString() {
        return "Din: " + dinFromWhichInteractionsAreChecked;
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);

    }

    public boolean isInteractingWith(Din dinToCheck) {
        return interactingDins.contains(dinToCheck);
    }

    public Din getDinFromWhichInteractionsAreChecked() {
        return dinFromWhichInteractionsAreChecked;
    }

    public List<Din> getInteractingDins() {
        return interactingDins;
    }

}
