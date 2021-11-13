package repository;

import java.util.List;

/**
 * @author Nikita Gvardeev
 * 13.11.2021
 */

public interface GenericRepository<T, ID> {

    T getById(ID id);
    List<T> getAll();
    T save(T t);
    T update(T t);
    T deleteById(T t);
}