package Model;

/**
 * Created by Martin Agnar Dahl on 25.01.2018.
 */

public class Medicine {

    private int id;
    private String name;
    private int doses;
    private int status;

    public Medicine(){
    }

    public Medicine(int id, String name, int doses, int status) {
        this.id = id;
        this.name = name;
        this.doses = doses;
        this.status = status;
    }

    public Medicine(String name, int doses, int status) {
        this.name = name;
        this.doses = doses;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDoses() {
        return doses;
    }

    public void setDoses(int doses) {
        this.doses = doses;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return String.valueOf(getId()) + ", Name: " + getName() + ", Doses: " + String.valueOf(getDoses()) + ", Status: " + String.valueOf(getStatus());
    }
}
