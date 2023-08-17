package finalproject.EcommerceApp.service;

import finalproject.EcommerceApp.exception.ResourceNotFoundException;
import finalproject.EcommerceApp.model.AbstractAuditableEntity;
import finalproject.EcommerceApp.repository.AbstractBaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Service
public abstract class AbstractBaseService<T extends AbstractAuditableEntity<T, ID>, ID
        extends Serializable> {
    @Autowired
    private AbstractBaseRepository<T, ID> repository;

    public T save(T entity) {
        return repository.save(entity);
    }

    public boolean existsById(ID id) {
        return repository.existsById(id);
    }

    public List<T> findAll() {
        return repository.findAll();
    }

//    public T findById(ID id) throws ResourceNotFoundException {
//        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(""));
//    }

    public Optional<T> findById(ID id) throws ResourceNotFoundException {
        return repository.findById(id);
    }

    public void delete(T entity) {
        repository.delete(entity);
    }
}
