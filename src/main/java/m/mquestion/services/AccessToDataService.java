package m.mquestion.services;

import java.util.*;
import m.mquestion.domain.*;

public interface AccessToDataService {
    
    public QuestionDto getQuestion(long id);
    public ExtendedQuestionDto getQuestion(long id, String jSessionId);
    
    public Map<String, QuestionDto> getSiblingQuestions(long id);
    public List<QuestionDto> getQuestionsToVoteByPage(int page);
    public List<QuestionDto> getQuestionsToPreviewByPage(int page);

}
