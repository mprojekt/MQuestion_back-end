package m.mquestion.repositories;

import m.mquestion.entities.Voter;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoterDao extends CrudRepository<Voter, Long> {
    
    public Voter findByJSessionId(String jSessionId);

}
