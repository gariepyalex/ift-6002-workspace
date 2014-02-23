package ca.ulaval.ift6002.m2.infrastructure.persistence.dto;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DrugDTO {

    @Id
    public final String din;

    public final String brandName;

    public final String descriptor;

    public DrugDTO(String din, String brandName, String descriptor) {
        this.din = din;
        this.brandName = brandName;
        this.descriptor = descriptor;
    }
}
