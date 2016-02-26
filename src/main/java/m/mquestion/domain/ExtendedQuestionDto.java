package m.mquestion.domain;

import java.time.LocalDateTime;
import java.util.*;


public class ExtendedQuestionDto extends QuestionDto{

    private LocalDateTime createDateTime;
    private boolean showResultNow;
    private boolean answered;
    private int numberAnswerToCheck;
    private List<AnswerDto> answers;

    public ExtendedQuestionDto() {
    }

    public ExtendedQuestionDto(long id, String type, String title, String content, LocalDateTime endDateTime) {
        super(id, type, title, content, endDateTime);
    }

    public ExtendedQuestionDto(LocalDateTime createDateTime, boolean showResultNow, boolean answered, 
            int numberAnswerToCheck, List<AnswerDto> answers, long id, String type, String title, 
            String content, LocalDateTime endDateTime) {
        super(id, type, title, content, endDateTime);
        this.createDateTime = createDateTime;
        this.showResultNow = showResultNow;
        this.answered = answered;
        this.numberAnswerToCheck = numberAnswerToCheck;
        this.answers = answers;
    }

    public LocalDateTime getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(LocalDateTime createDateTime) {
        this.createDateTime = createDateTime;
    }

    public boolean isShowResultNow() {
        return showResultNow;
    }

    public void setShowResultNow(boolean showResultNow) {
        this.showResultNow = showResultNow;
    }

    public boolean isAnswered() {
        return answered;
    }

    public void setAnswered(boolean answered) {
        this.answered = answered;
    }

    public int getNumberAnswerToCheck() {
        return numberAnswerToCheck;
    }

    public void setNumberAnswerToCheck(int numberAnswerToCheck) {
        this.numberAnswerToCheck = numberAnswerToCheck;
    }

    public List<AnswerDto> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerDto> answers) {
        this.answers = answers;
    }

    @Override
    public int hashCode() {        
        int hash = 7 + super.hashCode();
        hash = 97 * hash + Objects.hashCode(this.createDateTime);
        hash = 97 * hash + (this.showResultNow ? 1 : 0);
        hash = 97 * hash + this.numberAnswerToCheck;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {        
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final QuestionDto overriding = (QuestionDto) obj;
        if(!super.equals(overriding)){
            return false;
        }
        final ExtendedQuestionDto other = (ExtendedQuestionDto) obj;
        if (!Objects.equals(this.createDateTime, other.createDateTime)) {
            return false;
        }
        if (this.showResultNow != other.showResultNow) {
            return false;
        }
        if (this.answered != other.answered) {
            return false;
        }
        if (this.numberAnswerToCheck != other.numberAnswerToCheck) {
            return false;
        }
        if (!Objects.equals(this.answers, other.answers)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ExtendedQuestionDto{" + 
                "id=" + super.getId() + 
                ", type=" + super.getType() + 
                ", title=" + super.getType() + 
                ", content=" + super.getContent() + 
                ", createDateTime=" + createDateTime + 
                ", endDateTime=" + super.getEndDateTime() + 
                ", showResultNow=" + showResultNow + 
                ", answered=" + answered + 
                ", numberAnswerToCheck=" + numberAnswerToCheck + 
                ", answers=" + answers +
                ", links=" + super.getLinks() +  
                "}";
    }
    
}
