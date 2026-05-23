package domain;

public class Person {

    private int idPerson;
    private String name;

    public Person() {
    }

    public Person(int idPerson, String name) {
        this.idPerson = idPerson;
        this.name = name;
    }

    public int getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(int idPerson) {
        this.idPerson = idPerson;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" + "idPerson=" + idPerson + ", name=" + name + '}';
    }
}
