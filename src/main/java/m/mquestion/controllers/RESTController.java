package m.mquestion.controllers;

import java.util.*;
import m.mquestion.domain.*;
import m.mquestion.services.AccessToDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RESTController {
    
    @Autowired
    private AccessToDataService accessToDataService;
    
    @RequestMapping("/")
    public String start() { 
        return "start";
    }
    
    @RequestMapping("/question/{id}")
    public QuestionDto getActiveQuestion(@PathVariable long id) { 
        return accessToDataService.getQuestion(id, "a");
    }
    
    @RequestMapping("/simple/{id}")
    public QuestionDto getPreviewQuestion(@PathVariable long id) { 
        return accessToDataService.getQuestion(id);
    }
    
    @RequestMapping("/list/{page}")
    public List<QuestionDto> getPreviewQuestions(@PathVariable int page) { 
        return accessToDataService.getQuestionsToPreviewByPage(page);
    }
//    @RequestMapping("/test/{page}")
//    public List<Question> startTest(@PathVariable int page, HttpServletResponse response) { 
//        response.setContentType("application/json;charset=UTF-8");
//        return accessToDataService.getTest(page);        
//    }

}
