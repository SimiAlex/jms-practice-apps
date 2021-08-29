package SimiAlex.com.github.applicationB.dao;

import SimiAlex.com.github.backend.model.Car;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@ApplicationScoped
public class CarRepositoryImpl implements CrudRepository<Car>{

    @PersistenceContext(name = "my-persistence-unit")
    private EntityManager em;

    @Override
    public void add(Car object) {
        em.persist(object);
    }

    @Override
    public List<Car> findAll() {
        return em.createQuery("from Car", Car.class).getResultList();
    }

    @Override
    public Car findById(int id) {
        return null;
    }

    @Override
    public void update(Car object) {

    }

    @Override
    public void delete(int id) {

    }
}
