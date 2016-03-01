package m.mquestion.utility;

import java.util.*;
import m.mquestion.domain.*;
import m.mquestion.entities.*;

public class QuestionsConverter extends QuestionConverter{

    private final QuestionConverter questionConverter;
    
    public QuestionsConverter(String baseUrl) {
        super(baseUrl);
        questionConverter = new QuestionConverter(getBaseUrl());
    }    
    
    public List<QuestionDto> convert(List<Question> questions) throws IllegalArgumentException{
        return convertList(questions, DtoType.SIMPLE, null);
    }

    public List<ExtendedQuestionDto> convert(List<Question> questions, String jSessionId) throws IllegalArgumentException{
        return convertList(questions, DtoType.EXTENDED, jSessionId);
    }
    
    private List convertList(List<Question> questions, DtoType type, String jSessionId) throws IllegalArgumentException{
        if(!isRightQuestions(questions) || isDoubleQuestions(questions))
            throw new IllegalArgumentException();
        
        List result = new ArrayList();        
        for (Question question : questions) {
            try{                
                QuestionDto convertedQuestion = (QuestionDto) type.convertUsing(questionConverter, question, jSessionId);
                result.add(convertedQuestion);
            } catch(IllegalArgumentException e){
                throw new IllegalArgumentException();
            }            
        }
        
        return result;
    }
    
    private boolean isRightQuestions(List<Question> questions){
        return ((questions != null) && !questions.isEmpty());
    }
    
    private boolean isDoubleQuestions(List<Question> questions){
        Set<Question> tmp = new HashSet<>(questions);
        return (tmp.size() != questions.size());            
    }
    
    private enum DtoType{
        SIMPLE {

            @Override
            public Convertable convertUsing(QuestionConverter qc, Question question, String jSessionId) {
                return qc.convert(question);
            }
        }, 
        EXTENDED {
            
            @Override
            public Convertable convertUsing(QuestionConverter qc, Question question, String jSessionId) {
                return qc.convert(question, jSessionId);
            }
        };
        
        public abstract Convertable convertUsing(QuestionConverter qc, Question question, String jSessionId);
    }
}
