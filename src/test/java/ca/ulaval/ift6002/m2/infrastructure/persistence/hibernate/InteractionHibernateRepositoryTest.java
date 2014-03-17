package ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate;

import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.ift6002.m2.domain.drug.Din;
import ca.ulaval.ift6002.m2.infrastructure.persistence.assemblers.InteractionDTOAssembler;
import ca.ulaval.ift6002.m2.infrastructure.persistence.dto.InteractionDTO;
import ca.ulaval.ift6002.m2.infrastructure.persistence.provider.EntityManagerProvider;

@RunWith(MockitoJUnitRunner.class)
public class InteractionHibernateRepositoryTest {

    private static final Din A_DIN = new Din("00000000");

    private static final String[] INTERACTING_STRINGS = { "11111111", "22222222" };

    private static final InteractionDTO INTERACTION_DTO = new InteractionDTO("11111111",
            Arrays.asList(INTERACTING_STRINGS));

    @Mock
    private EntityManagerProvider entityManagerProvider;

    @Mock
    private InteractionDTOAssembler assembler;

    @Mock
    private EntityManager entityManager;

    @Mock
    private EntityTransaction transaction;

    @InjectMocks
    InteractionHibernateRepository repo;

    @Before
    public void setUp() {
        willReturn(entityManager).given(entityManagerProvider).getEntityManager();
        willReturn(transaction).given(entityManager).getTransaction();
    }

    @Test
    public void whenGettingInteractionShouldCallInteractionAssemblerFromDTO() {
        willReturn(INTERACTION_DTO).given(entityManager).find(InteractionDTO.class, A_DIN.getValue());
        repo.get(A_DIN);
        verify(assembler).fromDTO(INTERACTION_DTO);
    }

    // ga
}
