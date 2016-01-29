package m.mquestion.utility.linkers.impl;

import java.util.HashMap;
import java.util.Map;
import m.mquestion.domain.QuestionDto;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class QuestionLinkMakerTest {
    
    QuestionDto dto;
    QuestionLinkMaker instance;
    
    public QuestionLinkMakerTest() {
    }
    
    @Before
    public void setUp() {
        instance = new QuestionLinkMaker();
        instance.setBaseUrl("http://www.example.com/mquestion");
        dto = new QuestionDto();
    }
    
    @Test
    public void testIsDtoCorrectNormal() {
        dto.setId(94812l);
        boolean expResult = true;
        
        boolean result = instance.isDtoCorrect(dto);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testIsDtoCorrectEmpty() {
        boolean expResult = false;
        
        boolean result = instance.isDtoCorrect(dto);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testIsDtoCorrectZero() {
        dto.setId(0);
        boolean expResult = false;
        
        boolean result = instance.isDtoCorrect(dto);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testIsDtoCorrectNegativ() {
        dto.setId(-1684l);
        boolean expResult = false;
        
        boolean result = instance.isDtoCorrect(dto);
        assertEquals(expResult, result);
    }

    @Test
    public void testMakeSelfLinkNormal() {
        dto.setId(150l);
        Map<String, String> expResult = new HashMap<>();
        expResult.put("self", "http://www.example.com/mquestion/question/150");
        
        Map<String, String> result = instance.makeSelfLink(dto);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testMakeSelfLinkWithoutBaseUrl() {
        dto.setId(12530l);
        instance.setBaseUrl(null);
        Map<String, String> expResult = new HashMap<>();
        expResult.put("self", "/question/12530");
        
        Map<String, String> result = instance.makeSelfLink(dto);
        assertEquals(expResult, result);
    }

    @Test
    public void testMakeAnotherLinksNormal() {
        Map<String, String> expResult = new HashMap<>();
        
        setTestEvironment("http://www.example.com/mquestion", 14l, 24l);        
        expResult.put("prev", "http://www.example.com/mquestion/question/14");
        expResult.put("next", "http://www.example.com/mquestion/question/24");
        expResult.put("home", "http://www.example.com/mquestion/");
        
        Map<String, String> result = instance.makeAnotherLinks(dto);
        assertEquals(expResult, result);        
    }
    
    @Test
    public void testMakeAnotherLinksWithoutBaseLink() {
        Map<String, String> expResult = new HashMap<>();
        
        setTestEvironment(null, 14l, 24l);        
        expResult.put("prev", "/question/14");
        expResult.put("next", "/question/24");
        expResult.put("home", "/");
        
        Map<String, String> result = instance.makeAnotherLinks(dto);
        assertEquals(expResult, result);        
    }
    
    @Test
    public void testMakeAnotherLinksZeroPrevId() {
        Map<String, String> expResult = new HashMap<>();
        
        setTestEvironment("http://www.example.com/mquestion", 0, 24l);
        expResult.put("next", "http://www.example.com/mquestion/question/24");
        expResult.put("home", "http://www.example.com/mquestion/");
        
        Map<String, String> result = instance.makeAnotherLinks(dto);
        assertEquals(expResult, result);        
    }
    
    @Test
    public void testMakeAnotherLinksZeroNextId() {
        Map<String, String> expResult = new HashMap<>();
        
        setTestEvironment("http://www.example.com/mquestion", 42l, 0);
        expResult.put("prev", "http://www.example.com/mquestion/question/42");
        expResult.put("home", "http://www.example.com/mquestion/");
        
        Map<String, String> result = instance.makeAnotherLinks(dto);
        assertEquals(expResult, result);        
    }
    
    private void setTestEvironment(String baseUrl, long prevId, long nextId){
        dto.setId(12l);
        instance = new QuestionLinkMaker(baseUrl, prevId, nextId);
    }
    
}