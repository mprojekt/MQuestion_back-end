package m.mquestion.services.impl;

import java.util.*;
import m.mquestion.domain.*;
import m.mquestion.entities.*;
import m.mquestion.repositories.QuestionDao;
import m.mquestion.services.AccessToDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AccessToDataServiceImpl implements AccessToDataService {
    
    @Autowired
    private QuestionDao questionDao;    

    @Override
    public QuestionDto getQuestionsToVoteByPage(int page) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public QuestionDto getQuestionsToPreviewByPage(int page) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
