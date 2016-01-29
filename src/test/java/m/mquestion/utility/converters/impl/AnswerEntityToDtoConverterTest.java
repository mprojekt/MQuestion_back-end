package m.mquestion.utility.converters.impl;

import m.mquestion.domain.AnswerDto;
import m.mquestion.entities.Answer;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class AnswerEntityToDtoConverterTest {
    
    private AnswerEntityToDtoConverter instance;
    private Answer answer;
    private AnswerDto expResult;
    
    public AnswerEntityToDtoConverterTest() {
    }
    
    @Before
    public void setUp() {
        instance = new AnswerEntityToDtoConverter();
    }
    
    @Test
    public void testConvertNormalAnswer() {
        answer = makeNormalAnswer();        
        expResult = makeExpectedNormalResult();
        
        AnswerDto result = instance.convert(answer);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testConvertIncompleteAnswer() {
        answer = makeIncompleteAnswer();        
        expResult = makeExpectedIncompleteResult();
        
        AnswerDto result = instance.convert(answer);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testConvertNullAnswer() {
        answer = null;
        
        try {
            instance.convert(answer);
            fail("Should be IllegalArgumentException, because Answer not exist");
        } catch (IllegalArgumentException iae){
            assertTrue(true);
        }
    }
    
    @Test
    public void testConvertEmptyAnswer() {
        answer = new Answer();
        
        try {
            instance.convert(answer);
            fail("Should be IllegalArgumentException, because Answer Id not existed");
        } catch (IllegalArgumentException iae){
            assertTrue(true);
        }
    }
    
    @Test
    public void testConvertWrongAnswer() {
        answer =  makeWrongAnswer();
        
        try {
            instance.convert(answer);
            fail("Should be IllegalArgumentException, bacause Answer Id is negativ number");
        } catch (IllegalArgumentException iae){
            assertTrue(true);
        }
    }
    
    private Answer makeNormalAnswer(){
        Answer result = new Answer("lorem ipsum");
        result.setId(4l);
        result.setCorrectAnswer(true);
        result.setCurrentVoteNumber(5);
        return result;
    }
    
    private AnswerDto makeExpectedNormalResult(){
        AnswerDto result = new AnswerDto(4l, "lorem ipsum", true, 5, null);
        return result;
    }
    
    private Answer makeIncompleteAnswer() {
        Answer result = new Answer();
        result.setId(15l);
        return result;
    }    
    
    private AnswerDto makeExpectedIncompleteResult(){
        AnswerDto result = new AnswerDto();
        result.setId(15l);
        return result;
    }
    
    private Answer makeWrongAnswer(){
        Answer result = new Answer("lorem ipsum");
        result.setId(-1l);
        return result;
    }
    
}
