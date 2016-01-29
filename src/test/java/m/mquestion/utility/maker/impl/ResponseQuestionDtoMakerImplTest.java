package m.mquestion.utility.maker.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import m.mquestion.domain.AnswerDto;
import m.mquestion.domain.QuestionDto;
import m.mquestion.domain.ResponseQuestionDto;
import m.mquestion.entities.Answer;
import m.mquestion.entities.Question;
import m.mquestion.entities.Voter;
import m.mquestion.utility.data.QuestionSibling;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

public class ResponseQuestionDtoMakerImplTest {
    
    private ResponseQuestionDtoMakerImpl instance;
    
    public ResponseQuestionDtoMakerImplTest() {
    }
    
    @Before
    public void setUp() {
        instance = new ResponseQuestionDtoMakerImpl("baseUrl", "m45", 100, 5, 10);
    }
    
    @Test
    public void testSetBaseUrl() {
        instance = new ResponseQuestionDtoMakerImpl("http://www.example.com", "jsession", 0, 0, 0);
        assertEquals("http://www.example.com", instance.getBaseUrl());
        instance = new ResponseQuestionDtoMakerImpl("http://example.com", "jsession", 0, 0, 0);
        assertEquals("http://example.com", instance.getBaseUrl());
        instance = new ResponseQuestionDtoMakerImpl("www.example.com", "jsession", 0, 0, 0);
        assertEquals("", instance.getBaseUrl());
        instance = new ResponseQuestionDtoMakerImpl("lorem ipsum", "jsession", 0, 0, 0);
        assertEquals("", instance.getBaseUrl());
        instance = new ResponseQuestionDtoMakerImpl("", "jsession", 0, 0, 0);
        assertEquals("", instance.getBaseUrl());
        instance = new ResponseQuestionDtoMakerImpl(null, "jsession", 0, 0, 0);
        assertEquals("", instance.getBaseUrl());
    }
    
    @Test
    public void testSetNumbersStatisticNormal() {
        try {
            instance = new ResponseQuestionDtoMakerImpl("url", "jsession", 200, 14, 53);
            instance = new ResponseQuestionDtoMakerImpl("url", "jsession", 0, 0, 0);
            assertTrue(true);
        } catch (IllegalArgumentException iae){
            fail("Should not be IllegalArgumentException, because numbers are ok.");
        }
    }
    
    @Test
    public void testSetNumbersStatisticNegativ() {        
        try {
            instance = new ResponseQuestionDtoMakerImpl("url", "jsession", -45, 5, 9);
            fail("Should be IllegalArgumentException, because argument All Question is negativ");
        } catch (IllegalArgumentException iae){
            assertTrue(true);
        }
        
        try {
            instance = new ResponseQuestionDtoMakerImpl("url", "jsession", 500, -5, 10);
            fail("Should be IllegalArgumentException, because argument Current Page is negativ");
        } catch (IllegalArgumentException iae){
            assertTrue(true);
        }        
        
        try {
            instance = new ResponseQuestionDtoMakerImpl("url", "jsession", 100, 5, -20);
            fail("Should be IllegalArgumentException, because argument Last Page is negativ");
        } catch (IllegalArgumentException iae){
            assertTrue(true);
        }
        
        try {
            instance = new ResponseQuestionDtoMakerImpl("url", "jsession", -100, -1, -2);
            fail("Should be IllegalArgumentException, because argument Last Page is negativ");
        } catch (IllegalArgumentException iae){
            assertTrue(true);
        }
    }
    
