package ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import ca.ulaval.ift6002.m2.domain.date.DateFormatter;
import ca.ulaval.ift6002.m2.domain.drug.Drug;
import ca.ulaval.ift6002.m2.domain.prescription.Consumption;
import ca.ulaval.ift6002.m2.domain.prescription.Practitioner;
import ca.ulaval.ift6002.m2.domain.prescription.Prescription;

@Entity
@Table(name = "tbl_prescription")
public class PrescriptionHibernate extends Prescription {

    private int id;
    private String practitionerName;
    private String date;
    private int renewals;
    @ManyToOne
    private DrugHibernate drug;
    private List<Consumption> consumptions;
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
        // TODO create a list of consumption with the consuptionHibernate List
        return consumptions;
    }

    @Override
    public int getNumber() {
        return id;
    }

    @Override
    protected void addConsumptionInPrescription(Consumption consumption) {
        // TODO Cast object to ConsumptionHibernate and add it to the list

    }

    @Override
    protected Consumption lastConsumption() {
        // TODO Cast object to consumptionHibernate
        return consumptions.get(consumptions.size() - 1);
    }

    @Override
    protected boolean isConsumptionsEmpty() {
        return consumptions.isEmpty();
    }
}
