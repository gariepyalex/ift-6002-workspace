package ca.ulaval.ift6002.m2.domain.instrument;

public enum InstrumentStatus {
    USED_PATIENT, SOILED, UNUSED;

    public static boolean isValid(String status) {
        boolean valid = true;

        try {
            InstrumentStatus.valueOf(status);
        } catch (Exception e) {
            System.out.println(status);
            valid = false;
        }

        return valid;
    }
}
