package ca.ulaval.ift6002.m2.infrastructure.persistence.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class SurgeonDTO {

    @Id
    @GeneratedValue
    public Integer id;
    public Integer surgeon;

    public SurgeonDTO(Integer surgeon) {
        this.surgeon = surgeon;
    }

    protected SurgeonDTO() {
        this.surgeon = 0;
    }
}
