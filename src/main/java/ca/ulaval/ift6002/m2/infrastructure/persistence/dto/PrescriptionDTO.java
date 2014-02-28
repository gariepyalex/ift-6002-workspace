package ca.ulaval.ift6002.m2.infrastructure.persistence.dto;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PrescriptionDTO {

    // TODO have a real id also...
    @Id
    public String id;

    public String practitioner;
    public String date;
    public Integer renewals;
    public DrugDTO drugDTO;

    public PrescriptionDTO(String practitioner, String date, Integer renewals, DrugDTO drugDTO) {
        this.practitioner = practitioner;
        this.date = date;
        this.renewals = renewals;
        this.drugDTO = drugDTO;
    }
}
