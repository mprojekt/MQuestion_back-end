package m.mquestion.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Question")
public class Question implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;    
    private String title;
    private String text;
    @Column(name = "create_date")
    private LocalDateTime createDateTime;
    @Column(name = "end_date")
    private LocalDateTime endDateTime;
    @Column(name = "shoe_answer")
    private boolean isEnabledShowResultNow;
    @Column(name = "number_answer")
    private int numberAnswers;
    @Column(name = "notification")
    private boolean isEnabledNotificationAfterEnd;
    @Column(name = "email")
    private String emailToNotification;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "id", targetEntity = Answer.class)
    private List<Answer> thisQuestionAnswers;
    @ManyToMany
    @JoinTable(
            name = "User_info_Question",
            joinColumns = { @JoinColumn(name = "id_question", referencedColumnName = "id") },
            inverseJoinColumns = { @JoinColumn(name = "id_user_info", referencedColumnName = "id") })
    private List<Voter> thisQuestionVoter;

    public Question() {
    }

    public Question(String title, String text, LocalDateTime createDateTime, LocalDateTime endDateTime, boolean isEnabledShowResultNow, int numberAnswers) {
        this.title = title;
        this.text = text;
        this.createDateTime = createDateTime;
        this.endDateTime = endDateTime;
        this.isEnabledShowResultNow = isEnabledShowResultNow;
        this.numberAnswers = numberAnswers;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Answer> getThisQuestionAnswers() {
        return thisQuestionAnswers;
    }

    public void setThisQuestionAnswers(List<Answer> thisQuestionAnswers) {
        this.thisQuestionAnswers = thisQuestionAnswers;
    }

    public List<Voter> getThisQuestionVoter() {
        return thisQuestionVoter;
    }

    public void setThisQuestionVoter(List<Voter> thisQuestionVoter) {
        this.thisQuestionVoter = thisQuestionVoter;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(LocalDateTime createDateTime) {
        this.createDateTime = createDateTime;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }

    public boolean isIsEnabledShowResultNow() {
        return isEnabledShowResultNow;
    }

    public void setIsEnabledShowResultNow(boolean isEnabledShowResultNow) {
        this.isEnabledShowResultNow = isEnabledShowResultNow;
    }

    public int getNumberAnswers() {
        return numberAnswers;
    }

    public void setNumberAnswers(int numberAnswers) {
        this.numberAnswers = numberAnswers;
    }

    public boolean isIsEnabledNotificationAfterEnd() {
        return isEnabledNotificationAfterEnd;
    }

    public void setIsEnabledNotificationAfterEnd(boolean isEnabledNotificationAfterEnd) {
        this.isEnabledNotificationAfterEnd = isEnabledNotificationAfterEnd;
    }

    public String getEmailToNotification() {
        return emailToNotification;
    }

    public void setEmailToNotification(String emailToNotification) {
        this.emailToNotification = emailToNotification;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
        hash = 97 * hash + Objects.hashCode(this.createDateTime);
        hash = 97 * hash + Objects.hashCode(this.endDateTime);
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
        final Question other = (Question) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.text, other.text)) {
            return false;
        }
        if (!Objects.equals(this.createDateTime, other.createDateTime)) {
            return false;
        }
        if (!Objects.equals(this.endDateTime, other.endDateTime)) {
            return false;
        }
        return true;
    }
    
}
