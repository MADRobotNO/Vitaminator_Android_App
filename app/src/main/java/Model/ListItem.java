package Model;

/**
 * Created by Martin Agnar Dahl on 28.01.2018.
 */

public class ListItem {

    private String name;
    private String description;

    public ListItem(String name, String description) {
        this.name = name;
        this.description = description;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }





}
