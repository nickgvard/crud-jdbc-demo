package model.entity;

/**
 * @author Nikita Gvardeev 01.11.2021
 * email gvardeev@po-korf.ru
 */
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