package projectH.domain.drug;

import java.util.Objects;

public class Din {

	private final String value;

	public Din(String value) {
		this.value = value;
	}

	@Override
	public int hashCode() {
		return Objects.hash(value);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof Din)) {
			return false;
		}

		Din other = (Din) obj;

		return Objects.equals(value, other.value);
	}

	@Override
	public String toString() {
		return String.valueOf(value);
	}
}
