package m.mquestion.controllers;

import java.util.*;
import m.mquestion.domain.*;
import m.mquestion.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RESTController {
    
    @Autowired
    private AccessToDataService accessToDataService;
    @Autowired
    private StatisticService statisticService;
        
    @RequestMapping("/question/{id}")
    public QuestionDto getActiveQuestion(@PathVariable long id) { 
        return accessToDataService.getQuestion(id, "a");
    }
    
    @RequestMapping("/simple/{id}")
    public QuestionDto getPreviewQuestion(@PathVariable long id) { 
        return accessToDataService.getQuestion(id);
    }
    
    @RequestMapping("/vote/{page}")
    public ResponseEntity<?> getVoteQuestions(@PathVariable int page) { 
        List<QuestionDto> result;
        try{
            result = accessToDataService.getQuestionsToVoteByPage(page);
        } catch(IllegalArgumentException iae){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        return ResponseEntity.ok(result);
    }
    
    @RequestMapping("/result/{page}")
    public ResponseEntity<?> getResultQuestions(@PathVariable int page) {
        List<QuestionDto> result;
        try{
            result = accessToDataService.getQuestionsToResultByPage(page);
        } catch(IllegalArgumentException iae){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        return ResponseEntity.ok(result);
    }
    
    @RequestMapping("/sibling/{id}")
    public Map getSiblingQuestion(@PathVariable long id){
        return accessToDataService.getSiblingQuestions(id);
    }
    
    @RequestMapping("/statistic")
    public StatisticQuestion getStatistic(){
        return statisticService.getStatistic();
    }

}
