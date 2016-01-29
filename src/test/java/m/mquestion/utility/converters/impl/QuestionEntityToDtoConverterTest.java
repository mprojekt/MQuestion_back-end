package m.mquestion.utility.converters.impl;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import m.mquestion.domain.AnswerDto;
import m.mquestion.domain.QuestionDto;
import m.mquestion.entities.Answer;
import m.mquestion.entities.Question;
import m.mquestion.entities.Type;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class QuestionEntityToDtoConverterTest {
    
    QuestionEntityToDtoConverter instance;
    Question question;    
    QuestionDto expResult;
    Type type;
    List<Answer> answers;
    List<AnswerDto> expAnswers;
    LocalDateTime dateTime;
                
    public QuestionEntityToDtoConverterTest() {
        type = new Type("testType");
        answers = new ArrayList<>();
        expAnswers = new ArrayList<>();
        createAnswersLists();
    }
    
    @Before
    public void setUp() {
        instance = new QuestionEntityToDtoConverter();
        dateTime = LocalDateTime.now();
    }

    @Test
    public void testConvertNormalQuestion() {
        question = makeNormalQuestion();
        expResult = makeExpectedNormalResult();
        
        QuestionDto result = instance.convert(question);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testConvertIncompleteQuestion() {
        question = makeIncompleteQuestion();
        expResult = makeExpectedIncompleteResult();
        
        QuestionDto result = instance.convert(question);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testConvertNullQuestion() {
        question = null;
        
        try {
            instance.convert(question);
            fail("Should be IllegalArgumentException, because Question not exist");
        } catch (IllegalArgumentException iae){
            assertTrue(true);
        }
    }
    
    @Test
    public void testConvertEmptyQuestion() {
        question = new Question();
        
        try {
            instance.convert(question);
            fail("Should be IllegalArgumentException, because Question Id not existed");
        } catch (IllegalArgumentException iae){
            assertTrue(true);
        }
    }
    
    @Test
    public void testConvertWrongQuestion() {
        question =  makeWrongQuestion();
        
        try {
            instance.convert(question);
            fail("Should be IllegalArgumentException, bacause Question Id is negativ number");
        } catch (IllegalArgumentException iae){
            assertTrue(true);
        }
    }

    private void createAnswersLists() {      
        for (int i = 0; i < 4; i++) {
            Answer answer = new Answer("lorem ipsum");
            answer.setId(10l + i);            
            
            AnswerDto answerDto = new AnswerDto();
            answerDto.setId(10l + i);
            answerDto.setContent("lorem ipsum");
            
            answers.add(answer);
            expAnswers.add(answerDto);
        }
    }

    private Question makeNormalQuestion() { 
        Question result = new Question();
        
        result.setId(5l);
        result.setTitle("title");
        result.setContent("lorem ipsum");
        result.setCreateDateTime(Timestamp.valueOf(dateTime));
        result.setEndDateTime(Timestamp.valueOf(dateTime));
        result.setIsEnabledShowResultNow(true);
        result.setNumberAnswersToCheck(2);
        result.setTypeQuestion(type);
        result.setThisQuestionAnswers(answers);
        return result;
    }
    
    private QuestionDto makeExpectedNormalResult() {        
        QuestionDto result = new QuestionDto();
        
        result.setId(5l);
        result.setTitle("title");
        result.setContent("lorem ipsum");
        result.setCreateDate(dateTime);
        result.setEndDate(dateTime);
        result.setShowResultNow(true);
        result.setNumberAnswer(2);
        result.setType("testType");
        result.setAnswers(expAnswers);
        return result;
    }
    
    private Question makeIncompleteQuestion() { 
        Question result = new Question();
        
        result.setId(50l);
        return result;
    }
    
    private QuestionDto makeExpectedIncompleteResult() {        
        QuestionDto result = new QuestionDto();
        
        result.setId(50l);
        return result;
    }
    
    private Question makeWrongQuestion() { 
        Question result = new Question();
        
        result.setId(-450l);
        return result;
    }

    
}
