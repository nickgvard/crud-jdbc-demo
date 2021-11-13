package model;

/**
 * @author Nikita Gvardeev
 * 13.11.2021
 */

public class BaseEntity {
    private long id;

    public BaseEntity(long id) {
        this.id = id;
    }

    public BaseEntity() {
    }

    public long id() {
        return id;
    }
}