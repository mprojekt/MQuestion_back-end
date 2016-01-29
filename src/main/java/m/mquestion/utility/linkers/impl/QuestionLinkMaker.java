package m.mquestion.utility.linkers.impl;

import java.util.HashMap;
import java.util.Map;
import m.mquestion.domain.QuestionDto;


public class QuestionLinkMaker extends GeneralLinkMaker<QuestionDto>{
    
    private long prevQuestionId;
    private long nextQuestionId;

    public QuestionLinkMaker() {
        super();
    }

    public QuestionLinkMaker(String baseUrl, long prevQuestionId, long nextQuestionId) {
        super(baseUrl);
        this.prevQuestionId = prevQuestionId;
        this.nextQuestionId = nextQuestionId;
    }

    @Override
    protected boolean isDtoCorrect(QuestionDto questionDto) {
        return questionDto.getId() > 0;
    }

    @Override
    protected Map<String, String> makeSelfLink(QuestionDto questionDto) {
        Map<String, String> result = new HashMap<>();
        String selfLink = getBaseUrl() + "/question/" + questionDto.getId();        
        result.put("self", selfLink);
        return result;
    }

    @Override
    protected Map<String, String> makeAnotherLinks(QuestionDto dto) {
        Map<String, String> result = new HashMap<>();
        
        if(prevIdExist()){
            String prevLink = generatePrevLink();
            result.put("prev", prevLink);
        }
        if(nextIdExist()){
            String nextLink = generateNextLink();
            result.put("next", nextLink);
        }          
        String homeLink = getBaseUrl() + "/"; 
        result.put("home", homeLink);
        
        return result;
    }
    
    private boolean prevIdExist() {
        return prevQuestionId > 0;
    }
    
    private String generatePrevLink() {
        return getBaseUrl() + "/question/" + prevQuestionId; 
    }

    private boolean nextIdExist() {
        return nextQuestionId > 0;
    }

    private String generateNextLink() {
        return getBaseUrl() + "/question/" + nextQuestionId;
    }
    
}