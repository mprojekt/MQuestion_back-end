package m.mquestion.domain;

import java.util.List;
import java.util.Objects;

public class ResponseQuestionDto {
    
    private int numberAllQuestion;
    private int numberCurrentPage;
    private int numberLastPage;
    private String jSessionID;
    private List<QuestionDto> questions;

    public ResponseQuestionDto() {
    }

    public ResponseQuestionDto(int numberAllQuestion, int numberCurrentPage, String jSessionID, List<QuestionDto> questions) {
        this.numberAllQuestion = numberAllQuestion;
        this.numberCurrentPage = numberCurrentPage;
        this.jSessionID = jSessionID;
        this.questions = questions;
    }

    public int getNumberAllQuestion() {
        return numberAllQuestion;
    }

    public void setNumberAllQuestion(int numberAllQuestion) {
        this.numberAllQuestion = numberAllQuestion;
    }

    public int getNumberCurrentPage() {
        return numberCurrentPage;
    }

    public void setNumberCurrentPage(int numberCurrentPage) {
        this.numberCurrentPage = numberCurrentPage;
    }

    public int getNumberLastPage() {
        return numberLastPage;
    }

    public void setNumberLastPage(int numberLastPage) {
        this.numberLastPage = numberLastPage;
    }

    public List<QuestionDto> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionDto> questions) {
        this.questions = questions;
    }

    public String getjSessionID() {
        return jSessionID;
    }

    public void setjSessionID(String jSessionID) {
        this.jSessionID = jSessionID;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + this.numberAllQuestion;
        hash = 23 * hash + this.numberCurrentPage;
        hash = 23 * hash + this.numberLastPage;
        hash = 23 * hash + Objects.hashCode(this.jSessionID);
        hash = 23 * hash + Objects.hashCode(this.questions);
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
        final ResponseQuestionDto other = (ResponseQuestionDto) obj;
        if (this.numberAllQuestion != other.numberAllQuestion) {
            return false;
        }
        if (this.numberCurrentPage != other.numberCurrentPage) {
            return false;
        }
        if (this.numberLastPage != other.numberLastPage) {
            return false;
        }
        if (!Objects.equals(this.jSessionID, other.jSessionID)) {
            return false;
        }
        if (!Objects.equals(this.questions, other.questions)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ResponseQuestionDto{" + 
                "numberAllQuestion=" + numberAllQuestion + 
                ", numberCurrentPage=" + numberCurrentPage + 
                ", numberLastPage=" + numberLastPage + 
                ", jSessionID=" + jSessionID + 
                ", questions=" + questions + 
                "}";
    }

}
