package io.codeforall.hackaton.service;

import io.codeforall.hackaton.persistence.model.Event;

public interface EventService {
     Event addEvent(Event event);

     Event save(Event event);

     Event get(Integer id);
}
