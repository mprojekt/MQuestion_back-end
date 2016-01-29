package m.mquestion.utility.converters.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import m.mquestion.domain.AnswerDto;
import m.mquestion.domain.QuestionDto;
import m.mquestion.entities.Answer;
import m.mquestion.entities.Question;
import m.mquestion.entities.Type;
import m.mquestion.utility.EntityToDtoConverter;
import m.mquestion.utility.LinkMaker;
import m.mquestion.utility.linkers.impl.AnswerLinkMaker;


public class QuestionEntityToDtoConverter implements EntityToDtoConverter<Question, QuestionDto>{
    
    private Question question;
    private final QuestionDto result;

    public QuestionEntityToDtoConverter() {
        result = new QuestionDto();
    }
    
    @Override
    public QuestionDto convert(Question question) throws IllegalArgumentException{
        this.question = question;
        
        if(questionExist() && questionIsRight()){
            rewriteSimpleData(); 
            convertComplicatedData();
            return result;
        } else {
            throw new IllegalArgumentException("No Question obiect to convert!");
        } 
    }
    
    private boolean questionExist(){
        return question != null;
    }

    private boolean questionIsRight() {
        if(question.getId() != null){
            if(question.getId() > 0)
                return true;
        }
        return false;        
    }
    
    private void rewriteSimpleData(){
        result.setId(question.getId());
        result.setTitle(question.getTitle());
        result.setContent(question.getContent());
        result.setShowResultNow(question.isIsEnabledShowResultNow());
        result.setNumberAnswer(question.getNumberAnswersToCheck());
    }
    
    private void convertComplicatedData(){
        convertType();
        convertDate();
        convertAnswers();
    }
    
    private void convertType(){
        if(typeExist()){
            Type type = question.getTypeQuestion();
            result.setType(type.getName());
        }
    }
    
    private boolean typeExist(){
        return question.getTypeQuestion() != null;
    }
    
    private void convertDate(){
        convertCreatedDate();
        convertEndDate();
    }
    
    private void convertCreatedDate(){
        LocalDateTime ldt = question.getCreateDateTime() == null ? null : question.getCreateDateTime().toLocalDateTime();
        result.setCreateDate(ldt);
    }

    private void convertEndDate(){
        LocalDateTime ldt = question.getEndDateTime() == null ? null : question.getEndDateTime().toLocalDateTime();
        result.setEndDate(ldt);
    }

    private void convertAnswers() {
        if(ansfersExist()){
            List<AnswerDto> convertedAnswers = new ArrayList<>();
            LinkMaker<AnswerDto> linkMaker = new  AnswerLinkMaker();

            for (Answer answer : question.getThisQuestionAnswers()) {
                try {
                    AnswerDto answerDto = convertCurrentAnswer(answer);
                    Map<String, String> links = linkMaker.makeLinks(answerDto);
                    answerDto.setLinks(links);
                    convertedAnswers.add(answerDto);
                } catch(IllegalArgumentException iae){                
                }
            }            
            result.setAnswers(convertedAnswers);
        }
    }

    private boolean ansfersExist() {
        return question.getThisQuestionAnswers() != null;
    }
    
    private AnswerDto convertCurrentAnswer(Answer answer) throws IllegalArgumentException{
        EntityToDtoConverter<Answer, AnswerDto> answerConverter = new AnswerEntityToDtoConverter();
        
        try {
            return answerConverter.convert(answer);
        } catch(IllegalArgumentException iae){
            throw iae;
        }
    }

}
