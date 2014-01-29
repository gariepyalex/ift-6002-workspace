package projectH;

import java.util.Objects;

public class Drug {

	private final String name;
	private final String brand;
	private final String descriptor;

	public Drug(String name, String brand, String descriptor) {
		this.name = name;
		this.brand = brand;
		this.descriptor = descriptor;
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
		return Objects.hash(name, brand, descriptor);
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

		return Objects.equals(name, other.name) && Objects.equals(brand, other.brand)
				&& Objects.equals(descriptor, other.descriptor);
	}

}
