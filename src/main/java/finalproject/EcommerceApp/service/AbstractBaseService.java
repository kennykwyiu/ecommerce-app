package finalproject.EcommerceApp.service;

import finalproject.EcommerceApp.exception.ResourceNotFoundException;
import finalproject.EcommerceApp.model.AbstractAuditableEntity;
import finalproject.EcommerceApp.repository.AbstractBaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Service
public abstract class AbstractBaseService<T extends AbstractAuditableEntity<T, ID>, ID
        extends Serializable> {
    @Autowired
    protected AbstractBaseRepository<T, ID> repository;

    public AbstractBaseService(AbstractBaseRepository<T, ID> repository) {
        this.repository = repository;
    }

    public T save(T entity) {
        return repository.save(entity);
    }

    public boolean existsById(ID id) {
        return repository.existsById(id);
    }

    public List<T> findAll(Pageable pageable) {
        return repository.findAll(pageable).toList();
    }

//    public T findById(ID id) throws ResourceNotFoundException {
//        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(""));
//    }

    public T findById(ID id) throws ResourceNotFoundException {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Entity of %s not found by id %s", getClass().getSimpleName(), id)
                ));
    }

    public void delete(T entity) {
        repository.delete(entity);
    }
}
