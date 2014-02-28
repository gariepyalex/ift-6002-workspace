package ca.ulaval.ift6002.m2.domain.drug;

import java.util.Objects;

public class Drug {

    private final Din din;
    private final String brandName;
    private final String descriptor;

    public Drug(Din din, String brandName, String descriptor) {
        this.din = din;
        this.brandName = brandName;
        this.descriptor = descriptor;
    }

    public Din getDin() {
        return din;
    }

    public String getBrandName() {
        return brandName;
    }

    public String getDescriptor() {
        return descriptor;
    }

    public static Drug fromName(String name) {
        return new Drug(new Din(""), name, "");
    }

    @Override
    public int hashCode() {
        return Objects.hash(din, brandName, descriptor);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Drug)) {
            return false;
        }

        Drug other = (Drug) obj;

        return Objects.equals(din, other.din) && Objects.equals(brandName, other.brandName)
                && Objects.equals(descriptor, other.descriptor);
    }

}
