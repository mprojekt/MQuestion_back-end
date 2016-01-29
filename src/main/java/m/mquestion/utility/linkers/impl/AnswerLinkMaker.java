package m.mquestion.utility.linkers.impl;

import java.util.HashMap;
import java.util.Map;
import m.mquestion.domain.AnswerDto;
import m.mquestion.utility.LinkMaker;


public class AnswerLinkMaker extends GeneralLinkMaker<AnswerDto> implements LinkMaker<AnswerDto>{
    
    public AnswerLinkMaker() {
        super();
    }

    public AnswerLinkMaker(String baseUrl) {
        super(baseUrl);
    }

    @Override
    protected boolean isDtoCorrect(AnswerDto dto) {
        return dto.getId() > 0;
    }

    @Override
    protected Map<String, String> makeSelfLink(AnswerDto dto) {
        Map<String, String> result = new HashMap<>();
        String selfLink = getBaseUrl() + "/answer/" + dto.getId();        
        result.put("self", selfLink);
        return result;
    }

    @Override
    protected Map<String, String> makeAnotherLinks(AnswerDto dto) {
        Map<String, String> result = new HashMap<>();
        String voteLink = getBaseUrl() + "/answer/vote/" + dto.getId();
        result.put("vote", voteLink);
        return result;
    }
    
}