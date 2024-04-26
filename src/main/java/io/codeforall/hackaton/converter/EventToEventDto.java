package io.codeforall.hackaton.converter;

import io.codeforall.hackaton.command.EventDto;
import io.codeforall.hackaton.command.KidDto;
import io.codeforall.hackaton.persistence.model.Event;
import io.codeforall.hackaton.persistence.model.Kid;
import org.springframework.stereotype.Component;

@Component
public class EventToEventDto extends AbstractConverter<Event, EventDto> {


    @Override
    public EventDto convert(Event event) {

        EventDto eventDto = new EventDto();
        eventDto.setId(event.getId());
        eventDto.setDescription(event.getDescription());
        eventDto.setStartDate(event.getStartDate());
        eventDto.setEndDate(event.getEndDate());

        return eventDto;
    }
}

