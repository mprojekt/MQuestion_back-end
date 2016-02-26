package m.mquestion.utility;

import java.util.*;
import m.mquestion.domain.*;
import m.mquestion.entities.*;
import org.apache.commons.validator.UrlValidator;


public class QuestionEntityToComplexDomainConverter {
    
    private final String baseUrl;

    public QuestionEntityToComplexDomainConverter(String baseUrl) {
        UrlValidator urlValidator = new UrlValidator();
        if(urlValidator.isValid(baseUrl))
            this.baseUrl = baseUrl;
        else
            this.baseUrl = "";
    }
    
    public String getBaseUrl(){
        return baseUrl;
    }
    
    public ExtendedQuestionDto convert(Question question, String jSessionId) throws IllegalArgumentException{
        if((question == null) || (question.getId() == null) || (question.getId() <= 0))
            throw new IllegalArgumentException();
        
        ExtendedQuestionDto result = new ExtendedQuestionDto();
        result.setId(question.getId());
        result.setTitle(question.getTitle());
        result.setContent(question.getContent());
        result.setCreateDateTime(question.getCreateDateTime().toLocalDateTime());
        result.setEndDateTime(question.getEndDateTime().toLocalDateTime());
        result.setNumberAnswerToCheck(question.getNumberAnswersToCheck());
        result.setShowResultNow(question.isEnabledShowResultNow());
                
        Type type = question.getTypeQuestion();
        result.setType(type.getName());
        
        List<AnswerDto> answers = new ArrayList<>();
        for (Answer sourceAnswer : question.getThisQuestionAnswers()) {
            AnswerDto answer = new AnswerDto();
            answer.setId(sourceAnswer.getId());
            answer.setContent(sourceAnswer.getContent());
            answer.setNumberOfVote(sourceAnswer.getCurrentVoteNumber());
            
            Map<String, String> links = new HashMap<>();
            links.put("vote", "/question/vote/" + question.getId() + "." + sourceAnswer.getId());
            answer.setLinks(links);
            
            answers.add(answer);
        }
        result.setAnswers(answers);
        
        if(null != jSessionId){
            List<Voter> voters = question.getThisQuestionVoter();
            for (Voter voter : voters) {
                if(voter.getjSessionId().equals(jSessionId)){
                    result.setAnswered(true);
                    break;
                }
            }
        }
        
        Map<String, String> links = new HashMap<>();
        links.put("self", "/question/" + question.getId());
        result.setLinks(links);
        
        return result;
    }
    
    public List<ExtendedQuestionDto> convert(List<Question> questions, String jSessionId) throws IllegalArgumentException{
        if((questions == null) || (questions.isEmpty()))
            throw new IllegalArgumentException();
        
        Set<Question> tmp = new HashSet<>(questions);
        if(tmp.size() != questions.size())
            throw new IllegalArgumentException();
        
        List<ExtendedQuestionDto> result = new ArrayList<>();
        for (Question question : questions) {
            try{
                ExtendedQuestionDto convertedQuestion = convert(question, jSessionId);
                result.add(convertedQuestion);
            } catch(IllegalArgumentException e){
                throw new IllegalArgumentException();
            }            
        }
        
        return result;
    }
}
