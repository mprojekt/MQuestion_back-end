package m.mquestion.utility;

import org.junit.Test;
import static org.junit.Assert.*;


public class PaginationTest {
    
    @Test
    public void testCalculateNumberStartQuestion() {
        assertEquals(0, Pagination.calculateNumberStartQuestion(0));
        assertEquals(25, Pagination.calculateNumberStartQuestion(5));
        
        assertEquals(0, Pagination.calculateNumberStartQuestion(-3));
    }
    
}
