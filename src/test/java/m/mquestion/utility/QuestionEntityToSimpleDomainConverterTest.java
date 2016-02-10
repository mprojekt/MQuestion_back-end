package m.mquestion.utility;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import m.mquestion.domain.*;
import m.mquestion.entities.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

public class QuestionEntityToSimpleDomainConverterTest {
    
    Question question;
    List<Question> questions;
    QuestionEntityToSimpleDomainConverter instance;
    
    public QuestionEntityToSimpleDomainConverterTest() {
    }
    
    @Before
    public void setUp() {
        question = makeQuestionToConvert(64l);
        questions = makeQuestionsToConvert();
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
        SimpleQuestionDto expResult = makeExpResult(question);
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
        List<SimpleQuestionDto> expResult = makeExpResult(questions);
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
        questions.add(makeQuestionToConvert(2l));
        try{
            instance.convert(questions);
            fail("Should by IllegalArgumentException, because List Questions contains two the same Questions");
        } catch(IllegalArgumentException e){
            assertTrue(true);
        }        
    }
    
    private Question makeQuestionToConvert(long id) {
        Question result = new Question();
        result.setId(id);
        result.setTitle("Title");
        result.setContent("Lorem Ipsum");
        
        Type type = new Type("test");
        result.setTypeQuestion(type);
        
        LocalDateTime dateTimeNow = LocalDateTime.now();
        result.setCreateDateTime(Timestamp.valueOf(dateTimeNow));
        
        LocalDateTime dateTimeEnd= dateTimeNow.plusDays(14);
        result.setEndDateTime(Timestamp.valueOf(dateTimeEnd));        
        
        return result;
    }

    private List<Question> makeQuestionsToConvert() {
        List<Question> result = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            Question q = makeQuestionToConvert((long)i);
            result.add(q);
        }
        
        return result;
    }
    
    private SimpleQuestionDto makeExpResult(Question question) {
        SimpleQuestionDto result = new SimpleQuestionDto();
        result.setId(question.getId());
        result.setTitle(question.getTitle());
        result.setContent(question.getContent());
        result.setEndDate(question.getEndDateTime().toLocalDateTime());
        
        Type type = question.getTypeQuestion();
        result.setType(type.getName());
        
        Map<String, String> links = new HashMap<>();
        links.put("self", "/question/" + question.getId());
        result.setLinks(links);
        
        return result;
    }

    private List<SimpleQuestionDto> makeExpResult(List<Question> questions){
        List<SimpleQuestionDto> result = new ArrayList<>();
        for (Question q : questions) {
            SimpleQuestionDto simple = makeExpResult(q);
            result.add(simple);
        }
        
        return result;
    }
    
}
