package io.codeforall.hackaton.service;

import io.codeforall.hackaton.persistence.dao.EventDao;
import io.codeforall.hackaton.persistence.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EventServiceImp implements EventService {

    private EventDao eventDao;

    public EventDao getEventDao() {
        return eventDao;
    }

    @Autowired
    public void setEventDao(EventDao eventDao) {
        this.eventDao = eventDao;
    }

    @Transactional
    public Event addEvent(Event event) {
        // Perform any additional business logic or validation

        // Insert event into the database
        return eventDao.saveOrUpdate(event);
    }

    @Transactional
    @Override
    public Event save(Event event) {
        return eventDao.saveOrUpdate(event);
    }

    @Override
    public Event get(Integer id) {
        return eventDao.findById(id);
    }

}
