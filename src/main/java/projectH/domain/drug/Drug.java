package projectH.domain.drug;

import java.util.Objects;

public class Drug {

	private final String din;
	private final String brandName;
	private final String descriptor;

	public Drug(String din, String brandName, String descriptor) {
		this.din = din;
		this.brandName = brandName;
		this.descriptor = descriptor;
	}

	public String getDin() {
		return din;
	}

	public String getBrandName() {
		return brandName;
	}

	public String getDescriptor() {
		return descriptor;
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

		if (getClass() != obj.getClass()) {
			return false;
		}

		Drug other = (Drug) obj;

		return Objects.equals(din, other.din) && Objects.equals(brandName, other.brandName)
				&& Objects.equals(descriptor, other.descriptor);
	}

}
