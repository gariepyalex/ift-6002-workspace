package projectH.infrastructure.persistence.inmemory.repository;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.regex.Pattern;

import projectH.domain.drug.Din;
import projectH.domain.drug.Drug;
import projectH.domain.drug.DrugRepository;
import projectH.infrastructure.persistence.inmemory.ListingRepository;
import au.com.bytecode.opencsv.CSVReader;

public class DrugInMemoryRepository extends ListingRepository<Drug> implements DrugRepository {

    private static final String DATA_FILE = "/drug.txt";
    private static final int MIN_LENGTH_OF_SEARCH_KEYWORDS = 3;

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

        Collection<Drug> drugs = new ArrayList<Drug>();
        // Pattern style: word.*otherword
        Pattern pattern = Pattern.compile(keyword.replace(" ", ".*"), Pattern.CASE_INSENSITIVE);

        for (Drug drug : getCollection()) {
            if (drug.matchBrandNameOrDescription(pattern)) {
                drugs.add(drug);
            }
        }

        if (drugs.isEmpty()) {
            throw new NoSuchElementException("There is no drug found with keyword: " + keyword);
        }

        return drugs;
    }

    @Override
    protected Object[] getKeys(Drug element) {
        Object[] keys = { element.getDin() };

        return keys;
    }

    @Override
    protected Collection<Drug> loadData() {
        Collection<Drug> drugs = new ArrayList<>();
        List<String[]> allLines = readAllLinesFromDataFile();

        for (String[] line : allLines) {
            Drug drugBuilt = hydrateDrugFromLine(line);

            drugs.add(drugBuilt);
        }

        return drugs;
    }

    private Drug hydrateDrugFromLine(String[] line) {
        String din = line[4];
        String brandName = line[5];
        String descriptor = line[6];

        return new Drug(new Din(din), brandName, descriptor);
    }

    private List<String[]> readAllLinesFromDataFile() {
        CSVReader reader = openDataFile();

        try {
            return reader.readAll();
        } catch (IOException e) {
            return Collections.emptyList();
        }
    }

    private CSVReader openDataFile() {
        InputStream drugResource = this.getClass().getResourceAsStream(DATA_FILE);

        return new CSVReader(new InputStreamReader(drugResource));
    }

}
