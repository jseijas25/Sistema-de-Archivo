package modelos;

/**
 * Created by Admin on 13-06-2015.
 */
public class Programa {

    private int id;
    private String name;
    private boolean active;
    private int recordCount;

    public Programa() {
    }

    public Programa(int id, String name, boolean active, int recordCount) {
        this.id = id;
        this.name = name;
        this.active = active;
        this.recordCount = recordCount;
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(int recordCount) {
        this.recordCount = recordCount;
    }
}
