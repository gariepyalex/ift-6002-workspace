package ca.ulaval.ift6002.m2.locator;

import java.util.HashMap;
import java.util.Map;

import ca.ulaval.ift6002.m2.domain.drug.DrugFactory;
import ca.ulaval.ift6002.m2.domain.instrument.InstrumentFactory;
import ca.ulaval.ift6002.m2.domain.operation.OperationFactory;
import ca.ulaval.ift6002.m2.domain.patient.PatientFactory;
import ca.ulaval.ift6002.m2.domain.prescription.ConsumptionFactory;
import ca.ulaval.ift6002.m2.domain.prescription.PrescriptionFactory;

public class FactoryLocator {

    private static FactoryLocator instance;

    public static void load(FactoryLocator factoryLocator) {
        instance = factoryLocator;
    }

    private final Map<Class<?>, Object> factories = new HashMap<>();

    public void register(Class<?> interfaceClass, Object implementation) {
        factories.put(interfaceClass, implementation);
    }

    public static DrugFactory getDrugFactory() {
        return (DrugFactory) instance.factories.get(DrugFactory.class);
    }

    public static ConsumptionFactory getConsumptionFactory() {
        return (ConsumptionFactory) instance.factories.get(ConsumptionFactory.class);
    }

    public static InstrumentFactory getInstrumentFactory() {
        return (InstrumentFactory) instance.factories.get(InstrumentFactory.class);
    }

    public static OperationFactory getOperationFactory() {
        return (OperationFactory) instance.factories.get(OperationFactory.class);
    }

    public static PatientFactory getPatientFactory() {
        return (PatientFactory) instance.factories.get(PatientFactory.class);
    }

    public static PrescriptionFactory getPrescriptionFactory() {
        return (PrescriptionFactory) instance.factories.get(PrescriptionFactory.class);
    }

}
