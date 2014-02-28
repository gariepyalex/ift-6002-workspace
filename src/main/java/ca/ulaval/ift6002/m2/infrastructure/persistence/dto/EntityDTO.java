package ca.ulaval.ift6002.m2.infrastructure.persistence.dto;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class EntityDTO {

    @Id
    public int id;

}
