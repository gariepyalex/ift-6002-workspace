package ca.ulaval.ift6002.m2.acceptance.contexts;

import ca.ulaval.ift6002.m2.domain.surgery.Surgery;

public class SurgeryContext {

    private static Surgery surgeryInstance;

    public static void setSurgery(Surgery surgery) {
        surgeryInstance = surgery;
    }

    public static Surgery getSurgery() {
        return surgeryInstance;
    }

    public static Integer getSurgeryNumber() {
        return surgeryInstance.getNumber();
    }

    public static void reset() {
        surgeryInstance = null;
    }
}
