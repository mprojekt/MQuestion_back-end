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
    
    @Test
    public void testCalculateNumberMaxPage() {
        assertEquals(0, Pagination.calculateNumberMaxPage(0));
        assertEquals(4, Pagination.calculateNumberMaxPage(20));
        assertEquals(8, Pagination.calculateNumberMaxPage(36));
        
        assertEquals(0, Pagination.calculateNumberMaxPage(-6));
    }
    
}
