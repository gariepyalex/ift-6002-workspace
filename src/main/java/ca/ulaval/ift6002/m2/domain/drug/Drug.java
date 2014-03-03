package ca.ulaval.ift6002.m2.domain.drug;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

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
        return new HashCodeBuilder().append(din).append(brandName).append(descriptor).toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }
        Drug other = (Drug) obj;
        return new EqualsBuilder().append(din, other.din).append(brandName, other.brandName)
                .append(descriptor, other.descriptor).isEquals();
    }

}
