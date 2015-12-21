package m.mquestion.repositories;

import m.mquestion.entities.Type;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeDao  extends CrudRepository<Type, Integer> {
    
    public Type findByName(String name);

}