    @Test
    public void testSetNumbersStatisticDepending() {        
        try {
            instance = new ResponseQuestionDtoMakerImpl("url", "jsession", 4, 5, 2);
            fail("Should be IllegalArgumentException, because argument Number Current Page is biger than Number All Question");
        } catch (IllegalArgumentException iae){
            assertTrue(true);
        }
        
        try {
            instance = new ResponseQuestionDtoMakerImpl("url", "jsession", 100, 50, 20);
            fail("Should be IllegalArgumentException, because argument Number Current Page is biger than Number Last Page");
        } catch (IllegalArgumentException iae){
            assertTrue(true);
        }
        
        try {
            instance = new ResponseQuestionDtoMakerImpl("url", "jsession", 10, 5, 20);
            fail("Should be IllegalArgumentException, because argument Number Last Page is biger than Number All Question");
        } catch (IllegalArgumentException iae){
            assertTrue(true);
        }
    }

    @Test
    public void testMakeNormal_Question_QuestionSibling() { 
        Question question = makeEntity(4l, 3, "m45");
        QuestionSibling sibling = makeSibling(4l, 3l, 5l);
        ResponseQuestionDto expResult = makeExpResult("m45", 3, true, sibling);
        
        ResponseQuestionDto result = instance.make(question, sibling);
        assertEquals(expResult, result);
    }

    @Ignore
    @Test
    public void testMake_List() {
        System.out.println("make");
        List<Question> listEntity = null;
        
        ResponseQuestionDto expResult = null;
        ResponseQuestionDto result = instance.make(listEntity);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    private Question makeEntity(long id, int numberAnswer, String jSessionId) {
        Question result = new Question();
        result.setId(id);
        result.setTitle("Lorem Ipsum");
        result.setContent("Lorem Ipsum");
        
        List<Answer> answers = new ArrayList<>();
        for (int i = 1; i <= numberAnswer; i++) {
            Answer answer = new Answer();
            answer.setId((long)i);
            answers.add(answer);
        }
        result.setThisQuestionAnswers(answers);
        
        List<Voter> voters = new ArrayList<>();
        Voter voter = new Voter();
        voter.setjSessionId(jSessionId);
        voters.add(voter);
        result.setThisQuestionVoter(voters);
        
        return result;
    }

    private QuestionSibling makeSibling(long actual, long prev, long next) {
        QuestionSibling result = new QuestionSibling(actual, prev, next);
        return result;
    }

    private ResponseQuestionDto makeExpResult(String jSessionId, int numberAnswers, boolean answered, QuestionSibling sibling) {
        ResponseQuestionDto result = new ResponseQuestionDto();
        result.setNumberCurrentPage(5);
        result.setNumberLastPage(10);
        result.setNumberAllQuestion(100);
        result.setjSessionID(jSessionId);
        List<QuestionDto> questions = makeExpListQuestionDto(numberAnswers, answered, sibling);
        result.setQuestions(questions);        
        
        return result;
    }

    private List<QuestionDto> makeExpListQuestionDto(int numberAnswers, boolean answered, QuestionSibling sibling) {
        List<QuestionDto> result = new ArrayList<>();
        QuestionDto questionDto = new QuestionDto();
        questionDto.setId(sibling.getThisQuestionId());
        questionDto.setAnswered(answered);
        questionDto.setTitle("Lorem Ipsum");
        questionDto.setContent("Lorem Ipsum");
        
        List<AnswerDto> answers = new ArrayList<>();
        for (int i = 1; i <= numberAnswers; i++) {
            AnswerDto answer = new AnswerDto();
            answer.setId((long)i);
            
            Map<String, String> links = new HashMap<>();
            links.put("self", "/answer/" + (long)i);
            links.put("vote", "/answer/vote/" + (long)i);
            answer.setLinks(links);
            
            answers.add(answer);
        }
        questionDto.setAnswers(answers);
        
        Map<String, String> links = new HashMap<>();
        links.put("self", "/question/" + sibling.getThisQuestionId());
        links.put("prev", "/question/" + sibling.getPrevQuestionId());
        links.put("next", "/question/" + sibling.getNextQuestionId());
        links.put("home", "/");
        questionDto.setLinks(links);
        
        result.add(questionDto);
        return result;
    }
    
}
