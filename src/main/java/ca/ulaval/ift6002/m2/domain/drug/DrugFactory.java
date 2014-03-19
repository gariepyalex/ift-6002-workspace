package ca.ulaval.ift6002.m2.domain.drug;

import java.util.List;

public interface DrugFactory {

    Drug create(Din din, String brandName);

    Drug create(Din din, String brandName, String descriptor);

    Drug create(Din din, String brandName, String descriptor, List<Din> interactingDins);

    Drug create(String brandName);
}
