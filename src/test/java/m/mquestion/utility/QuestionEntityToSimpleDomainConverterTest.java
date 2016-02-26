package m.mquestion.utility;

import java.util.*;
import m.mquestion.domain.*;
import m.mquestion.entities.*;
import m.mquestion.utility.toTests.QuestionMakerToTests;
import org.junit.*;
import static org.junit.Assert.*;

public class QuestionEntityToSimpleDomainConverterTest {
    
    private Question question;
    private List<Question> questions;
    private QuestionEntityToSimpleDomainConverter instance;
    
    public QuestionEntityToSimpleDomainConverterTest() {
    }
    
    @Before
    public void setUp() {
        question = QuestionMakerToTests.makeQuestionToConvert(64l);
        questions = QuestionMakerToTests.makeQuestionsToConvert();
        instance = new QuestionEntityToSimpleDomainConverter("example");        
    }

    @Test
    public void testGetBaseUrl() {
        QuestionEntityToSimpleDomainConverter c1 = new QuestionEntityToSimpleDomainConverter(null);
        assertEquals("", c1.getBaseUrl());
        
        QuestionEntityToSimpleDomainConverter c2 = new QuestionEntityToSimpleDomainConverter("");
        assertEquals("", c2.getBaseUrl());
        
        QuestionEntityToSimpleDomainConverter c3 = new QuestionEntityToSimpleDomainConverter("http://www.example.com/mquestion");
        assertEquals("http://www.example.com/mquestion", c3.getBaseUrl());
        
        QuestionEntityToSimpleDomainConverter c4 = new QuestionEntityToSimpleDomainConverter("http://example.com/mquestion");
        assertEquals("http://example.com/mquestion", c4.getBaseUrl());
        
        QuestionEntityToSimpleDomainConverter c5 = new QuestionEntityToSimpleDomainConverter("www.example.com/mquestion");
        assertEquals("", c5.getBaseUrl());
        
        QuestionEntityToSimpleDomainConverter c6 = new QuestionEntityToSimpleDomainConverter("/mquestion");
        assertEquals("", c6.getBaseUrl());
    }
    
    @Test
    public void testConvertRightStructure_Question() {        
        SimpleQuestionDto expResult = QuestionMakerToTests.makeExpResult(question);
        SimpleQuestionDto result = instance.convert(question);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testConvertNullQuestion_Question() {  
        question = null;
        try{
            instance.convert(question);
            fail("Should by IllegalArgumentException, because Question is null");
        } catch(IllegalArgumentException e){
            assertTrue(true);
        }        
    }
    
    @Test
    public void testConvertNewQuestion_Question() {  
        question = new Question();
        try{
            instance.convert(question);
            fail("Should by IllegalArgumentException, because Question have not ID");
        } catch(IllegalArgumentException e){
            assertTrue(true);
        }        
    }
    
    @Test
    public void testConvertNegativQuestion_Question() {  
        question.setId(-34l);
        try{
            instance.convert(question);
            fail("Should by IllegalArgumentException, because Question's ID is negativ");
        } catch(IllegalArgumentException e){
            assertTrue(true);
        }        
    }

    @Test
    public void testConvert_List() {
        List<SimpleQuestionDto> expResult = QuestionMakerToTests.makeExpResult(questions);
        List<SimpleQuestionDto> result = instance.convert(questions);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testConvertNullQuestion_List() {
        questions = null;
        try{
            instance.convert(questions);
            fail("Should by IllegalArgumentException, because List Questions is null");
        } catch(IllegalArgumentException e){
            assertTrue(true);
        }        
    }
    
    @Test
    public void testConvertEmptyQuestion_List() {
        questions = new ArrayList<>();
        try{
            instance.convert(questions);
            fail("Should by IllegalArgumentException, because List Questions is empty");
        } catch(IllegalArgumentException e){
            assertTrue(true);
        }        
    }
    
    @Test
    public void testConvertWrongOneQuestion_List() {
        question.setId(-3636l);
        questions.add(question);
        try{
            instance.convert(questions);
            fail("Should by IllegalArgumentException, because List Questions contains wrong one of Questions");
        } catch(IllegalArgumentException e){
            assertTrue(true);
        }        
    }    
    
    @Test
    public void testConvertTheSaneTwoQuestion_List() {
        questions.add(QuestionMakerToTests.makeQuestionToConvert(2l));
        try{
            instance.convert(questions);
            fail("Should by IllegalArgumentException, because List Questions contains two the same Questions");
        } catch(IllegalArgumentException e){
            assertTrue(true);
        }        
    }
    
}