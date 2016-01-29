package m.mquestion.utility.linkers.impl;

import java.util.HashMap;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class GeneralLinkMakerTest {
    
    GeneralLinkMaker instance;
    SomeoneDto dto;
    
    public GeneralLinkMakerTest() {        
    }
    
    @Before
    public void setUp() {
        instance = new GeneralLinkMakerImpl();
        instance.setBaseUrl("http://www.example.com/mquestion");
        dto = new SomeoneDto();
    }

    @Test
    public void testMakeLinksNormal() {        
        dto.setId(15l);
        Map<String, String> expResult = makeResultLinksNormal(dto.getId());
        
        Map<String, String> result = instance.makeLinks(dto);        
        assertEquals(expResult, result);
    }
    
    @Test
    public void testMakeLinksWithoutBaseLink() {        
        dto.setId(468l);
        instance.setBaseUrl("null");
        Map<String, String> expResult = makeResultWithoutLinksBaseLink(dto.getId());
        
        Map<String, String> result = instance.makeLinks(dto);        
        assertEquals(expResult, result);
    }
    
    @Test
    public void testMakeLinksNullDto() {
        dto = null;
        
        try {
            instance.makeLinks(dto);
            fail("Should be IllegalArgumentException, because Dao not exist");
        } catch (IllegalArgumentException iae){
            assertTrue(true);
        }
    }
    
    @Test
    public void testMakeLinksNewDto() {
        try {
            instance.makeLinks(dto);
            fail("Should be IllegalArgumentException, because Dao not exist");
        } catch (IllegalArgumentException iae){
            assertTrue(true);
        }
    }
    
    @Test
    public void testMakeLinksWrongDto() {
        dto.setId(-20l);
        
        try {
            instance.makeLinks(dto);
            fail("Should be IllegalArgumentException, because Dao not exist");
        } catch (IllegalArgumentException iae){
            assertTrue(true);
        }
    }

    @Test
    public void testSetBaseUrlNormal() {
        dto.setId(4658l);
        instance.setBaseUrl("http://www.example.com/mquestion");
        Map<String, String> expResult = makeResultLinksNormal(dto.getId());
        
        Map<String, String> result = instance.makeLinks(dto);  
        assertEquals(expResult, result);
    }
    
    @Test
    public void testSetBaseUrlNull() {
        dto.setId(4658l);
        instance.setBaseUrl(null);
        Map<String, String> expResult = makeResultWithoutLinksBaseLink(dto.getId());
        
        Map<String, String> result = instance.makeLinks(dto);  
        assertEquals(expResult, result);
    }
    
    @Test
    public void testSetBaseUrlEmpty() {
        dto.setId(4658l);
        instance.setBaseUrl("");
        Map<String, String> expResult = makeResultWithoutLinksBaseLink(dto.getId());
        
        Map<String, String> result = instance.makeLinks(dto);  
        assertEquals(expResult, result);
    }
    
    @Test
    public void testSetBaseUrlWithoutWww() {
        dto.setId(4658l);
        instance.setBaseUrl("http://example.com/mquestion");
        Map<String, String> expResult = makeResultLinksWithoutWww(dto.getId());
        
        Map<String, String> result = instance.makeLinks(dto);  
        assertEquals(expResult, result);
    }
    
    @Test
    public void testSetBaseUrlWithoutHttp() {
        dto.setId(4658l);
        instance.setBaseUrl("www.example.com/mquestion");
        Map<String, String> expResult = makeResultWithoutLinksBaseLink(dto.getId());
        
        Map<String, String> result = instance.makeLinks(dto);  
        assertEquals(expResult, result);
    }
    
    @Test
    public void testSetBaseUrlWrong() {
        dto.setId(4658l);
        instance.setBaseUrl("http://www.example/mquestion");
        Map<String, String> expResult = makeResultWithoutLinksBaseLink(dto.getId());
        
        Map<String, String> result = instance.makeLinks(dto);  
        assertEquals(expResult, result);
    }

    @Test
    public void testGetBaseUrlNormal() {
        String expResult = "http://www.example.com/mquestion";
        
        String result = instance.getBaseUrl();        
        assertEquals(expResult, result);
    }
    
    @Test
    public void testGetBaseUrlNull() {
        instance = new GeneralLinkMakerImpl();
        String expResult = "";
        
        String result = instance.getBaseUrl();        
        assertEquals(expResult, result);
    }
    
    private Map<String, String> makeResultLinksNormal(long id) {
        Map<String, String> result = new HashMap<>();
        result.put("self", "http://www.example.com/mquestion/ok/" + id);
        result.put("home", "http://www.example.com/mquestion/ok");        
        return result;
    }
    
    private Map<String, String> makeResultWithoutLinksBaseLink(long id) {
        Map<String, String> result = new HashMap<>();
        result.put("self", "/ok/" + id);
        result.put("home", "/ok");     
        return result;
    }
    
    private Map<String, String> makeResultLinksWithoutWww(long id) {
        Map<String, String> result = new HashMap<>();
        result.put("self", "http://example.com/mquestion/ok/" + id);
        result.put("home", "http://example.com/mquestion/ok");        
        return result;
    }

    public class GeneralLinkMakerImpl extends GeneralLinkMaker<SomeoneDto> {

        @Override
        public boolean isDtoCorrect(SomeoneDto dto) {
            return dto.getId() > 0;
        }

        @Override
        public Map<String, String> makeSelfLink(SomeoneDto dto) {
            Map<String, String> result = new HashMap<>();
            result.put("self", getBaseUrl() + "/ok/" + dto.getId());
            return result;
        }

        @Override
        public Map<String, String> makeAnotherLinks(SomeoneDto dto) {
            Map<String, String> result = new HashMap<>();
            result.put("home", getBaseUrl() + "/ok");
            return result;
        }
    }
    
    public class SomeoneDto {
        
        private long id;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }        
    }
    
}
