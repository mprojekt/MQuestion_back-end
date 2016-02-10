package m.mquestion.domain;

import java.time.LocalDateTime;
import java.util.*;


public class SimpleQuestionDto {
    
    private long id;
    private String type;
    private String title;
    private String content;
    private LocalDateTime endDate;    
    private Map<String, String> links;

    public SimpleQuestionDto() {
    }

    public SimpleQuestionDto(long id, String type, String title, String content, LocalDateTime endDate) {
        this.id = id;
        this.type = type;
        this.title = title;
        this.content = content;
        this.endDate = endDate;
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

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
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
        hash = 79 * hash + Objects.hashCode(this.endDate);
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
        final SimpleQuestionDto other = (SimpleQuestionDto) obj;
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
        if (!Objects.equals(this.endDate, other.endDate)) {
            return false;
        }
        if (!Objects.equals(this.links, other.links)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "SimpleQuestionDto{" + 
                "id=" + id + 
                ", type=" + type + 
                ", title=" + title + 
                ", content=" + content + 
                ", endDate=" + endDate + 
                ", links=" + links + 
                "}";
    }

}
