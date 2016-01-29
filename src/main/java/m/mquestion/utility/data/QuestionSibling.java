package m.mquestion.utility.data;


public class QuestionSibling {
    
    private long thisQuestionId;
    private long prevQuestionId;
    private long nextQuestionId;

    public QuestionSibling() {
    }

    public QuestionSibling(long thisQuestionId) {
        this.thisQuestionId = thisQuestionId;
    }

    public QuestionSibling(long thisQuestionId, long prevQuestionId, long nextQuestionId) {
        this(thisQuestionId);
        this.prevQuestionId = prevQuestionId;
        this.nextQuestionId = nextQuestionId;
    }
    
    public long getThisQuestionId() {
        return thisQuestionId;
    }

    public void setThisQuestionId(long thisQuestionId) {
        this.thisQuestionId = thisQuestionId;
    }

    public long getPrevQuestionId() {
        return prevQuestionId;
    }

    public void setPrevQuestionId(long prevQuestionId) {
        this.prevQuestionId = prevQuestionId;
    }

    public long getNextQuestionId() {
        return nextQuestionId;
    }

    public void setNextQuestionId(long nextQuestionId) {
        this.nextQuestionId = nextQuestionId;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + (int) (this.thisQuestionId ^ (this.thisQuestionId >>> 32));
        hash = 23 * hash + (int) (this.prevQuestionId ^ (this.prevQuestionId >>> 32));
        hash = 23 * hash + (int) (this.nextQuestionId ^ (this.nextQuestionId >>> 32));
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
        final QuestionSibling other = (QuestionSibling) obj;
        if (this.thisQuestionId != other.thisQuestionId) {
            return false;
        }
        if (this.prevQuestionId != other.prevQuestionId) {
            return false;
        }
        if (this.nextQuestionId != other.nextQuestionId) {
            return false;
        }
        return true;
    }
    
}
