package projectH.domain.drug;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;

import projectH.domain.drug.Drug;

public class DrugTest {

	private static final String DRUG_DIN = "1234567890";
	private static final String DRUG_NAME = "A drug name";
	private static final String DRUG_DESCRIPTOR = "A descriptor";
	private static final String DRUG_BRAND_NAME = "A brand name";

	private static final Drug EMPTY_DRUG = new Drug("", "", "", "");

	private static final Integer RANDOM_INTEGER = new Integer(5);

	private Drug drug;

	@Before
	public void setup() {
		drug = new Drug(DRUG_DIN, DRUG_NAME, DRUG_BRAND_NAME, DRUG_DESCRIPTOR);
	}

	@Test
	public void canGetDin() {
		assertEquals(DRUG_DIN, drug.getDin());
	}

	@Test
	public void canGetName() {
		assertEquals(DRUG_NAME, drug.getName());
	}

	@Test
	public void canGetBrand() {
		assertEquals(DRUG_BRAND_NAME, drug.getBrand());
	}

	@Test
	public void canGetDescriptor() {
		assertEquals(DRUG_DESCRIPTOR, drug.getDescriptor());
	}

	@Test
	public void shouldHaveSameHashCodeWhenSameObject() {
		assertEquals(drug.hashCode(), drug.hashCode());
	}

	@Test
	public void shouldNotHaveSameHashCodeWhenDifferent() {
		assertNotEquals(drug.hashCode(), EMPTY_DRUG.hashCode());
	}

	@Test
	public void shouldBeEqualsWhenSameObject() {
		assertEquals(drug, drug);
	}

	@Test
	public void shouldNotBeEqualsWhenNotSameClass() {
		assertNotEquals(drug, RANDOM_INTEGER);
	}

	@Test
	public void shouldBeEqualsWhenHaveSameInformation() {
		Drug otherDrug = new Drug(DRUG_DIN, DRUG_NAME, DRUG_BRAND_NAME, DRUG_DESCRIPTOR);

		assertEquals(drug, otherDrug);
	}

	@Test
	public void shouldNotBeEqualsWhenHaveDifferentInformation() {
		assertNotEquals(drug, EMPTY_DRUG);
	}

}
