package projectH;

import static org.junit.Assert.*;

import java.util.GregorianCalendar;

import org.junit.Test;

public class PrescriptionTest {

	@Test
	public void canGetPractionerNumber() {
		GregorianCalendar date = new GregorianCalendar(1970, 1, 1, 12, 0);
		Prescription aPrescription = new Prescription(123123, date, 0, "ADVIL");
		assertEquals(123123, aPrescription.getPractioner());
	}
	
}