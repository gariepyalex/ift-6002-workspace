package ca.ulaval.ift6002.m2.acceptance.contexts;

public class DateOrderContext {

    private static String[] expectedPrescriptionDateOrder;
    private static String[][] expectedConsumptionDateOrder;

    public static void setExpectedPrescriptionDateOrder(String... dateOrder) {
        expectedPrescriptionDateOrder = dateOrder;
    }

    public static void setExpectedConsumptionDateOrder(String[]... dateOrder) {
        expectedConsumptionDateOrder = dateOrder;
    }

    public static String[] getExpectedPrescriptionDateOrder() {
        return expectedPrescriptionDateOrder;
    }

    public static String[][] getExpectedConsumptionDateOrder() {
        return expectedConsumptionDateOrder;
    }
}
