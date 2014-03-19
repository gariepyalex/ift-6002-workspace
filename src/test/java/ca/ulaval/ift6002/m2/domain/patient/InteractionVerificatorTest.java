package ca.ulaval.ift6002.m2.domain.patient;

import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;

import ca.ulaval.ift6002.m2.domain.drug.Din;
import ca.ulaval.ift6002.m2.domain.drug.Drug;
import ca.ulaval.ift6002.m2.domain.prescription.Practitioner;
import ca.ulaval.ift6002.m2.domain.prescription.Prescription;

//TODO Refactor this test
public class InteractionVerificatorTest {

    private static final Drug DRUG_1 = mock(Drug.class);
    private static final Drug DRUG_2 = mock(Drug.class);
    private static final Drug DRUG_3 = mock(Drug.class);
    private static final Drug[] DRUGS = { DRUG_1, DRUG_2, DRUG_3 };

    private static final Drug NO_INTERACTION_DRUG = mock(Drug.class);
    private static final Drug WITH_INTERACTION_DRUG = mock(Drug.class);
    private static final Prescription NO_INTERACTION_PRESCRIPTION = createPrescription(NO_INTERACTION_DRUG);
    private static final Prescription WITH_INTERACTION_PRESCRIPTION = createPrescription(WITH_INTERACTION_DRUG);

    private static final List<Din> INTERACTING_DINS = fillInteractingDinList(DRUG_1, DRUG_2);
    private static final Interaction INTERACTION = new Interaction(WITH_INTERACTION_DRUG.getDin(), INTERACTING_DINS);
    private static final Interaction EMPTY_INTERACTION = new Interaction(NO_INTERACTION_DRUG.getDin(),
            new ArrayList<Din>());

    private static final Collection<Prescription> ALREADY_ASIGNED_PRESCRIPTION = fillPrescriptionsCollection();

    @Mock
    private InteractionRepository repo;

    private InteractionVerificator verificator;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() {
        InteractionRepository repo = mock(InteractionRepository.class);
        willReturn(INTERACTION).given(repo).get(WITH_INTERACTION_DRUG.getDin());
        willReturn(EMPTY_INTERACTION).given(repo).get(NO_INTERACTION_DRUG.getDin());

        verificator = new InteractionVerificator(repo);
    }

    @Test
    public void givenPrescriptionsWithInteractionWhenVerifyPrescriptionShouldThrowInteractionException() {
        exception.expect(InteractionException.class);
        // TODO Refactor this
        // exception.expectMessage("New prescription(din: " +
        // WITH_INTERACTION_DRUG.getDin()
        // + ") interacts with already asigned dins: " + DRUG_1.getDin() + ", "
        // + DRUG_2.getDin());

        verificator.verifyInteractionsWithNewPrescription(WITH_INTERACTION_PRESCRIPTION, ALREADY_ASIGNED_PRESCRIPTION);
    }

    @Test
    public void givenPrescriptionsWhitoutInteractionwhenVerifyPresciptionShouldDoNothing() {
        verificator.verifyInteractionsWithNewPrescription(NO_INTERACTION_PRESCRIPTION, ALREADY_ASIGNED_PRESCRIPTION);
    }

    private static List<Din> fillInteractingDinList(Drug... drugs) {
        List<Din> dinList = new ArrayList<Din>();

        for (Drug drug : drugs) {
            dinList.add(drug.getDin());
        }

        return dinList;
    }

    private static Collection<Prescription> fillPrescriptionsCollection() {
        Collection<Prescription> alreadyAsignedPrescription = new ArrayList<Prescription>();
        for (Drug drug : DRUGS) {
            alreadyAsignedPrescription.add(createPrescription(drug));
        }
        return alreadyAsignedPrescription;
    }

    // private static Drug createDrug(String din) {
    // return new Drug(new Din(din), "CouldBeAnyName",
    // "Description doesn't matter, we only use dins");
    // }

    private static Prescription createPrescription(Drug drug) {
        Practitioner practitioner = new Practitioner("Mister X");
        Date date = new Date();
        int renewal = 1;
        return new Prescription(practitioner, date, renewal, drug);
    }
}
