package ca.ulaval.ift6002.m2.domain.drug;

public interface DrugFactory {

    Drug create(Din din, String brandName, String descriptor);

    Drug create(String brandName);
}
