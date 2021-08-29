package SimiAlex.com.github.applicationB.dao;


import java.util.List;

public interface CrudRepository<T> {
    void add(T object);
    List<T> findAll();
    T findById(int id);
    void update(T object);
    void delete(int id);
}
