package ca.ulaval.ift6002.m2.domain.drug;

import java.util.Objects;

import org.apache.commons.lang3.builder.EqualsBuilder;

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
        if (obj == null) {
            return false;
        }
        if (obj instanceof Drug) {
            Drug other = (Drug) obj;
            return new EqualsBuilder().append(din, other.din).append(brandName, other.brandName)
                    .append(descriptor, other.descriptor).isEquals();
        } else {
            return false;
        }
    }

}
