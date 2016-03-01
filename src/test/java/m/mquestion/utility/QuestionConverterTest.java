package m.mquestion.utility;

import m.mquestion.domain.*;
import m.mquestion.entities.*;
import m.mquestion.utility.toTests.QuestionMakerToTests;
import org.junit.*;
import static org.junit.Assert.*;

public class QuestionConverterTest {
    
    private Question question;
    private QuestionConverter instance;
    
    @Before
    public void setUp() {
        question = QuestionMakerToTests.makeQuestionToConvert(64l);
        instance = new QuestionConverter("example");        
    }

    @Test
    public void testGetBaseUrl() {
        QuestionConverter c1 = new QuestionConverter(null);
        assertEquals("", c1.getBaseUrl());
        
        QuestionConverter c2 = new QuestionConverter("");
        assertEquals("", c2.getBaseUrl());
        
        QuestionConverter c3 = new QuestionConverter("http://www.example.com/mquestion");
        assertEquals("http://www.example.com/mquestion", c3.getBaseUrl());
        
        QuestionConverter c4 = new QuestionConverter("http://example.com/mquestion");
        assertEquals("http://example.com/mquestion", c4.getBaseUrl());
        
        QuestionConverter c5 = new QuestionConverter("www.example.com/mquestion");
        assertEquals("", c5.getBaseUrl());
        
        QuestionConverter c6 = new QuestionConverter("/mquestion");
        assertEquals("", c6.getBaseUrl());
    }
    
    @Test
    public void testConvertRightStructure() {        
        QuestionDto expResult = QuestionMakerToTests.makeExpResult(question);
        QuestionDto result = instance.convert(question);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testConvertNullQuestion() {  
        question = null;
        try{
            instance.convert(question);
            fail("Should by IllegalArgumentException, because Question is null");
        } catch(IllegalArgumentException e){
            assertTrue(true);
        }        
    }
    
    @Test
    public void testConvertNewQuestion() {  
        question = new Question();
        try{
            instance.convert(question);
            fail("Should by IllegalArgumentException, because Question have not ID");
        } catch(IllegalArgumentException e){
            assertTrue(true);
        }        
    }
    
    @Test
    public void testConvertNegativQuestion() {  
        question.setId(-34l);
        try{
            instance.convert(question);
            fail("Should by IllegalArgumentException, because Question's ID is negativ");
        } catch(IllegalArgumentException e){
            assertTrue(true);
        }        
    }
    
    @Test
    public void testConvertRightStructure_Extended(){
        ExtendedQuestionDto resultTrue = instance.convert(question, "session3");
        ExtendedQuestionDto expResultTrue = QuestionMakerToTests.makeExpQuestion(question, true);
        assertEquals(expResultTrue, resultTrue);
        ExtendedQuestionDto resultFalse = instance.convert(question, "no_session");
        ExtendedQuestionDto expResultFalse = QuestionMakerToTests.makeExpQuestion(question, false);
        assertEquals(expResultFalse, resultFalse);
    }
    
    @Test
    public void testConvertNullQuestion_Extended() {  
        question = null;
        try{
            instance.convert(question, "session1");
            fail("Should by IllegalArgumentException, because Question is null");
        } catch(IllegalArgumentException e){
            assertTrue(true);
        }        
    }
    
    @Test
    public void testConvertNewQuestion_Extended() {  
        question = new Question();
        try{
            instance.convert(question, "session1");
            fail("Should by IllegalArgumentException, because Question is empty");
        } catch(IllegalArgumentException e){
            assertTrue(true);
        }        
    }
    
    @Test
    public void testConvertNegativIdQuestion_Extended() {  
        question.setId(-636l);
        try{
            instance.convert(question, "session1");
            fail("Should by IllegalArgumentException, because Question Id is not positiv");
        } catch(IllegalArgumentException e){
            assertTrue(true);
        }        
    }
    
}