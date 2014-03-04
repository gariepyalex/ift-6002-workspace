package ca.ulaval.ift6002.m2.infrastructure.persistence.dto;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DrugDTO {

    @Id
    public String din;

    public String brandName;

    public String descriptor;

    public DrugDTO(String din, String brandName, String descriptor) {
        this.din = din;
        this.brandName = brandName;
        this.descriptor = descriptor;
    }

    protected DrugDTO() {
        this.din = "";
        this.brandName = "";
        this.descriptor = "";
    }
}
