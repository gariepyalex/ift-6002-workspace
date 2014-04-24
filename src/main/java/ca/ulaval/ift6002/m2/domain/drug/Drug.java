package ca.ulaval.ift6002.m2.domain.drug;

import java.util.Collection;

public abstract class Drug {

    public boolean hasDin() {
        return !getDin().isEmpty();
    }

    public boolean isInteractingWith(Drug otherDrug) {
        for (Drug interactingDrug : getInteractingDrugs()) {
            if (interactingDrug.hasSameDin(otherDrug)) {
                return true;
            }
        }
        return false;
    }

    private boolean hasSameDin(Drug otherDrug) {
        return getDin().equals(otherDrug.getDin());
    }

    public abstract void interactWith(Collection<Drug> drugs);

    protected abstract Collection<Drug> getInteractingDrugs();

    public abstract Din getDin();

    public abstract String getBrandName();

    public abstract String getDescriptor();

}
