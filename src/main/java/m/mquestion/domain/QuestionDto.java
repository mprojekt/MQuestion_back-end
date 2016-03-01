package m.mquestion.domain;

import java.time.LocalDateTime;
import java.util.*;


public class QuestionDto implements Convertable{
    
    private long id;
    private String type;
    private String title;
    private String content;
    private LocalDateTime endDateTime;    
    private Map<String, String> links;

    public QuestionDto() {
    }

    public QuestionDto(long id, String type, String title, String content, LocalDateTime endDateTime) {
        this.id = id;
        this.type = type;
        this.title = title;
        this.content = content;
        this.endDateTime = endDateTime;
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

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;
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
        hash = 79 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 79 * hash + Objects.hashCode(this.type);
        hash = 79 * hash + Objects.hashCode(this.title);
        hash = 79 * hash + Objects.hashCode(this.content);
        hash = 79 * hash + Objects.hashCode(this.endDateTime);
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
        final QuestionDto other = (QuestionDto) obj;
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
        if (!Objects.equals(this.endDateTime, other.endDateTime)) {
            return false;
        }
        if (!Objects.equals(this.links, other.links)) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "QuestionDto{" + 
                "id=" + id + 
                ", type=" + type + 
                ", title=" + title + 
                ", content=" + content + 
                ", endDateTime=" + endDateTime + 
                ", links=" + links + 
                "}";
    }
}
