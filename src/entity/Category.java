package entity;

/**
 * map to table category
 */
public class Category {

    //member variables
    private int id;
    private String name;
    private int recordNumber; //not stored in db

    public Category(int id, String name) {
        this.id = id;
        this.name = name;
        this.recordNumber = 0;
    }

    public Category(String name) {
        this.name = name;
    }

    //getters and setters

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

    public int getRecordNumber() {
        return recordNumber;
    }

    public void setRecordNumber(int recordNumber) {
        this.recordNumber = recordNumber;
    }

    @Override
    public String toString() {
        return name;
    }
}
