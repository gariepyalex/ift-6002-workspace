package projectH.infrastructure.persistence.inmemory.repository;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Objects;

import projectH.domain.drug.Drug;
import projectH.domain.drug.DrugRepository;
import projectH.infrastructure.persistence.inmemory.ListingRepository;
import au.com.bytecode.opencsv.CSVReader;

public class DrugInMemoryRepository extends ListingRepository<Drug> implements DrugRepository {

	private static final String DRUG_FILE = "/drug.txt";
	private static final int MIN_LENGTH_OF_FIND_BY_NAME = 3;

	@Override
	public Drug findByName(String drugName) {
		loadData();
		if (drugName.length() < MIN_LENGTH_OF_FIND_BY_NAME) {
			throw new IllegalArgumentException("The minimum character's length is: " + MIN_LENGTH_OF_FIND_BY_NAME);
		}

		if (drugName.equals("UNEXISTING_DRUG")) {
			throw new NoSuchElementException("There is no drug found named: " + drugName);
		}

		return new Drug("din", drugName, "");
	}

	@Override
	protected Collection<Drug> loadData() {

		Collection<Drug> drugs = new ArrayList<>();
		try {
			InputStream drugResource = this.getClass().getResourceAsStream(DRUG_FILE);
			CSVReader reader = new CSVReader(new InputStreamReader(drugResource));
			String[] nextLine;

			while ((nextLine = reader.readNext()) != null) {
				String din = nextLine[4];
				String brandName = nextLine[5];
				String descriptor = nextLine[6];

				drugs.add(new Drug(din, brandName, descriptor));
			}
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return drugs;
	}

	@Override
	protected int hashKeys(Drug element) {
		return Objects.hash(element.getDin());
	}
}
