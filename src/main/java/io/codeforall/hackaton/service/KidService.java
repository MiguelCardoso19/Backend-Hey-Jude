package io.codeforall.hackaton.service;

import io.codeforall.hackaton.Exception.KidNotFoundException;
import io.codeforall.hackaton.Exception.ParentNotFoundException;
import io.codeforall.hackaton.command.EventDto;
import io.codeforall.hackaton.persistence.model.Event;
import io.codeforall.hackaton.persistence.model.Kid;
import io.codeforall.hackaton.persistence.model.Parent;
import org.springframework.stereotype.Service;

import java.util.List;


public interface KidService {


        Kid get(Integer id);

        Kid save(Kid kid);

        void delete(Integer id) throws KidNotFoundException;

        List<Kid> list();

        List<Parent> listParents(Integer id) throws KidNotFoundException;

        Parent addParent(Integer id, Parent recipient)
                throws KidNotFoundException;

        Event addEvent(Integer id, Event convert) throws KidNotFoundException;

        List<Event> listEvents(Integer id) throws KidNotFoundException;
}

