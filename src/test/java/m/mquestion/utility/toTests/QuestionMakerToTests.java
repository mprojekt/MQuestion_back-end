package m.mquestion.utility.toTests;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;
import m.mquestion.domain.*;
import m.mquestion.entities.*;


public class QuestionMakerToTests {

    public static Question makeQuestionToConvert(long id) {
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
    
       
    public static List<Question> makeQuestionsToConvert() {
        List<Question> result = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            Question q = QuestionMakerToTests.makeQuestionToConvert((long)i);
            result.add(q);
        }
        
        return result;
    }
    
    public static ComplexQuestionDto makeExpQuestion(Question question, boolean voted){
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
    
    public static List<ComplexQuestionDto> makeExpQuestions(List<Question> questions, boolean voted){
        List<ComplexQuestionDto> result = new ArrayList<>();
        for (Question q : questions) {
            ComplexQuestionDto complex = makeExpQuestion(q, voted);
            result.add(complex);
        }
        
        return result;        
    }
    
    public static SimpleQuestionDto makeExpResult(Question question) {
        SimpleQuestionDto result = new SimpleQuestionDto();
        result.setId(question.getId());
        result.setTitle(question.getTitle());
        result.setContent(question.getContent());
        result.setEndDate(question.getEndDateTime().toLocalDateTime());
        
        Type type = question.getTypeQuestion();
        result.setType(type.getName());
        
        Map<String, String> links = new HashMap<>();
        links.put("self", "/question/" + question.getId());
        result.setLinks(links);
        
        return result;
    }

    public static List<SimpleQuestionDto> makeExpResult(List<Question> questions){
        List<SimpleQuestionDto> result = new ArrayList<>();
        for (Question q : questions) {
            SimpleQuestionDto simple = makeExpResult(q);
            result.add(simple);
        }
        
        return result;
    }
}
