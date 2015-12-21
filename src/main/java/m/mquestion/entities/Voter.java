package m.mquestion.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "User_info")
public class Voter implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "jsessionid")
    private String jSessionId;
    @Column(name = "added_date")
    private LocalDateTime addedDateTime;

    public Voter() {
    }

    public Voter(String jSessionId, LocalDateTime addedDateTime) {
        this.jSessionId = jSessionId;
        this.addedDateTime = addedDateTime;
    }

    public String getjSessionId() {
        return jSessionId;
    }

    public void setjSessionId(String jSessionId) {
        this.jSessionId = jSessionId;
    }

    public LocalDateTime getAddedDateTime() {
        return addedDateTime;
    }

    public void setAddedDateTime(LocalDateTime addedDateTime) {
        this.addedDateTime = addedDateTime;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.id);
        hash = 37 * hash + Objects.hashCode(this.jSessionId);
        hash = 37 * hash + Objects.hashCode(this.addedDateTime);
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
        final Voter other = (Voter) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.jSessionId, other.jSessionId)) {
            return false;
        }
        if (!Objects.equals(this.addedDateTime, other.addedDateTime)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "{" + "id = " + id + ", jsessionid = " + jSessionId + ", added date and time = " + addedDateTime + "}";
    }
    
}
