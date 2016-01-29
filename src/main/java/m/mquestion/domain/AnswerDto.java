package m.mquestion.domain;

import java.util.Map;
import java.util.Objects;


public class AnswerDto {
    
    private long id;
    private String content;
    private boolean rightAnswer;
    private int numberOfVote;
    private Map<String, String> links;

    public AnswerDto() {
    }

    public AnswerDto(long id, String content, boolean rightAnswer, 
            int numberOfVote, Map<String, String> links) {
        this.id = id;
        this.content = content;
        this.rightAnswer = rightAnswer;
        this.numberOfVote = numberOfVote;
        this.links = links;
    }
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(boolean rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    public int getNumberOfVote() {
        return numberOfVote;
    }

    public void setNumberOfVote(int numberOfVote) {
        this.numberOfVote = numberOfVote;
    }

    public Map<String, String> getLinks() {
        return links;
    }

    public void setLinks(Map<String, String> links) {
        this.links = links;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 97 * hash + Objects.hashCode(this.content);
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
        final AnswerDto other = (AnswerDto) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.content, other.content)) {
            return false;
        }
        if (this.rightAnswer != other.rightAnswer) {
            return false;
        }
        if (this.numberOfVote != other.numberOfVote) {
            return false;
        }
        if (!Objects.equals(this.links, other.links)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AnswerDto{" + 
                "id=" + id + 
                ", content=" + content + 
                ", rightAnswer=" + rightAnswer + 
                ", numberOfVote=" + numberOfVote + 
                ", links=" + links + 
                "}";
    }

    
}
