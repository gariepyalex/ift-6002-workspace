package ca.ulaval.ift6002.m2.infrastructure.persistence.assemblers;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;

public class DTOAssemblerTest {

    private static final Collection<String> STRINGS = Arrays.asList("1", "2", "3");
    private static final Collection<Integer> INTEGERS = Arrays.asList(1, 2, 3);

    @Test
    public void givenCollectionOfStringShouldReturnCollectionOfInteger() {
        DTOAssembler<String, Integer> assembler = createDTOAssembler();
        Collection<Integer> result = assembler.toDTOs(STRINGS);
        assertEquals(INTEGERS, result);
    }

    @Test
    public void givenCollectionOfIntegersShouldReturnCollectionOfStrings() {
        DTOAssembler<String, Integer> assembler = createDTOAssembler();
        Collection<String> result = assembler.fromDTOs(INTEGERS);
        assertEquals(STRINGS, result);
    }

    private DTOAssembler<String, Integer> createDTOAssembler() {
        DTOAssembler<String, Integer> dtoAssembler;

        dtoAssembler = new DTOAssembler<String, Integer>() {

            @Override
            public String fromDTO(Integer dto) {
                return String.valueOf(dto);
            }

            @Override
            public Integer toDTO(String element) {
                return Integer.valueOf(element);
            }
        };

        return dtoAssembler;
    }
}
