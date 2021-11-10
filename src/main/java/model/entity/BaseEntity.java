package model.entity;

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