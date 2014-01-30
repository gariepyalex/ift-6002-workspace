package projectH;

import java.util.NoSuchElementException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/drugs/")
@Produces("application/json")
public class DrugResource {

	private final static Drug DRUG_1 = new Drug("Din123", "Drug1", "Brand", "");
	private final static Drug DRUG_2 = new Drug("DinABC", "Drug2", "Another brand", "Some description");

	private final static DrugDTO DRUG_DTO_1 = new DrugDTO(DRUG_1);
	private final static DrugDTO DRUG_DTO_2 = new DrugDTO(DRUG_2);

	@GET
	public DrugDTO[] getDrugs() {
		DrugDTO[] drugs = { DRUG_DTO_1, DRUG_DTO_2 };

		return drugs;
	}

	@GET
	@Path("{drugId}")
	public DrugDTO getDrug(@PathParam("drugId") int drugId) {
		DrugDTO drugFound = findDrugById(drugId);

		return drugFound;
	}

	private DrugDTO findDrugById(int drugId) {
		// We will clearly never do this this way
		// In fact, we would use the DrugRepository!
		if (drugId == 1) {
			return DRUG_DTO_1;
		} else if (drugId == 2) {
			return DRUG_DTO_2;
		}

		throw new NoSuchElementException("Drug id should be 1 or 2");
	}
}
