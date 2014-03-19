package ca.ulaval.ift6002.m2.infrastructure.persistence.assemblers;

import java.util.ArrayList;
import java.util.List;

import ca.ulaval.ift6002.m2.domain.drug.Din;
import ca.ulaval.ift6002.m2.domain.patient.Interaction;
import ca.ulaval.ift6002.m2.infrastructure.persistence.dto.InteractionDTO;

public class InteractionDTOAssembler extends DTOAssembler<Interaction, InteractionDTO> {

    @Override
    public Interaction fromDTO(InteractionDTO dto) {
        Din din = stringToDin(dto.dinFromWhichInteractionsAreChecked);
        List<Din> interactingDins = stringToDin(dto.interactingDins);
        return new Interaction(din, interactingDins);
    }

    @Override
    public InteractionDTO toDTO(Interaction element) {
        String din = dinToString(element.getDinFromWhichInteractionsAreChecked());
        List<String> interactingDins = dinToString(element.getInteractingDins());
        return new InteractionDTO(din, interactingDins);
    }

    private Din stringToDin(String dinString) {
        return new Din(dinString);
    }

    private List<Din> stringToDin(List<String> dinsString) {
        ArrayList<Din> dins = new ArrayList<Din>();

        for (String dinString : dinsString) {
            dins.add(new Din(dinString));
        }

        return dins;
    }

    private String dinToString(Din din) {
        return din.getValue();
    }

    private List<String> dinToString(List<Din> dins) {
        ArrayList<String> dinsString = new ArrayList<String>();
        for (Din din : dins) {
            dinsString.add(din.getValue());
        }
        return dinsString;
    }
}
