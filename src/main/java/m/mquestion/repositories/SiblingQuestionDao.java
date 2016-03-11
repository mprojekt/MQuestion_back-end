package m.mquestion.repositories;

import m.mquestion.entities.Question;

public interface SiblingQuestionDao {
    
    public Question getPreviusQuestion(Question question);
    public Question getNextQuestion(Question question);

}
