package ca.ulaval.ift6002.m2.domain.patient;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import ca.ulaval.ift6002.m2.domain.drug.Din;
import ca.ulaval.ift6002.m2.domain.prescription.Prescription;

public class InteractionVerificator {

    private final InteractionRepository repository;

    public InteractionVerificator(InteractionRepository interactionRepository) {
        repository = interactionRepository;
    }

    public void verifyInteractionsWithNewPrescription(Prescription newPrescription,
            Collection<Prescription> alreadyAsignedPrescriptions) {

        Din newDin = prescriptionToDin(newPrescription);
        List<Din> oldDins = prescriptionToDin(alreadyAsignedPrescriptions);
        Interaction interaction = repository.get(newDin);

        List<Din> dinsInConflict = getDinsInConflict(oldDins, interaction);

        if (!dinsInConflict.isEmpty()) {
            throwInteractionException(newDin, dinsInConflict);
        }
    }

    private List<Din> getDinsInConflict(List<Din> alreadyAsignedDins, Interaction interaction) {
        List<Din> dinsInConflict = new ArrayList<Din>();

        for (Din din : alreadyAsignedDins) {
            if (interaction.isInteractingWith(din)) {
                dinsInConflict.add(din);
            }
        }

        return dinsInConflict;
    }

    private void throwInteractionException(Din newDin, List<Din> dinsInConflict) throws InteractionException {
        String message = createExceptionMessage(newDin, dinsInConflict);
        throw new InteractionException(message);
    }

    private String createExceptionMessage(Din newDin, List<Din> dinsInConflict) {
        String message = "New prescription(din: " + newDin + ") interacts with already asigned dins: ";

        for (Din din : dinsInConflict) {
            message += din + ", ";
        }
        message.substring(0, message.length() - 2);

        return message;
    }

    private Din prescriptionToDin(Prescription prescription) {
        return prescription.getDrug().getDin();
    }

    private List<Din> prescriptionToDin(Collection<Prescription> prescriptions) {
        ArrayList<Din> dins = new ArrayList<Din>();

        for (Prescription prescription : prescriptions) {
            dins.add(prescriptionToDin(prescription));
        }

        return dins;
    }
}
