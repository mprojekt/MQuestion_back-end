package m.mquestion.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Answer")
public class Answer implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String text;
    @Column(name = "right")
    private boolean isCorrectAnswer;
    @Column(name = "number")
    private int currentVoteNumber;

    public Answer() {
    }

    public Answer(String text) {
        this.text = text;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isIsCorrectAnswer() {
        return isCorrectAnswer;
    }

    public void setIsCorrectAnswer(boolean isCorrectAnswer) {
        this.isCorrectAnswer = isCorrectAnswer;
    }

    public int getCurrentVoteNumber() {
        return currentVoteNumber;
    }

    public void setCurrentVoteNumber(int currentVoteNumber) {
        this.currentVoteNumber = currentVoteNumber;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
        hash = 97 * hash + Objects.hashCode(this.text);
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
        final Answer other = (Answer) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.text, other.text)) {
            return false;
        }
        return true;
    }       

}
