package ca.ulaval.ift6002.m2.domain.drug;

public abstract class Drug {

    public abstract Din getDin();

    public abstract String getBrandName();

    public abstract String getDescriptor();

    public boolean hasDin() {
        return !getDin().isEmpty();
    }

}
