package ca.ulaval.ift6002.m2.domain.prescription;

public class Pharmacy {
    private final String description;

    public Pharmacy(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
