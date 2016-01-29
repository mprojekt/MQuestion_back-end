package m.mquestion.utility;

import java.time.LocalDateTime;
import java.util.*;
import m.mquestion.domain.*;
import m.mquestion.entities.*;
import org.apache.commons.validator.UrlValidator;


public class ResponseMaker {

    private Question question;
    private final String baseUrl;

    public ResponseMaker(String baseUrl) {
        UrlValidator urlValidator = new UrlValidator();
        if(urlValidator.isValid(baseUrl))
            this.baseUrl = baseUrl;
        else
            this.baseUrl = "";
    }
    
    public String getBaseUrl(){
        return baseUrl;
    }
    
    public ResponseQuestionDto makeResponse(Question question, int numberAllQuestion, int numberCurrentPage, int numberLastPage, String jSessionId){
        this.question = question;
        ResponseQuestionDto response = new ResponseQuestionDto(numberAllQuestion, numberCurrentPage, numberLastPage, jSessionId);
        QuestionDto questionDto = convertQuestion(question, jSessionId);
        
        List<QuestionDto> questions = new ArrayList<>();
        questions.add(questionDto);
        response.setConvertedQuestions(questions);
        
        return response;
    }

    private QuestionDto convertQuestion(Question question, String jSessionId) {
        QuestionDto questionDto = new QuestionDto();
        
        questionDto.setId(question.getId());
        questionDto.setTitle(question.getTitle());
        questionDto.setContent(question.getContent());
        questionDto.setNumberAnswer(question.getNumberAnswersToCheck());
        questionDto.setShowResultNow(question.isEnabledShowResultNow());
        
        LocalDateTime ldt = question.getCreateDateTime().toLocalDateTime();
        questionDto.setCreateDate(ldt);
        ldt = question.getEndDateTime().toLocalDateTime();
        questionDto.setEndDate(ldt);        
        
        Type type = question.getTypeQuestion();
        questionDto.setType(type.getName());
        
        List<Answer> answers = question.getThisQuestionAnswers();
        List<AnswerDto> convertedAnswers = new ArrayList<>();
        for (Answer answer : answers) {
            AnswerDto answerDto = new AnswerDto();
            answerDto.setId(answer.getId());
            answerDto.setContent(answer.getContent());
            answerDto.setNumberOfVote(answer.getCurrentVoteNumber());
            answerDto.setRightAnswer(answer.isCorrectAnswer());
            
            Map<String, String> links = new HashMap<>();
            links.put("vote", baseUrl + "/answer/vote/" + answer.getId());
            answerDto.setLinks(links);
            
            convertedAnswers.add(answerDto);
        }
        questionDto.setAnswers(convertedAnswers);
        
        Map<String, String> links = new HashMap<>();
        links.put("self", baseUrl + "/question/" + question.getId());
        questionDto.setLinks(links);
        
        List<Voter> voters = question.getThisQuestionVoter();
        for (Voter voter : voters) {
            if(voter.getjSessionId().equals(jSessionId)){
                questionDto.setAnswered(true);
                break;
            }  
        }
        
        return questionDto;
    }
    
}
