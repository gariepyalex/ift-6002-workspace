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
import javax.persistence.Table;
import javax.persistence.Transient;

import ca.ulaval.ift6002.m2.domain.date.DateFormatter;
import ca.ulaval.ift6002.m2.domain.drug.Drug;
import ca.ulaval.ift6002.m2.domain.prescription.Consumption;
import ca.ulaval.ift6002.m2.domain.prescription.Practitioner;
import ca.ulaval.ift6002.m2.domain.prescription.Prescription;

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
    private List<ConsumptionHibernate> consumptions;
    @Transient
    private DateFormatter dateFormatter;

    public PrescriptionHibernate(Practitioner practitioner, Date date, int renewals, Drug drug,
            DateFormatter dateFormatter) {
        this.dateFormatter = dateFormatter;

        this.practitionerName = practitioner.toString();
        this.date = dateFormatter.dateToString(date);
        this.renewals = renewals;
        this.drug = (DrugHibernate) drug;
        this.consumptions = new ArrayList<>();
    }

    public PrescriptionHibernate(Practitioner practitioner, Date date, int renewals, Drug drug) {
        this(practitioner, date, renewals, drug, new DateFormatter());
    }

    protected PrescriptionHibernate() {
        // For hibernate
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
        return drug;
    }

    @Override
    public List<Consumption> getConsumptions() {
        return new ArrayList<Consumption>(consumptions);
    }

    @Override
    public int getNumber() {
        return id;
    }

    @Override
    protected void addConsumptionInPrescription(Consumption consumption) {
        ConsumptionHibernate consumptionHibernate = (ConsumptionHibernate) consumption;
        consumptions.add(consumptionHibernate);
    }

    @Override
    protected Consumption lastConsumption() {
        return consumptions.get(consumptions.size() - 1);
    }

    @Override
    protected boolean isConsumptionsEmpty() {
        return consumptions.isEmpty();
    }
}
