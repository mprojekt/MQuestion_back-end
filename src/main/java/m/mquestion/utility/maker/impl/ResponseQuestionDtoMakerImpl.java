package m.mquestion.utility.maker.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import m.mquestion.domain.QuestionDto;
import m.mquestion.domain.ResponseQuestionDto;
import m.mquestion.entities.Question;
import m.mquestion.entities.Voter;
import m.mquestion.utility.EntityToDtoConverter;
import m.mquestion.utility.LinkMaker;
import m.mquestion.utility.ResponseQuestionMaker;
import m.mquestion.utility.converters.impl.QuestionEntityToDtoConverter;
import m.mquestion.utility.data.QuestionSibling;
import m.mquestion.utility.linkers.impl.QuestionLinkMaker;
import org.apache.commons.validator.UrlValidator;


public class ResponseQuestionDtoMakerImpl implements ResponseQuestionMaker{
    
    private String baseUrl;
    private ResponseQuestionDto result;

    private ResponseQuestionDtoMakerImpl() {
        result = new ResponseQuestionDto();
    }
    
    public ResponseQuestionDtoMakerImpl(String jSessionID) {
        this();
        setSesionToken(jSessionID);
    }
    
    private void setSesionToken(String jSessionID) throws NullPointerException{
        if(jSessionIdExist(jSessionID)){
            result.setjSessionID(jSessionID);
        } else {
            throw new NullPointerException("Session Token must be given.");
        }
    } 
    
    private boolean jSessionIdExist(String jSessionID) {
        return jSessionID != null;
    }

    public ResponseQuestionDtoMakerImpl(String baseUrl, String jSessionID, int numberAllQuestion, int numberCurrentPage, int numberLastPage) throws IllegalArgumentException {
        this(jSessionID);
        setBaseUrl(baseUrl);
        setResponseStatistic(numberAllQuestion,  numberCurrentPage,  numberLastPage);
    }
    
    private void setBaseUrl(String baseUrl) {
        if(corectBaseUrl(baseUrl))
            this.baseUrl = baseUrl;
        else
            this.baseUrl = "";
    }
    
    private boolean corectBaseUrl(String baseUrl) {
        UrlValidator urlValidator = new UrlValidator();        
        if(baseUrl != null)
            return urlValidator.isValid(baseUrl);
        return false;
    }
    
    private void setResponseStatistic(int numberAllQuestion, int numberCurrentPage, int numberLastPage) throws IllegalArgumentException{
        if(isPositiveNumbers(numberAllQuestion, numberCurrentPage, numberLastPage) && 
                isRightDependingNumbers(numberAllQuestion, numberCurrentPage, numberLastPage)){            
            addDataToResult(numberAllQuestion, numberCurrentPage, numberLastPage);
        } else{            
            throw new IllegalArgumentException("One of argument is wrong");
        }
    }
    
    public ResponseQuestionDto getResponse(){
        return result;
    }
    
    public String getBaseUrl(){
        return baseUrl;
    }
    
    @Override
    public ResponseQuestionDto make(Question entity, QuestionSibling sibling) {
        EntityToDtoConverter<Question, QuestionDto> converter = new QuestionEntityToDtoConverter();
        LinkMaker<QuestionDto> linker = new QuestionLinkMaker(baseUrl, sibling.getPrevQuestionId(), sibling.getNextQuestionId());
        
        QuestionDto questionDto = converter.convert(entity);
        Map<String, String> links = linker.makeLinks(questionDto); 
        questionDto.setLinks(links);
        //TODO check is answered
        List<Voter> voters = entity.getThisQuestionVoter();
        for (Voter voter : voters) {
            if(voter.getjSessionId().equals(result.getjSessionID()))
                questionDto.setAnswered(true);
        }
        
        putQuestionToResult(questionDto);   
        return result;
    }

    @Override
    public ResponseQuestionDto make(List<Question> listEntity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private boolean isPositiveNumbers(int numberAllQuestion, int numberCurrentPage, int numberLastPage){
        return ((numberAllQuestion >= 0) && (numberCurrentPage >= 0) && (numberLastPage >= 0));
    }

    private boolean isRightDependingNumbers(int numberAllQuestion, int numberCurrentPage, int numberLastPage){
        return !((numberCurrentPage > numberAllQuestion) || (numberCurrentPage > numberLastPage) || (numberLastPage > numberAllQuestion));
    }

    private void addDataToResult(int numberAllQuestion, int numberCurrentPage, int numberLastPage) {
        result.setNumberAllQuestion(numberAllQuestion);
        result.setNumberCurrentPage(numberCurrentPage);
        result.setNumberLastPage(numberLastPage);
    }

    private void putQuestionToResult(QuestionDto questionDto) {
        List<QuestionDto> questions = new ArrayList<>();
        questions.add(questionDto);
        
        result.setQuestions(questions);
    }


}
