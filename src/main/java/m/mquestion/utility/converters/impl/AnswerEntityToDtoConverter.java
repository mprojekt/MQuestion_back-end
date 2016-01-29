package m.mquestion.utility.converters.impl;

import m.mquestion.domain.AnswerDto;
import m.mquestion.entities.Answer;
import m.mquestion.utility.EntityToDtoConverter;


public class AnswerEntityToDtoConverter implements EntityToDtoConverter<Answer, AnswerDto>{
    
    private Answer answer;
    private final AnswerDto result;

    public AnswerEntityToDtoConverter() {
        this.result = new AnswerDto();
    }    
    
    @Override
    public AnswerDto convert(Answer answer) throws IllegalArgumentException{
        this.answer = answer;
        
        if(answerExist() && answerIsRight()){
            rewriteSimpleData();
            return result;
        } else {
            throw new IllegalArgumentException("No Answer obiect to convert!");
        } 
    }

    private boolean answerExist() {
        return answer != null;
    }

    private boolean answerIsRight() {
        if(answer.getId() != null){
            if(answer.getId() > 0)
                return true;
        }
        return false;        
    }
    
    private void rewriteSimpleData() {
        result.setId(answer.getId());
        result.setContent(answer.getContent());
        result.setRightAnswer(answer.isCorrectAnswer());
        result.setNumberOfVote(answer.getCurrentVoteNumber());
    }

}
