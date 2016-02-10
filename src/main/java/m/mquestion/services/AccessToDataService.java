package m.mquestion.services;

import m.mquestion.domain.ResponseQuestionDto;

public interface AccessToDataService {
    
    public ResponseQuestionDto getQuestionsToVoteByPage(int page);
    public ResponseQuestionDto getQuestionsToPreviewByPage(int page);

}
