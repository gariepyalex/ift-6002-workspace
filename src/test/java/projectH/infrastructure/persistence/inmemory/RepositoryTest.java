package projectH.infrastructure.persistence.inmemory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;

import org.junit.Before;
import org.junit.Test;

public class RepositoryTest {

	private final static String AN_ELEMENT = "1234567890";
	private final static int AN_ELEMENT_HASHED_KEY = Objects.hash(AN_ELEMENT);

	// Minimal implementation of the abstract class
	final private class RepositoryImpl extends Repository<String> {

		@Override
		protected Collection<String> loadData() {
			Collection<String> data = new ArrayList<>();
			data.add(AN_ELEMENT);
			return data;
		}

		@Override
		protected Object[] getKeys(String element) {
			Object[] keys = { element };
			return keys;
		}

		// Since getData() is protected, it is a work-around to expose it
		public Map<Integer, String> exposeGetData() {
			return super.getData();
		}

	}

	private RepositoryImpl repository;

	@Before
	public void setup() {
		repository = new RepositoryImpl();
	}

	@Test
	public void givenRepositoryWhenCreatedShouldBeLoadedAndHaveSizeOfOne() {
		assertEquals(1, repository.size());
	}

	@Test
	public void givenRepositoryWhenGettingDataShouldNotBeEmpty() {
		repository.exposeGetData();

		assertFalse(repository.isEmpty());
	}

	@Test
	public void givenRepositoryWhenGettingDataMapShouldContainsElement() {
		Map<Integer, String> data = repository.exposeGetData();
		boolean result = data.containsValue(AN_ELEMENT);
		assertTrue(result);
	}

	@Test
	public void givenRepositoryWhenGettingDataShouldContainsElementKeysHashed() {
		Map<Integer, String> data = repository.exposeGetData();
		boolean result = data.containsKey(AN_ELEMENT_HASHED_KEY);
		assertTrue(result);
	}
}
