package m.mquestion.services.impl;

import m.mquestion.domain.StatisticQuestion;
import m.mquestion.repositories.QuestionDao;
import m.mquestion.services.*;
import m.mquestion.utility.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StatisticServiceImpl implements StatisticService{
    
    @Autowired
    private QuestionDao questionDao;

    @Override
    public StatisticQuestion getStatistic() {
        long numberAllQuestion = questionDao.count();
        int numberAllPage = Pagination.calculateNumberMaxPage(numberAllQuestion);
        return new StatisticQuestion(numberAllQuestion, numberAllPage);
    }
    
    

}
