package io.codeforall.hackaton.Controller.Rest;
import io.codeforall.hackaton.Exception.KidNotFoundException;
import io.codeforall.hackaton.command.KidDto;
import io.codeforall.hackaton.converter.KidDtoToKid;
import io.codeforall.hackaton.converter.KidToKidDto;
import io.codeforall.hackaton.persistence.model.Kid;
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
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/kid")
public class RestKidController {

    private KidService kidService;
    private KidDtoToKid kidDtoToKid;
    private KidToKidDto kidToKidDto;

    @Autowired
    public void setKidService(KidService kidService) {
        this.kidService = kidService;
    }

    @Autowired
    public void setKidDtoToKid(KidDtoToKid kidDtoToKid) {
        this.kidDtoToKid = kidDtoToKid;
    }


    @Autowired
    public void setKidToKidDto(KidToKidDto kidToKidDto) {
        this.kidToKidDto = kidToKidDto;
    }

    @RequestMapping(method = RequestMethod.GET, path = {"/", ""})
    public ResponseEntity<List<KidDto>> listKids() {

        List<KidDto> kidDtos = kidService.list().stream()
                .map(kid -> kidToKidDto.convert(kid))
                .collect(Collectors.toList());

        return new ResponseEntity<>(kidDtos, HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public ResponseEntity<KidDto> showKid(@PathVariable Integer id) {

        Kid kid = kidService.get(id);

        if (kid == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(kidToKidDto.convert(kid), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, path = {"/", ""})
    public ResponseEntity<?> addKid (@Valid @RequestBody KidDto kidDto, BindingResult bindingResult, UriComponentsBuilder uriComponentsBuilder) {

        if (bindingResult.hasErrors() || kidDto.getId() != null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Kid savedKid = kidService.save(kidDtoToKid.convert(kidDto));

        UriComponents uriComponents = uriComponentsBuilder.path("/api/kid/" + savedKid.getId()).build();

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriComponents.toUri());

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/{id}")
    public ResponseEntity<KidDto> editKid(@Valid @RequestBody KidDto kidDto, BindingResult bindingResult, @PathVariable Integer id) {

        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (kidDto.getId() != null && !kidDto.getId().equals(id)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (kidService.get(id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        kidDto.setId(id);

        kidService.save(kidDtoToKid.convert(kidDto));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    public ResponseEntity<KidDto> deleteKid(@PathVariable Integer id) {

        try {

            kidService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } catch (KidNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }
    }
}
