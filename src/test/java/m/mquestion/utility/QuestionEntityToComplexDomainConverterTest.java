package m.mquestion.utility;

import java.util.*;
import m.mquestion.domain.*;
import m.mquestion.entities.*;
import m.mquestion.utility.toTests.QuestionMakerToTests;
import org.junit.*;
import static org.junit.Assert.*;


public class QuestionEntityToComplexDomainConverterTest {
    
    private Question question;
    private List<Question> questions;
    private QuestionEntityToComplexDomainConverter instance;
    
    public QuestionEntityToComplexDomainConverterTest() {
    }
    
    @Before
    public void setUp() {
        question = QuestionMakerToTests.makeQuestionToConvert(64l);
        questions = QuestionMakerToTests.makeQuestionsToConvert();
        instance = new QuestionEntityToComplexDomainConverter("example");
    }

    @Test
    public void testGetBaseUrl() {
        QuestionEntityToComplexDomainConverter c1 = new QuestionEntityToComplexDomainConverter(null);
        assertEquals("", c1.getBaseUrl());
        
        QuestionEntityToComplexDomainConverter c2 = new QuestionEntityToComplexDomainConverter("");
        assertEquals("", c2.getBaseUrl());
        
        QuestionEntityToComplexDomainConverter c3 = new QuestionEntityToComplexDomainConverter("http://www.example.com/mquestion");
        assertEquals("http://www.example.com/mquestion", c3.getBaseUrl());
        
        QuestionEntityToComplexDomainConverter c4 = new QuestionEntityToComplexDomainConverter("http://example.com/mquestion");
        assertEquals("http://example.com/mquestion", c4.getBaseUrl());
        
        QuestionEntityToComplexDomainConverter c5 = new QuestionEntityToComplexDomainConverter("www.example.com/mquestion");
        assertEquals("", c5.getBaseUrl());
        
        QuestionEntityToComplexDomainConverter c6 = new QuestionEntityToComplexDomainConverter("/mquestion");
        assertEquals("", c6.getBaseUrl());
    }
    
    @Test
    public void testConvertRightStructure_Question(){
        ComplexQuestionDto resultTrue = instance.convert(question, "session3");
        ComplexQuestionDto expResultTrue = QuestionMakerToTests.makeExpQuestion(question, true);
        assertEquals(expResultTrue, resultTrue);
        ComplexQuestionDto resultFalse = instance.convert(question, "no_session");
        ComplexQuestionDto expResultFalse = QuestionMakerToTests.makeExpQuestion(question, false);
        assertEquals(expResultFalse, resultFalse);
    }
    
    @Test
    public void testConvertNullQuestion_Question() {  
        question = null;
        try{
            instance.convert(question, "session1");
            fail("Should by IllegalArgumentException, because Question is null");
        } catch(IllegalArgumentException e){
            assertTrue(true);
        }        
    }
    
    @Test
    public void testConvertNewQuestion_Question() {  
        question = new Question();
        try{
            instance.convert(question, "session1");
            fail("Should by IllegalArgumentException, because Question is empty");
        } catch(IllegalArgumentException e){
            assertTrue(true);
        }        
    }
    
    @Test
    public void testConvertNegativIdQuestion_Question() {  
        question.setId(-636l);
        try{
            instance.convert(question, "session1");
            fail("Should by IllegalArgumentException, because Question Id is not positiv");
        } catch(IllegalArgumentException e){
            assertTrue(true);
        }        
    }
    
    @Test
    public void testConvert_List() {
        List<ComplexQuestionDto> expResult = QuestionMakerToTests.makeExpQuestions(questions, true);
        List<ComplexQuestionDto> result = instance.convert(questions, "session1");
        assertEquals(expResult, result);
        List<ComplexQuestionDto> expResultFalse = QuestionMakerToTests.makeExpQuestions(questions, false);
        List<ComplexQuestionDto> resultFalse = instance.convert(questions, "none");
        assertEquals(expResultFalse, resultFalse);
    }
    
    @Test
    public void testConvertNullQuestion_List() {
        questions = null;
        try{
            instance.convert(questions, null);
            fail("Should by IllegalArgumentException, because List Questions is null");
        } catch(IllegalArgumentException e){
            assertTrue(true);
        }        
    }
    
    @Test
    public void testConvertEmptyQuestion_List() {
        questions = new ArrayList<>();
        try{
            instance.convert(questions, null);
            fail("Should by IllegalArgumentException, because List Questions is empty");
        } catch(IllegalArgumentException e){
            assertTrue(true);
        }        
    }
    
    @Test
    public void testConvertWrongOneQuestion_List() {
        question.setId(-194l);
        questions.add(question);
        try{
            instance.convert(questions, null);
            fail("Should by IllegalArgumentException, because List Questions contains wrong one of Questions");
        } catch(IllegalArgumentException e){
            assertTrue(true);
        }        
    }
    
    @Test
    public void testConvertTheSaneTwoQuestion_List() {
        questions.add(QuestionMakerToTests.makeQuestionToConvert(2l));
        try{
            instance.convert(questions, null);
            fail("Should by IllegalArgumentException, because List Questions contains two the same Questions");
        } catch(IllegalArgumentException e){
            assertTrue(true);
        }        
    }
        
}