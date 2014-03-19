package ca.ulaval.ift6002.m2.infrastructure.persistence.dto;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class InteractionDTO {

    @Id
    public String dinFromWhichInteractionsAreChecked;
    public List<String> interactingDins;

    public InteractionDTO(String dinsFromWhichInteractionsAreChecked, List<String> interactingDins) {
        this.dinFromWhichInteractionsAreChecked = dinsFromWhichInteractionsAreChecked;
        this.interactingDins = interactingDins;
    }
}
