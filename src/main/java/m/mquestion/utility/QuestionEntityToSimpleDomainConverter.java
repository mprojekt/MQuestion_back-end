package m.mquestion.utility;

import java.util.*;
import m.mquestion.domain.*;
import m.mquestion.entities.*;
import org.apache.commons.validator.UrlValidator;

public class QuestionEntityToSimpleDomainConverter {
    
    private final String baseUrl;

    public QuestionEntityToSimpleDomainConverter(String baseUrl) {
        UrlValidator urlValidator = new UrlValidator();
        if(urlValidator.isValid(baseUrl))
            this.baseUrl = baseUrl;
        else
            this.baseUrl = "";
    }
    
    public String getBaseUrl(){
        return baseUrl;
    }
    
    public SimpleQuestionDto convert(Question question) throws IllegalArgumentException{
        if((question == null) || (question.getId() == null) || (question.getId() <= 0))
            throw new IllegalArgumentException();
        
        SimpleQuestionDto result = new SimpleQuestionDto();
        result.setId(question.getId());
        result.setTitle(question.getTitle());
        result.setContent(question.getContent());
        result.setEndDate(question.getEndDateTime().toLocalDateTime());
        
        Type type = question.getTypeQuestion();
        result.setType(type.getName());
        
        Map<String, String> links = new HashMap<>();
        links.put("self", baseUrl + "/question/" + question.getId());
        result.setLinks(links);
        
        return result;
    }
    
    public List<SimpleQuestionDto> convert(List<Question> questions) throws IllegalArgumentException{
        if((questions == null) || (questions.isEmpty()))
            throw new IllegalArgumentException();
        
        Set<Question> tmp = new HashSet<>(questions);
        if(tmp.size() != questions.size())
            throw new IllegalArgumentException();
        
        List<SimpleQuestionDto> result = new ArrayList<>();
        for (Question question : questions) {
            try{
                SimpleQuestionDto convertedQuestion = convert(question);
                result.add(convertedQuestion);
            } catch(IllegalArgumentException e){
                throw new IllegalArgumentException();
            }            
        }
        
        return result;
    }

}
