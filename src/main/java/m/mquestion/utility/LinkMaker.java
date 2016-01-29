package m.mquestion.utility;

import java.util.Map;

public interface LinkMaker<T> {
    
    public void setBaseUrl(String baseUrl);
    public Map<String, String> makeLinks(T dto);

}
