package m.mquestion.utility.linkers.impl;

import java.util.HashMap;
import java.util.Map;
import m.mquestion.utility.LinkMaker;
import org.apache.commons.validator.UrlValidator;

public abstract class GeneralLinkMaker<T> implements LinkMaker<T>{
    
    private String baseUrl;
    private T dto;
    private Map<String, String> result;

    public GeneralLinkMaker() {
        baseUrl = new String();
        result = new HashMap<>();
    }

    public GeneralLinkMaker(String baseUrl) {
        this();
        setBaseUrl(baseUrl);
    }
    
    @Override
    public Map<String, String> makeLinks(T dto) throws IllegalArgumentException{
        this.dto = dto;
        
        if(isDtoExist() && isDtoCorrect(dto)){
            addLinks();
            return result;
        } else {
            throw new IllegalArgumentException("Dto object is wrong!");
        }        
    }
    
    @Override
    public final void setBaseUrl(String baseUrl){
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

    private boolean isDtoExist() {
        return dto != null;
    }
    
    private void addLinks(){
        result.putAll(makeSelfLink(dto));
        result.putAll(makeAnotherLinks(dto));
    }
    
    protected String getBaseUrl(){
        if(baseUrl != null)
            return baseUrl;
        else
            return "";
    }

    protected abstract boolean isDtoCorrect(T dto);

    protected abstract Map<String, String> makeSelfLink(T dto);

    protected abstract Map<String, String> makeAnotherLinks(T dto);

}
