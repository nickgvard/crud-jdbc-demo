package model.entity;

public class Label extends BaseEntity {

    private final String name;

    public Label(long id, String name) {
        super(id);
        this.name = name;
    }

    public Label(String name) {
        super(0L);
        this.name = name;
    }

    public String name() {
        return name;
    }

    @Override
    public String toString() {
        return "Label {" +
                "name='" + name + '\'' +
                '}';
    }
}