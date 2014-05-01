package ca.ulaval.ift6002.m2.domain.surgery;

public interface SurgeryRepository {

    Surgery get(int number);

    void store(Surgery surgery);
}
