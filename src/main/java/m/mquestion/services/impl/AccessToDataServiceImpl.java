package m.mquestion.services.impl;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import m.mquestion.domain.QuestionDto;
import m.mquestion.domain.ResponseQuestionDto;
import m.mquestion.entities.Question;
import m.mquestion.repositories.QuestionDao;
import m.mquestion.services.AccessToDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AccessToDataServiceImpl implements AccessToDataService {
    
    @Autowired
    private QuestionDao questionDao;
    
    @Override
    public QuestionDto getQuestionById(long idQuestion) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ResponseQuestionDto getQuestionsToVoteByPage(int page) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ResponseQuestionDto getQuestionsToPreviewByPage(int page) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
