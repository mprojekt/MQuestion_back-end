package m.mquestion.utility;

import java.util.List;
import m.mquestion.domain.ResponseQuestionDto;
import m.mquestion.entities.Question;
import m.mquestion.utility.data.QuestionSibling;

public interface ResponseQuestionMaker {
    
    public ResponseQuestionDto make(Question entity, QuestionSibling sibling);
    public ResponseQuestionDto make(List<Question> listEntity);

}
