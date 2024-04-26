package io.codeforall.hackaton.converter;

import io.codeforall.hackaton.command.EventDto;
import io.codeforall.hackaton.command.KidDto;
import io.codeforall.hackaton.persistence.model.Event;
import io.codeforall.hackaton.persistence.model.Parent;
import io.codeforall.hackaton.service.EventService;
import io.codeforall.hackaton.service.KidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.core.convert.converter.Converter;

import javax.persistence.ManyToOne;
import java.lang.annotation.Annotation;

@Component
public class EventDtoToEvent implements Converter<EventDto, Event> {

    private EventService eventService;
@Autowired
    public void setEventService(EventService eventService) {
        this.eventService = eventService;
    }

    @Override
    public Event convert(EventDto eventDto) {

        Event event = (eventDto.getId() != null ? eventService.get(eventDto.getId()) : new Event());
        event.setDescription(eventDto.getDescription());
        event.setStartDate(eventDto.getStartDate());
        event.setEndDate(eventDto.getEndDate());

        return event;
    }

}
