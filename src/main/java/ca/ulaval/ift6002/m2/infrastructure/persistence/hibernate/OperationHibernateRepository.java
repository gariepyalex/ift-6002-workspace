package ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate;

import java.util.Collection;

import javax.persistence.TypedQuery;

import ca.ulaval.ift6002.m2.domain.operation.Operation;
import ca.ulaval.ift6002.m2.domain.operation.OperationRepository;
import ca.ulaval.ift6002.m2.infrastructure.persistence.assemblers.OperationDTOAssembler;
import ca.ulaval.ift6002.m2.infrastructure.persistence.dto.OperationDTO;
import ca.ulaval.ift6002.m2.infrastructure.persistence.provider.EntityManagerProvider;

public class OperationHibernateRepository extends HibernateRepository<OperationDTO> implements OperationRepository {

    private final OperationDTOAssembler operationAssembler;

    public OperationHibernateRepository(OperationDTOAssembler operationAssembler) {
        super(OperationDTO.class);
        this.operationAssembler = operationAssembler;
    }

    public OperationHibernateRepository(EntityManagerProvider entityManagerProvider,
            OperationDTOAssembler operationAssembler) {
        super(entityManagerProvider, OperationDTO.class);
        this.operationAssembler = operationAssembler;
    }

    @Override
    public Operation get(int number) {
        OperationDTO operationDTO = find(number);

        return operationAssembler.fromDTO(operationDTO);
    }

    @Override
    public void store(Operation operation) {
        OperationDTO operationDTO = operationAssembler.toDTO(operation);

        operationDTO = merge(operationDTO);

        operation.updateNumber(operationDTO.number);
    }

    public Collection<Operation> list() {
        String query = "FROM OperationDTO";
        TypedQuery<OperationDTO> typedQuery = createQuery(query);

        return operationAssembler.fromDTOs(typedQuery.getResultList());
    }

}
