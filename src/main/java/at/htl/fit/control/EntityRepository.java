package at.htl.fit.control;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

public class EntityRepository<T> implements PanacheRepository<T> {

    public T find(long id, Class<T> type) {
        var entity = findById(id);
        if(entity == null)
        {
            throw new EntityNotFoundException(String.format("%s with id=%d is not available", type.getName(), id));
        }
        return entity;
    }

    @Transactional
    public void delete(long id, Class<T> type) {
        this.delete(find(id, type));
    }

    @Transactional
    public void save(T entity) {
        this.persist(entity);
    }

    @Transactional
    public List<T> listAll() {
        return this.list("");
    }
}