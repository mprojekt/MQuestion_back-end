package m.mquestion.domain;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;


public class ComplexQuestionDto {
    
    private long id;
    private String type;
    private String title;
    private String content;
    private LocalDateTime createDate;
    private LocalDateTime endDate;
    private boolean showResultNow;
    private boolean answered;
    private int numberAnswerToCheck;
    private List<AnswerDto> answers;
    private Map<String, String> links;

    public ComplexQuestionDto() {
    }

    public ComplexQuestionDto(long id, String type, String title, String content, LocalDateTime createDate, 
            LocalDateTime endDate, boolean showResultNow, int numberAnswerToCheck, List<AnswerDto> answers, 
            Map<String, String> links) {
        this.id = id;
        this.type = type;
        this.title = title;
        this.content = content;
        this.createDate = createDate;
        this.endDate = endDate;
        this.showResultNow = showResultNow;
        this.numberAnswerToCheck = numberAnswerToCheck;
        this.answers = answers;
        this.links = links;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public boolean isShowResultNow() {
        return showResultNow;
    }

    public boolean isAnswered() {
        return answered;
    }

    public void setAnswered(boolean answered) {
        this.answered = answered;
    }

    public void setShowResultNow(boolean showResultNow) {
        this.showResultNow = showResultNow;
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

    public Map<String, String> getLinks() {
        return links;
    }

    public void setLinks(Map<String, String> links) {
        this.links = links;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 71 * hash + Objects.hashCode(this.type);
        hash = 71 * hash + Objects.hashCode(this.title);
        hash = 71 * hash + Objects.hashCode(this.createDate);
        hash = 71 * hash + this.numberAnswerToCheck;
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
        final ComplexQuestionDto other = (ComplexQuestionDto) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.content, other.content)) {
            return false;
        }
        if (!Objects.equals(this.createDate, other.createDate)) {
            return false;
        }
        if (!Objects.equals(this.endDate, other.endDate)) {
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
        if (!Objects.equals(this.links, other.links)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ComplexQuestionDto{" + 
                "id=" + id + 
                ", type=" + type + 
                ", title=" + title + 
                ", content=" + content + 
                ", createDate=" + createDate + 
                ", endDate=" + endDate + 
                ", showResultNow=" + showResultNow + 
                ", answered=" + answered + 
                ", numberAnswer=" + numberAnswerToCheck + 
                ", answers=" + answers + 
                ", links=" + links + 
                "}";
    }
    
}
