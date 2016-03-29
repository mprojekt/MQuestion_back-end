package m.mquestion.domain;


public class StatisticQuestion {
    
    private long numberAllQuestion;
    private int numberAllPage;

    public StatisticQuestion() {
    }

    public StatisticQuestion(long numberAllQuestion, int numberAllPage) {
        this.numberAllQuestion = numberAllQuestion;
        this.numberAllPage = numberAllPage;
    }

    public long getNumberAllQuestion() {
        return numberAllQuestion;
    }

    public void setNumberAllQuestion(long numberAllQuestion) {
        this.numberAllQuestion = numberAllQuestion;
    }

    public int getNumberAllPage() {
        return numberAllPage;
    }

    public void setNumberAllPage(int numberAllPage) {
        this.numberAllPage = numberAllPage;
    }

}
