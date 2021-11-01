package model.entity;

/**
 * @author Nikita Gvardeev 01.11.2021
 * email gvardeev@po-korf.ru
 */
public class BaseEntity {
    private final long id;

    public BaseEntity(long id) {
        this.id = id;
    }

    public long id() {
        return id;
    }
}