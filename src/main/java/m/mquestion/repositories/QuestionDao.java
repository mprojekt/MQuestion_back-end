package m.mquestion.repositories;

import java.util.List;
import m.mquestion.entities.Question;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionDao extends CrudRepository<Question, Long>, PagingAndSortingRepository<Question, Long> {
    
    @Query(value = "SELECT * FROM Question q WHERE " +
            "q.end_date < CURRENT_TIMESTAMP " + 
            "ORDER BY q.end_date DESC, q.id ASC LIMIT ?1, 5", nativeQuery = true)
    public List<Question> findCloseQuestionPage(int start);
    
    @Query(value = "SELECT * FROM Question q WHERE " +
            "q.end_date > CURRENT_TIMESTAMP " + 
            "ORDER BY q.end_date ASC, q.id DESC LIMIT ?1, 5", nativeQuery = true)
    public List<Question> findOpenQuestionPage(int start);    
        
}