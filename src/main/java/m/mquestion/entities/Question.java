package m.mquestion.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;
import javax.persistence.*;

@Entity
@Table(name = "question")
public class Question implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; 
    
    private String title;
    
    private String content;
    
    @Column(name = "create_date")
    private Timestamp createDateTime;
    
    @Column(name = "end_date")
    private Timestamp endDateTime;
    
    @Column(name = "show_answer")
    private boolean enabledShowResultNow;
    
    @Column(name = "number_answers")
    private int numberAnswersToCheck;
    
    @Column(name = "notification")
    private boolean enabledNotificationAfterEnd;
    
    @Column(name = "email")
    private String emailToNotification;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "type_id")
    @JsonManagedReference
    private Type typeQuestion;
    
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "targetQuestion", targetEntity = Answer.class)
    @JsonManagedReference
    private List<Answer> thisQuestionAnswers;
    
    @ManyToMany
    @JoinTable(name = "question_has_user_info",
            joinColumns = { @JoinColumn(name = "question_id", referencedColumnName = "id") },
            inverseJoinColumns = { @JoinColumn(name = "user_info_id", referencedColumnName = "id") })
    @JsonIgnore
    private List<Voter> thisQuestionVoter;

    public Question() {
    }

    public Question(String title, String content, boolean isEnabledShowResultNow, int numberAnswersToCheck, 
            boolean isEnabledNotificationAfterEnd, Type typeQuestion, List<Answer> thisQuestionAnswers) {
        this.title = title;
        this.content = content;
        this.enabledShowResultNow = isEnabledShowResultNow;
        this.numberAnswersToCheck = numberAnswersToCheck;
        this.enabledNotificationAfterEnd = isEnabledNotificationAfterEnd;
        this.typeQuestion = typeQuestion;
        this.thisQuestionAnswers = thisQuestionAnswers;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(Timestamp createDateTime) {
        this.createDateTime = createDateTime;
    }

    public Timestamp getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(Timestamp endDateTime) {
        this.endDateTime = endDateTime;
    }

    public boolean isEnabledShowResultNow() {
        return enabledShowResultNow;
    }

    public void setEnabledShowResultNow(boolean isEnabledShowResultNow) {
        this.enabledShowResultNow = isEnabledShowResultNow;
    }

    public int getNumberAnswersToCheck() {
        return numberAnswersToCheck;
    }

    public void setNumberAnswersToCheck(int numberAnswersToCheck) {
        this.numberAnswersToCheck = numberAnswersToCheck;
    }

    public boolean isEnabledNotificationAfterEnd() {
        return enabledNotificationAfterEnd;
    }

    public void setEnabledNotificationAfterEnd(boolean isEnabledNotificationAfterEnd) {
        this.enabledNotificationAfterEnd = isEnabledNotificationAfterEnd;
    }

    public String getEmailToNotification() {
        return emailToNotification;
    }

    public void setEmailToNotification(String emailToNotification) {
        this.emailToNotification = emailToNotification;
    }

    public Type getTypeQuestion() {
        return typeQuestion;
    }

    public void setTypeQuestion(Type typeQuestion) {
        this.typeQuestion = typeQuestion;
    }       

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
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
        if (!Objects.equals(this.content, other.content)) {
            return false;
        }
        return true;
    }
    
}
