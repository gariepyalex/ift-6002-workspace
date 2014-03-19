package ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import ca.ulaval.ift6002.m2.domain.drug.Din;
import ca.ulaval.ift6002.m2.domain.drug.Drug;

@Entity
public class DrugHibernate extends Drug {

    @Id
    @GeneratedValue
    private Integer id;
    private String din;
    private String brandName;
    private String descriptor;

    public DrugHibernate(Din din, String brandName) {
        this.din = din.getValue();
        this.brandName = brandName;
        this.descriptor = "";
    }

    public DrugHibernate(Din din, String brandName, String descriptor) {
        this.din = din.getValue();
        this.brandName = brandName;
        this.descriptor = descriptor;
    }

    protected DrugHibernate() {
        // For hibernate
    }

    @Override
    public Din getDin() {
        return new Din(din);
    }

    @Override
    public String getBrandName() {
        return brandName;
    }

    @Override
    public String getDescriptor() {
        return descriptor;
    }

}
