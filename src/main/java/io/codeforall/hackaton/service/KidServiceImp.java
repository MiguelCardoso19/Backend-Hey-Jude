package io.codeforall.hackaton.service;
import io.codeforall.hackaton.Exception.KidNotFoundException;
import io.codeforall.hackaton.Exception.ParentNotFoundException;
import io.codeforall.hackaton.command.EventDto;
import io.codeforall.hackaton.persistence.dao.EventDao;
import io.codeforall.hackaton.persistence.dao.KidDao;
import io.codeforall.hackaton.persistence.dao.ParentDao;
import io.codeforall.hackaton.persistence.model.Event;
import io.codeforall.hackaton.persistence.model.Kid;
import io.codeforall.hackaton.persistence.model.Parent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class KidServiceImp implements KidService{

        private KidDao kidDao;
        private ParentDao parentDao;
        private EventDao eventDao;

        @Autowired
    public void setEventDao(EventDao eventDao) {
        this.eventDao = eventDao;
    }

    @Autowired
        public void setKidDao(KidDao kidDao) {
            this.kidDao = kidDao;
        }

        @Autowired
        public void setParentDao(ParentDao parentDao) {
            this.parentDao = parentDao;
        }

        @Override
        public Kid get(Integer id) {
            return kidDao.findById(id);
        }

        @Transactional
        @Override
        public Kid save(Kid kid) {
            return kidDao.saveOrUpdate(kid);
        }

        @Transactional
        @Override
        public void delete(Integer id) throws KidNotFoundException {

            Kid kid = Optional.ofNullable(kidDao.findById(id))
                    .orElseThrow(KidNotFoundException::new);

            kidDao.delete(id);
        }

        @Override
        public List<Kid> list() {
            return kidDao.findAll();
        }

        @Transactional(readOnly = true)
        @Override
        public List<Parent> listParents(Integer id) throws KidNotFoundException {

            Kid kid = Optional.ofNullable(kidDao.findById(id))
                    .orElseThrow(KidNotFoundException::new);

            return new ArrayList<>(kid.getParents());
        }

        @Transactional
        @Override
        public Parent addParent(Integer id, Parent parent) throws KidNotFoundException {

            Kid kid = Optional.ofNullable(kidDao.findById(id))
                    .orElseThrow(KidNotFoundException::new);

            if (parent.getId() == null) {
                kid.addParent(parent);
                kidDao.saveOrUpdate(kid);
            } else {
                parentDao.saveOrUpdate(parent);
            }
            return kid.getParents().get(kid.getParents().size() - 1);
        }

    @Transactional
    @Override
    public Event addEvent(Integer id, Event event) throws KidNotFoundException {

        Kid kid = Optional.ofNullable(kidDao.findById(id))
                .orElseThrow(KidNotFoundException::new);

        if (event.getId() == null) {
            kid.addEvent(event);
            kidDao.saveOrUpdate(kid);
        } else {
            eventDao.saveOrUpdate(event);
        }
        return kid.getEvents().get(kid.getEvents().size() - 1);
    }

    @Override
    public List<Event> listEvents(Integer id) throws KidNotFoundException {
        Kid kid = Optional.ofNullable(kidDao.findById(id))
                .orElseThrow(KidNotFoundException::new);

        return new ArrayList<>(kid.getEvents());
    }

}
