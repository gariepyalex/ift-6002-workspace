package ca.ulaval.ift6002.m2.infrastructure.persistence.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class PrescriptionDTO {

    @Id
    @GeneratedValue
    public Integer id;
    public String practitioner;
    public String date;
    public Integer renewals;
    public String other;

    @ManyToOne
    @JoinColumn(name = "drug_id")
    public DrugDTO drugDTO;

    public PrescriptionDTO(String practitioner, String date, Integer renewals, String other, DrugDTO drugDTO) {
        this.practitioner = practitioner;
        this.date = date;
        this.renewals = renewals;
        this.drugDTO = drugDTO;
        this.other = other;
    }

    protected PrescriptionDTO() {
        this.practitioner = "";
        this.date = "";
        this.renewals = 0;
        this.drugDTO = null;
        this.other = "";
    }
}
