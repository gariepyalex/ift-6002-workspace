package ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate;

public class DrugPersistentClass {
    private int din;
    private String name;
    private String descriptor;
    private int id;

    public DrugPersistentClass() {
    }

    public DrugPersistentClass(int din, String name, String descriptor) {
        this.din = din;
        this.name = name;
        this.descriptor = descriptor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDin() {
        return din;
    }

    public void setDin(int din) {
        this.din = din;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescriptor() {
        return descriptor;
    }

    public void setDescriptor(String descriptor) {
        this.descriptor = descriptor;
    }

}
