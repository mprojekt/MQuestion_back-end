package m.mquestion.utility;

import m.mquestion.domain.Convertable;
import m.mquestion.entities.Question;


public enum QuestionDtoType {
    SIMPLE {

        @Override
        public Convertable convertUsing(QuestionConverter qc, Question question, String jSessionId) {
            return qc.convert(question);
        }
    }, 
    EXTENDED {

        @Override
        public Convertable convertUsing(QuestionConverter qc, Question question, String jSessionId) {
            return qc.convert(question, jSessionId);
        }
    };

    public abstract Convertable convertUsing(QuestionConverter qc, Question question, String jSessionId);
    
}
