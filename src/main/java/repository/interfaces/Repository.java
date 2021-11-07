package repository;

public interface Repository {

    void add();
    void remove();
    void update();

    Object resultQuery();
}