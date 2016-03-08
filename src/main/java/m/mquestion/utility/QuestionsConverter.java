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
        return convertList(questions, QuestionDtoType.SIMPLE, null);
    }

    public List<ExtendedQuestionDto> convert(List<Question> questions, String jSessionId) throws IllegalArgumentException{
        return convertList(questions, QuestionDtoType.EXTENDED, jSessionId);
    }
    
    private List convertList(List<Question> questions, QuestionDtoType type, String jSessionId) throws IllegalArgumentException{
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
}
