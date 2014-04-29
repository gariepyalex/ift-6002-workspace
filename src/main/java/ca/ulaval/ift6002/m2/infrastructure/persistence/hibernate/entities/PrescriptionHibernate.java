package ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import ca.ulaval.ift6002.m2.domain.date.DateFormatter;
import ca.ulaval.ift6002.m2.domain.drug.Drug;
import ca.ulaval.ift6002.m2.domain.drug.DrugFactory;
import ca.ulaval.ift6002.m2.domain.prescription.Consumption;
import ca.ulaval.ift6002.m2.domain.prescription.Practitioner;
import ca.ulaval.ift6002.m2.domain.prescription.Prescription;
import ca.ulaval.ift6002.m2.locator.FactoryLocator;

@Entity
@Table(name = "tbl_prescription")
public class PrescriptionHibernate extends Prescription {

    @Id
    @GeneratedValue
    private int id;
    private String practitionerName;
    private String date;
    private int renewals;
    @ManyToOne
    private DrugHibernate drug;
    @OneToMany(cascade = { CascadeType.ALL })
    @OrderBy("date DESC")
    private List<ConsumptionHibernate> consumptions;
    private String drugName;

    @Transient
    private DateFormatter dateFormatter;
    @Transient
    private DrugFactory drugFactory;

    public PrescriptionHibernate(Practitioner practitioner, Date date, int renewals, Drug drug,
            DateFormatter dateFormatter) {
        this.dateFormatter = dateFormatter;
        this.practitionerName = practitioner.toString();
        this.date = dateFormatter.dateToString(date);
        this.renewals = renewals;
        this.consumptions = new ArrayList<>();
        this.drugFactory = FactoryLocator.getDrugFactory();

        if (drug.hasDin()) {
            this.drug = (DrugHibernate) drug;
            this.drugName = "";
        } else {
            this.drugName = drug.getBrandName();
        }
    }

    public PrescriptionHibernate(Practitioner practitioner, Date date, int renewals, Drug drug) {
        this(practitioner, date, renewals, drug, new DateFormatter());
    }

    protected PrescriptionHibernate() {
        this.dateFormatter = new DateFormatter();
        this.drugFactory = FactoryLocator.getDrugFactory();
    }

    public String getDrugName() {
        return drugName;
    }

    @Override
    public int getNumber() {
        return id;
    }

    @Override
    public Practitioner getPractioner() {
        return new Practitioner(practitionerName);
    }

    @Override
    public Date getDate() {
        return dateFormatter.parse(date);
    }

    @Override
    public int getRenewals() {
        return renewals;
    }

    @Override
    public Drug getDrug() {
        if (drugName.isEmpty()) {
            return drug;
        } else {
            return drugFactory.create(drugName);
        }
    }

    @Override
    public List<Consumption> getConsumptions() {
        return new ArrayList<Consumption>(consumptions);
    }

    @Override
    protected void addConsumptionInPrescription(Consumption consumption) {
        ConsumptionHibernate consumptionHibernate = (ConsumptionHibernate) consumption;
        consumptions.add(consumptionHibernate);
    }

    @Override
    protected Consumption getLastConsumption() {
        return consumptions.get(consumptions.size() - 1);
    }

    @Override
    protected boolean hasConsumptions() {
        return !consumptions.isEmpty();
    }

}
