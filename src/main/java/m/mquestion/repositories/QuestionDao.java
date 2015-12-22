package m.mquestion.repositories;

import java.time.LocalDateTime;
import java.util.List;
import m.mquestion.entities.Question;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionDao extends CrudRepository<Question, Long> {

    public List<Question> findFirst5ByEndDateTimeBefore(LocalDateTime currentDateTime, Sort sort, Pageable pageable);
    public List<Question> findFirst5ByEndDateTimeAfter(LocalDateTime currentDateTime, Sort sort, Pageable pageable);
    
}
