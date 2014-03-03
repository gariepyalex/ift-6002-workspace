//package ca.ulaval.ift6002.m2.domain.prescription;
//
//import static org.junit.Assert.*;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.Date;
//
//import org.junit.Test;
//
//import ca.ulaval.ift6002.m2.domain.drug.Din;
//import ca.ulaval.ift6002.m2.domain.drug.Drug;
//import ca.ulaval.ift6002.m2.domain.prescription.Prescription;
//
//public class InteractionVerificatorTest {
//
//    private static final Drug DRUG_1 = new Drug(new Din("111111"), "banana", "yellow fruit");
//    private static final Drug DRUG_2 = new Drug(new Din("222222"), "potato", "brown vegetable");
//    private static final Drug DRUG_3 = new Drug(new Din("333333"), "yaourt", "weird thing");
//    private static final Drug[] DRUGS = { DRUG_1, DRUG_2, DRUG_3 };
//
//    private static final Practitioner A_PRACTITIONER = new Practitioner("Bob");
//    private static final Date A_DATE = new Date();
//    private static final int A_NUMBER_OF_RENEWAL = 1;
//    private static final Drug DRUG_WITH_NO_INTERACTION = new Drug(new Din("444444"), "noInteractionDrug", "blablabla");
//    private static final Drug DRUG_WITH_INTERACTION = new Drug(new Din("555555"), "interactionDrug", "mouhahaha");
//
//    private static final Prescription NO_INTERACTION_PRESCRIPTION = new Prescription(A_PRACTITIONER, A_DATE,
//            A_NUMBER_OF_RENEWAL, DRUG_WITH_NO_INTERACTION);
//    private static final Prescription WITH_INTERACTION_PRESCRIPTION = new Prescription(A_PRACTITIONER, A_DATE,
//            A_NUMBER_OF_RENEWAL, DRUG_WITH_INTERACTION);
//
//    @Test
//    public void givenPrescriptionsWithInteractionWhenVerifyPrescriptionShouldReturnTrue() {
//
//        InteractionVerificator verificator = new InteractionVerificator();
//        Collection<Prescription> alreadyAsignedPrescription = fillPrescriptionsArray();
//
//        boolean thereIsInteractions = verificator.verifyInteractionsWithNewPrescription(NO_INTERACTION_PRESCRIPTION,
//                alreadyAsignedPrescription);
//
//        assertTrue(thereIsInteractions);
//    }
//
//    @Test
//    public void givenPrescriptionsWithNoInteractionWhenVerifyPrescriptionShouldReturnFalse() {
//        InteractionVerificator verificator = new InteractionVerificator();
//        Collection<Prescription> alreadyAsignedPrescription = fillPrescriptionsArray();
//
//        boolean thereIsInteractions = verificator.verifyInteractionsWithNewPrescription(NO_INTERACTION_PRESCRIPTION,
//                alreadyAsignedPrescription);
//
//        assertFalse(thereIsInteractions);
//    }
//
//    private Collection<Prescription> fillPrescriptionsArray() {
//        Collection<Prescription> alreadyAsignedPrescription = new ArrayList<Prescription>();
//        for (Drug drug : DRUGS) {
//            alreadyAsignedPrescription.add(new Prescription(A_PRACTITIONER, A_DATE, A_NUMBER_OF_RENEWAL, drug));
//        }
//        return alreadyAsignedPrescription;
//    }
// }
