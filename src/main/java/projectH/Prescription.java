package projectH;

import java.util.GregorianCalendar;

public class Prescription {
	private int practitioner;

	public Prescription(int i, GregorianCalendar date, int j, String string) {
		this.practitioner = i;
	}

	public int getPractioner() {
		return practitioner;
	}

}
