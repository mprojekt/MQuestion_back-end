package m.mquestion.domain;

import m.mquestion.entities.Question;
import m.mquestion.utility.toTests.QuestionMakerToTests;
import org.junit.*;
import static org.junit.Assert.*;


public class QuestionDtoTest {
    
    Question question;
    QuestionDto aDto, bDto, cDto;
    
    public QuestionDtoTest() {
    }
    
    @Before
    public void setUp() {
        question = QuestionMakerToTests.makeQuestionToConvert(46l);
        aDto = QuestionMakerToTests.makeExpResult(question);
        bDto = QuestionMakerToTests.makeExpResult(question);
        question = QuestionMakerToTests.makeQuestionToConvert(687l);
        cDto = QuestionMakerToTests.makeExpResult(question);
    }
    
    @Test
    public void testHashCode() {
        assertTrue(aDto.hashCode() == aDto.hashCode());
        assertTrue(aDto.hashCode() == bDto.hashCode());
        
        assertFalse(aDto.hashCode() == cDto.hashCode());
        assertFalse(bDto.hashCode() == cDto.hashCode());
    }

    @Test
    public void testEquals() {
        assertTrue(aDto.equals(aDto));
        assertTrue(aDto.equals(bDto));
        
        assertFalse(aDto.equals(cDto));
        assertFalse(bDto.equals(cDto));
    }    
}
