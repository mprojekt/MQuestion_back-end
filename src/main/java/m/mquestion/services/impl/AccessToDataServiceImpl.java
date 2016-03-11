package m.mquestion.services.impl;

import java.util.*;
import m.mquestion.domain.*;
import m.mquestion.entities.*;
import m.mquestion.repositories.*;
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
    private SiblingQuestionDao siblingQuestionDao;
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
        List<Question> questions = questionDao.findOpenQuestionPage(page);
        return qc.convert(questions);
    }

    @Override
    public List<QuestionDto> getQuestionsToPreviewByPage(int page) {
        setQuestionsConverter();
        List<Question> questions = questionDao.findCloseQuestionPage(page);
        return qc.convert(questions);
    }

    @Override
    public Map<String, QuestionDto> getSiblingQuestions(long id) {        
        Question actual = questionDao.findOne(id);
        Question previous = siblingQuestionDao.getPreviusQuestion(actual);
        Question next = siblingQuestionDao.getNextQuestion(actual);
        
        setQuestionsConverter();
        QuestionDto previusDto = qc.convert(previous);
        QuestionDto nextDto = qc.convert(next);
        
        Map<String, QuestionDto> result = new HashMap<>();
        result.put("prevQuestion", previusDto);
        result.put("nextQuestion", nextDto);
        return result;
    }
    
    private void setQuestionsConverter(){
        if(qc == null){
            qc = new QuestionsConverter(env.getProperty("site.baseUrl"));
        }
    }

}