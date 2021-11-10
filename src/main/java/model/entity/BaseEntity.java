package model.entity;

public class BaseEntity {
    private final long id;

    public BaseEntity(long id) {
        this.id = id;
    }

    public long id() {
        return id;
    }
}