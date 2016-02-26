package m.mquestion.services;

import m.mquestion.domain.*;

public interface AccessToDataService {
    
    public QuestionDto getQuestionsToVoteByPage(int page);
    public QuestionDto getQuestionsToPreviewByPage(int page);

}
