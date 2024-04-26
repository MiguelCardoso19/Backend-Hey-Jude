package io.codeforall.hackaton.Controller.Rest;

import io.codeforall.hackaton.Exception.KidNotFoundException;
import io.codeforall.hackaton.command.EventDto;
import io.codeforall.hackaton.command.KidDto;
import io.codeforall.hackaton.converter.EventDtoToEvent;
import io.codeforall.hackaton.converter.EventToEventDto;
import io.codeforall.hackaton.persistence.model.Event;
import io.codeforall.hackaton.service.EventService;
import io.codeforall.hackaton.service.KidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/kid")
public class RestEventController {

    private EventService eventService;
    private EventDtoToEvent eventDtoToEvent;
    private EventToEventDto eventToEventDto;
    private KidService kidService;

    @Autowired
    public void setKidService(KidService kidService) {
        this.kidService = kidService;
    }

    public RestEventController(EventService eventService) {
        this.eventService = eventService;
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, path = "/{id}/event")
    public ResponseEntity<EventDto> showEvent(@PathVariable Integer id) {

        Event event = eventService.get(id);

        if (event == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(eventToEventDto.convert(event), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, path = {"/{id}/event"})
    public ResponseEntity<Event> addEvent(@PathVariable Integer id, @Valid @RequestBody EventDto eventDto, BindingResult bindingResult, UriComponentsBuilder uriComponentsBuilder) throws KidNotFoundException {

        if (bindingResult.hasErrors() || eventDto.getId() != null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Event savedEvent = kidService.addEvent(id, eventDtoToEvent.convert(eventDto));

        UriComponents uriComponents = uriComponentsBuilder.path("/api/kid/" + savedEvent.getId()).build();

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriComponents.toUri());

        return new ResponseEntity<>(headers, HttpStatus.CREATED);

    }
    @Autowired
    public void setEventService(EventService eventService) {
        this.eventService = eventService;
    }

    public EventDtoToEvent getEventDtoToEvent() {
        return eventDtoToEvent;
    }
    @Autowired
    public void setEventDtoToEvent(EventDtoToEvent eventDtoToEvent) {
        this.eventDtoToEvent = eventDtoToEvent;
    }

    public EventToEventDto getEventToEventDto() {
        return eventToEventDto;
    }
    @Autowired
    public void setEventToEventDto(EventToEventDto eventToEventDto) {
        this.eventToEventDto = eventToEventDto;
    }

    public EventService getEventService() {
        return eventService;
    }

}


