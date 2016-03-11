package m.mquestion.repositories.impl;

import java.sql.Timestamp;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import m.mquestion.entities.Question;
import m.mquestion.repositories.SiblingQuestionDao;
import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SiblingQuestionDaoImpl implements SiblingQuestionDao{
    
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Question getPreviusQuestion(Question question) {
        String hql = "SELECT Q FROM Question Q "
                + "WHERE Q.endDateTime < CURRENT_TIMESTAMP AND Q.endDateTime >= :end_date_time AND Q.id != :id "
                + "ORDER BY Q.endDateTime ASC, Q.id DESC";
        
        return getQuestion(hql, question.getId(), question.getEndDateTime());
    }

    @Override
    public Question getNextQuestion(Question question) {
        String hql = "SELECT Q FROM Question Q "
                + "WHERE Q.endDateTime < CURRENT_TIMESTAMP AND Q.endDateTime <= :end_date_time AND Q.id != :id "
                + "ORDER BY Q.endDateTime DESC, Q.id ASC";
        
        return getQuestion(hql, question.getId(), question.getEndDateTime());
    }
    
    private Question getQuestion(String hql, long id, Timestamp endDateTime){
        Query q = getSession().createQuery(hql);
        q.setTimestamp("end_date_time", endDateTime);
        q.setLong("id", id);
        q.setMaxResults(1);
        List<Question> tmp = q.list();
        return tmp.isEmpty() ? null : tmp.get(0);
    }
    
    private Session getSession() {        
        return sessionFactory.getCurrentSession();
    }

}
