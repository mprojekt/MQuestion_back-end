package m.mquestion.utility;


public class Pagination {
    
    public static final int MAX_PAGE_ON_PAGE = 5;
    
    public static int calculateNumberStartQuestion(int page){
        if(page < 0)
            return 0;
        return page * MAX_PAGE_ON_PAGE;
    }
    
    public static int calculateNumberMaxPage(long number){
        if(number < 0)
            return 0;
         
        int excess = ((number % MAX_PAGE_ON_PAGE) > 0) ? 1: 0;
        int result = (int)(number / MAX_PAGE_ON_PAGE) + excess;
        
        return result;
    }

}
