package m.mquestion.utility;

import java.util.*;
import m.mquestion.domain.*;
import m.mquestion.entities.*;
import org.apache.commons.validator.UrlValidator;


public class QuestionConverter {

    private final String baseUrl;

    public QuestionConverter(String baseUrl) {
        UrlValidator urlValidator = new UrlValidator();
        if(urlValidator.isValid(baseUrl))
            this.baseUrl = baseUrl;
        else
            this.baseUrl = "";
    }
    
    public String getBaseUrl(){
        return baseUrl;
    }
    
    public QuestionDto convert(Question question) throws IllegalArgumentException{
        return (QuestionDto) primaryConvert(question, new QuestionDto());
    }
    
    public ExtendedQuestionDto convert(Question question, String jSessionId) throws IllegalArgumentException{        
        ExtendedQuestionDto result = (ExtendedQuestionDto) primaryConvert(question, new ExtendedQuestionDto());
        
        result.setCreateDateTime(question.getCreateDateTime().toLocalDateTime());
        result.setNumberAnswerToCheck(question.getNumberAnswersToCheck());
        result.setShowResultNow(question.isEnabledShowResultNow());
        
        List<AnswerDto> answers = convertAnswers(question.getThisQuestionAnswers(), question.getId());
        result.setAnswers(answers);
        
        boolean isAnswered = checkIsAnswered(question.getThisQuestionVoter(), jSessionId);
        result.setAnswered(isAnswered);
        
        return result;
    }
    
    private Convertable primaryConvert(Question question, QuestionDto result) throws IllegalArgumentException{ 
        if(!isRightQuestion(question))
            throw new IllegalArgumentException();
        
        result.setId(question.getId());
        result.setTitle(question.getTitle());
        result.setContent(question.getContent());
        result.setEndDateTime(question.getEndDateTime().toLocalDateTime());
        
        String type = getTypeToStringFrom(question);
        result.setType(type);
        
        Map<String, String> links = makeLinksFrom("self", "" + question.getId());
        result.setLinks(links);
        
        return result;
    }
    
    private boolean isRightQuestion(Question question){
        return ((question != null) && (question.getId() != null) && (question.getId() > 0));
    }
    
    private String getTypeToStringFrom(Question question){
        Type type = question.getTypeQuestion();
        return type.getName();
    }
    
    private Map<String, String> makeLinksFrom(String name, String addition){
        Map<String, String> links = new HashMap<>();
        links.put(name, baseUrl + "/question/" + addition);
        return links;
    }
    
    private List<AnswerDto> convertAnswers(List<Answer> answers, long idQuestion){
        List<AnswerDto> result = new ArrayList<>();
        for (Answer sourceAnswer : answers) {
            AnswerDto answer = new AnswerDto();
            answer.setId(sourceAnswer.getId());
            answer.setContent(sourceAnswer.getContent());
            answer.setNumberOfVote(sourceAnswer.getCurrentVoteNumber());
            
            Map<String, String> links = makeLinksFrom("vote", "vote/" + idQuestion + "." + sourceAnswer.getId());
            answer.setLinks(links);
            
            result.add(answer);
        }
        return result;
    }
    
    private boolean checkIsAnswered(List<Voter> voters, String jSessionId){
        if(null != jSessionId){
            for (Voter voter : voters) {
                if(voter.getjSessionId().equals(jSessionId)){
                    return true;
                }
            }
        }
        return false;
    }
    
}
