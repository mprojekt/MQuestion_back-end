package m.mquestion.domain;

import m.mquestion.entities.Question;
import m.mquestion.utility.toTests.QuestionMakerToTests;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class ExtendedQuestionDtoTest {
    
    Question question;
    ExtendedQuestionDto aDto, bDto, cDto;
    
    public ExtendedQuestionDtoTest() {
    }
    
    @Before
    public void setUp() {
        question = QuestionMakerToTests.makeQuestionToConvert(2l);
        aDto = QuestionMakerToTests.makeExpQuestion(question, true);
        bDto = QuestionMakerToTests.makeExpQuestion(question, true);
        question = QuestionMakerToTests.makeQuestionToConvert(845l);
        cDto = QuestionMakerToTests.makeExpQuestion(question, false);
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
