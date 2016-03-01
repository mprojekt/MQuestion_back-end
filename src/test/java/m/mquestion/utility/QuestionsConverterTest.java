package m.mquestion.utility;

import java.util.*;
import m.mquestion.domain.*;
import m.mquestion.entities.*;
import m.mquestion.utility.toTests.QuestionMakerToTests;
import org.junit.*;
import static org.junit.Assert.*;


public class QuestionsConverterTest {
    
    private Question question;
    private List<Question> questions;
    private QuestionsConverter instance;
    
    @Before
    public void setUp() {
        question = QuestionMakerToTests.makeQuestionToConvert(64l);
        questions = QuestionMakerToTests.makeQuestionsToConvert();
        instance = new QuestionsConverter("example");
    }
    
    @Test
    public void testConvert() {
        List<QuestionDto> expResult = QuestionMakerToTests.makeExpResult(questions);
        List<QuestionDto> result = instance.convert(questions);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testConvertNullQuestion() {
        questions = null;
        try{
            instance.convert(questions);
            fail("Should by IllegalArgumentException, because List Questions is null");
        } catch(IllegalArgumentException e){
            assertTrue(true);
        }        
    }
    
    @Test
    public void testConvertEmptyQuestion() {
        questions = new ArrayList<>();
        try{
            instance.convert(questions);
            fail("Should by IllegalArgumentException, because List Questions is empty");
        } catch(IllegalArgumentException e){
            assertTrue(true);
        }        
    }
    
    @Test
    public void testConvertWrongOneQuestion() {
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
    public void testConvertTheSameTwoQuestion() {
        questions.add(QuestionMakerToTests.makeQuestionToConvert(2l));
        try{
            instance.convert(questions);
            fail("Should by IllegalArgumentException, because List Questions contains two the same Questions");
        } catch(IllegalArgumentException e){
            assertTrue(true);
        }        
    }
    
    @Test
    public void testConvert_Extended() {
        List<ExtendedQuestionDto> expResult = QuestionMakerToTests.makeExpQuestions(questions, true);
        List<ExtendedQuestionDto> result = instance.convert(questions, "session1");
        assertEquals(expResult, result);
        List<ExtendedQuestionDto> expResultFalse = QuestionMakerToTests.makeExpQuestions(questions, false);
        List<ExtendedQuestionDto> resultFalse = instance.convert(questions, "none");
        assertEquals(expResultFalse, resultFalse);
    }
    
    @Test
    public void testConvertNullQuestion_Extended() {
        questions = null;
        try{
            instance.convert(questions, null);
            fail("Should by IllegalArgumentException, because List Questions is null");
        } catch(IllegalArgumentException e){
            assertTrue(true);
        }        
    }
    
    @Test
    public void testConvertEmptyQuestion_Extended() {
        questions = new ArrayList<>();
        try{
            instance.convert(questions, null);
            fail("Should by IllegalArgumentException, because List Questions is empty");
        } catch(IllegalArgumentException e){
            assertTrue(true);
        }        
    }
    
    @Test
    public void testConvertWrongOneQuestion_Extended() {
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
    public void testConvertTheSaneTwoQuestion_Extended() {
        questions.add(QuestionMakerToTests.makeQuestionToConvert(2l));
        try{
            instance.convert(questions, null);
            fail("Should by IllegalArgumentException, because List Questions contains two the same Questions");
        } catch(IllegalArgumentException e){
            assertTrue(true);
        }        
    }
        
}