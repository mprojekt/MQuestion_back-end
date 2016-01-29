package m.mquestion.utility.linkers.impl;

import java.util.HashMap;
import java.util.Map;
import m.mquestion.domain.AnswerDto;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class AnswerLinkerTest {
    
    AnswerLinkMaker instance;
    AnswerDto dto;
    
    public AnswerLinkerTest(){        
    }
    
    @Before
    public void setUp(){        
        instance = new AnswerLinkMaker("http://www.example.com/mquestion");
        dto = new AnswerDto();
    }
    
    @Test
    public void testIsDtoCorrectNormal() {
        dto.setId(254l);
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
        dto.setId(-1l);
        boolean expResult = false;
        
        boolean result = instance.isDtoCorrect(dto);
        assertEquals(expResult, result);
    }

    @Test
    public void testMakeSelfLink() {
        dto.setId(453l);
        Map<String, String> expResult = new HashMap<>();
        expResult.put("self", "http://www.example.com/mquestion/answer/453");
        
        Map<String, String> result = instance.makeSelfLink(dto);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testMakeSelfLinkWithoutBaseUrl() {
        dto.setId(954l);
        instance.setBaseUrl(null);
        Map<String, String> expResult = new HashMap<>();
        expResult.put("self", "/answer/954");
        
        Map<String, String> result = instance.makeSelfLink(dto);
        assertEquals(expResult, result);
    }

    @Test
    public void testMakeAnotherLinksNormal() {
        dto.setId(95l);
        Map<String, String> expResult = new HashMap<>();
        expResult.put("vote", "http://www.example.com/mquestion/answer/vote/95");
        
        Map<String, String> result = instance.makeAnotherLinks(dto);
        assertEquals(expResult, result);
    }    
    
    @Test
    public void testMakeAnotherLinksWithoutBaseUrl() {
        dto.setId(4195l);
        instance.setBaseUrl(null);
        Map<String, String> expResult = new HashMap<>();
        expResult.put("vote", "/answer/vote/4195");
        
        Map<String, String> result = instance.makeAnotherLinks(dto);
        assertEquals(expResult, result);
    }
    
}