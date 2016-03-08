package m.mquestion.services.impl;

import java.util.*;
import m.mquestion.domain.*;
import m.mquestion.entities.*;
import m.mquestion.repositories.QuestionDao;
import m.mquestion.services.AccessToDataService;
import m.mquestion.utility.QuestionsConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AccessToDataServiceImpl implements AccessToDataService {
    
    @Autowired
    private QuestionDao questionDao;
    @Autowired
    private Environment env;
    
    private QuestionsConverter qc;

    @Override
    public QuestionDto getQuestion(long id) {
        setQuestionsConverter();
        Question question = questionDao.findOne(id);
        return qc.convert(question);
    }
    
    @Override
    public ExtendedQuestionDto getQuestion(long id, String jSessionId) {
        setQuestionsConverter();
        Question question = questionDao.findOne(id);
        return qc.convert(question, jSessionId);
    }

    @Override
    public List<QuestionDto> getQuestionsToVoteByPage(int page) {
        setQuestionsConverter();
        List<Question> questions = questionDao.findQuestionToVotePage(page);
        return qc.convert(questions);
    }

    @Override
    public List<QuestionDto> getQuestionsToPreviewByPage(int page) {
        setQuestionsConverter();
        List<Question> questions = questionDao.findQuestionToShowResultPage(page);
        return qc.convert(questions);
    }

    @Override
    public Map<String, QuestionDto> getSiblingQuestions(long id) {
        setQuestionsConverter();
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    private void setQuestionsConverter(){
        if(qc == null){
            qc = new QuestionsConverter(env.getProperty("site.baseUrl"));
        }
    }

}