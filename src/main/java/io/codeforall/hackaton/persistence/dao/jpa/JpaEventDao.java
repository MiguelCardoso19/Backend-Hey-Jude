package io.codeforall.hackaton.persistence.dao.jpa;

import io.codeforall.hackaton.persistence.dao.EventDao;
import io.codeforall.hackaton.persistence.model.Event;
import org.springframework.stereotype.Repository;

@Repository
public class JpaEventDao extends GenericJpaDao<Event> implements EventDao {

    public JpaEventDao(){
        super(Event.class);
    }

}
