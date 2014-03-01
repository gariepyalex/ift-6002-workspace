package ca.ulaval.ift6002.m2.domain.instrument;

import java.util.Objects;

import org.apache.commons.lang3.builder.EqualsBuilder;

public class Typecode {

	private final String value;

	public Typecode(String value) {
		this.value = value;
	}

	@Override
	public int hashCode() {
		return Objects.hash(value);
	}

	@Override
	public boolean equals(Object obj) {

		if ((obj instanceof Typecode)) {
			Typecode other = (Typecode) obj;
			return new EqualsBuilder().append(value, other.value).isEquals();
		} else {
			return false;
		}
	}
}
