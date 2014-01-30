package projectH.domain.drug;

import java.util.Objects;

public class Drug {

	private final String din;
	private final String name;
	private final String brand;
	private final String descriptor;

	public Drug(String din, String name, String brand, String descriptor) {
		this.din = din;
		this.name = name;
		this.brand = brand;
		this.descriptor = descriptor;
	}

	public String getDin() {
		return din;
	}

	public String getName() {
		return name;
	}

	public String getBrand() {
		return brand;
	}

	public String getDescriptor() {
		return descriptor;
	}

	@Override
	public int hashCode() {
		return Objects.hash(din, name, brand, descriptor);
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

		return Objects.equals(din, other.din) && Objects.equals(name, other.name) && Objects.equals(brand, other.brand)
				&& Objects.equals(descriptor, other.descriptor);
	}

}
