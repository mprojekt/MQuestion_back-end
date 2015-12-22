package m.mquestion.repositories;

import m.mquestion.entities.Answer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerDao extends CrudRepository<Answer, Long>{

}
