package m.mquestion.utility;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;
import m.mquestion.domain.*;
import m.mquestion.entities.*;
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
        question = makeQuestionToConvert(64l);
        questions = makeQuestionsToConvert();
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
        ComplexQuestionDto expResultTrue = makeExpQuestion(question, true);
        assertEquals(expResultTrue, resultTrue);
        ComplexQuestionDto resultFalse = instance.convert(question, "no_session");
        ComplexQuestionDto expResultFalse = makeExpQuestion(question, false);
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
        List<ComplexQuestionDto> expResult = makeExpQuestions(questions, true);
        List<ComplexQuestionDto> result = instance.convert(questions, "session1");
        assertEquals(expResult, result);
        List<ComplexQuestionDto> expResultFalse = makeExpQuestions(questions, false);
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
        questions.add(makeQuestionToConvert(2l));
        try{
            instance.convert(questions, null);
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
        result.setEnabledShowResultNow(true);
        result.setEnabledNotificationAfterEnd(true);
        result.setNumberAnswersToCheck(1);
        
        Type type = new Type("test");
        result.setTypeQuestion(type);
        
        LocalDateTime dateTimeNow = LocalDateTime.now();
        result.setCreateDateTime(Timestamp.valueOf(dateTimeNow));
        
        LocalDateTime dateTimeEnd= dateTimeNow.plusDays(14);
        result.setEndDateTime(Timestamp.valueOf(dateTimeEnd));
        
        List<Answer> answers = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            Answer answer = new Answer();
            answer.setId((long)i);
            answer.setContent("Lorem Ipsum");
            answer.setCurrentVoteNumber(i);
            answers.add(answer);
        }
        result.setThisQuestionAnswers(answers);
        
        List<Voter> voters = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            Voter voter = new Voter();
            voter.setjSessionId("session" + i);
            voters.add(voter);
        }
        result.setThisQuestionVoter(voters);
        
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
    
    private ComplexQuestionDto makeExpQuestion(Question question, boolean voted){
        ComplexQuestionDto result = new ComplexQuestionDto();
        result.setId(question.getId());
        result.setTitle(question.getTitle());
        result.setContent(question.getContent());
        result.setCreateDate(question.getCreateDateTime().toLocalDateTime());
        result.setEndDate(question.getEndDateTime().toLocalDateTime());
        result.setNumberAnswerToCheck(question.getNumberAnswersToCheck());
        result.setShowResultNow(question.isEnabledShowResultNow());
                
        Type type = question.getTypeQuestion();
        result.setType(type.getName());
        
        List<AnswerDto> answers = new ArrayList<>();
        for (Answer sourceAnswer : question.getThisQuestionAnswers()) {
            AnswerDto answer = new AnswerDto();
            answer.setId(sourceAnswer.getId());
            answer.setContent(sourceAnswer.getContent());
            answer.setNumberOfVote(sourceAnswer.getCurrentVoteNumber());
            
            Map<String, String> links = new HashMap<>();
            links.put("vote", "/question/vote/" + question.getId() + "." + sourceAnswer.getId());
            answer.setLinks(links);
            
            answers.add(answer);
        }
        result.setAnswers(answers);
        
        result.setAnswered(voted);
        
        Map<String, String> links = new HashMap<>();
        links.put("self", "/question/" + question.getId());
        result.setLinks(links);
        
        return result;
    }
    
    private List<ComplexQuestionDto> makeExpQuestions(List<Question> questions, boolean voted){
        List<ComplexQuestionDto> result = new ArrayList<>();
        for (Question q : questions) {
            ComplexQuestionDto complex = makeExpQuestion(q, voted);
            result.add(complex);
        }
        
        return result;        
    }
    
}
