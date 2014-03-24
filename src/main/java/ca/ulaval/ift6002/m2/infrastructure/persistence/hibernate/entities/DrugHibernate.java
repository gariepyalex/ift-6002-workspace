package ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate.entities;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import ca.ulaval.ift6002.m2.domain.drug.Din;
import ca.ulaval.ift6002.m2.domain.drug.Drug;

@Entity
@Table(name = "tbl_drug")
public class DrugHibernate extends Drug {

    @Id
    @GeneratedValue
    private Integer id;
    private String din;
    private String brandName;
    private String descriptor;

    // TODO check if this annotation is ok
    @ManyToMany
    private Collection<DrugHibernate> interactingDrugs;

    public DrugHibernate(Din din, String brandName) {
        this(din, brandName, "");
    }

    public DrugHibernate(Din din, String brandName, String descriptor) {
        this.din = din.getValue();
        this.brandName = brandName;
        this.descriptor = descriptor;
        this.interactingDrugs = new ArrayList<>();
    }

    protected DrugHibernate() {
        // For hibernate
    }

    @Override
    public void interactWith(Collection<Drug> drugs) {
        this.interactingDrugs = convertToHibernate(drugs);
    }

    private Collection<DrugHibernate> convertToHibernate(Collection<Drug> drugs) {
        Collection<DrugHibernate> drugsHibernate = new ArrayList<>();

        for (Drug drug : drugs) {
            DrugHibernate drugHibernate = (DrugHibernate) drug;

            drugsHibernate.add(drugHibernate);
        }

        return drugsHibernate;
    }

    @Override
    protected Collection<Drug> getInteractingDrugs() {
        return new ArrayList<Drug>(interactingDrugs);
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
