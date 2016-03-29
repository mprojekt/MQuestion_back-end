package m.mquestion.services;

import java.util.*;
import m.mquestion.domain.*;

public interface AccessToDataService {
    
    public QuestionDto getQuestion(long id) throws IllegalArgumentException;
    public ExtendedQuestionDto getQuestion(long id, String jSessionId) throws IllegalArgumentException;
    
    public Map<String, QuestionDto> getSiblingQuestions(long id) throws IllegalArgumentException;
    public List<QuestionDto> getQuestionsToVoteByPage(int page) throws IllegalArgumentException;
    public List<QuestionDto> getQuestionsToResultByPage(int page) throws IllegalArgumentException;

}
