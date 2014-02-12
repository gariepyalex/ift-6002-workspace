package ca.ulaval.ift6002.m2.infrastructure.persistence.inmemory.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.regex.Pattern;

import ca.ulaval.ift6002.m2.domain.drug.Din;
import ca.ulaval.ift6002.m2.domain.drug.Drug;
import ca.ulaval.ift6002.m2.domain.drug.DrugDataAdapter;
import ca.ulaval.ift6002.m2.domain.drug.DrugRepository;
import ca.ulaval.ift6002.m2.infrastructure.persistence.inmemory.ListingRepository;

public class DrugInMemoryRepository extends ListingRepository<Drug> implements DrugRepository {

	private static final int MIN_LENGTH_OF_SEARCH_KEYWORDS = 3;

	private DrugDataAdapter adapter;

	public DrugInMemoryRepository(DrugDataAdapter adapter) {
		this.adapter = adapter;
		convertLoadedElementsToData();
	}

	@Override
	protected Collection<Drug> loadData() {
		Collection<Drug> drugs;
		drugs = adapter.getDrugsListFromFile();

		return drugs;
	}

	@Override
	public Drug get(Din din) {
		for (Drug drug : getCollection()) {
			if (drug.hasSameDin(din)) {
				return drug;
			}
		}

		throw new NoSuchElementException("There are no drug with din:" + din);
	}

	@Override
	public Collection<Drug> findByBrandNameOrDescriptor(String keyword) {
		if (keyword.length() < MIN_LENGTH_OF_SEARCH_KEYWORDS) {
			throw new IllegalArgumentException("The minimum character's length is: " + MIN_LENGTH_OF_SEARCH_KEYWORDS);
		}

		Collection<Drug> drugs = getMatchingDrug(keyword);

		if (drugs.isEmpty()) {
			throw new NoSuchElementException("There is no drug found with keyword: " + keyword);
		}

		return drugs;
	}

	private Collection<Drug> getMatchingDrug(String keyword) {
		// Pattern style: word.*otherword
		Pattern pattern = Pattern.compile(keyword.replace(" ", ".*"), Pattern.CASE_INSENSITIVE);

		Collection<Drug> drugs = new ArrayList<Drug>();
		for (Drug drug : getCollection()) {
			if (drug.matchBrandNameOrDescription(pattern)) {
				drugs.add(drug);
			}
		}
		return drugs;
	}

	@Override
	protected Object[] getKeys(Drug element) {
		Object[] keys = { element.getDin() };

		return keys;
	}

}
