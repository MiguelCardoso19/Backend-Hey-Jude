package io.codeforall.hackaton.Controller.Rest;
import io.codeforall.hackaton.Exception.KidNotFoundException;
import io.codeforall.hackaton.Exception.ParentNotFoundException;
import io.codeforall.hackaton.command.ParentDto;
import io.codeforall.hackaton.converter.ParentDtoToParent;
import io.codeforall.hackaton.converter.ParentToParentDto;
import io.codeforall.hackaton.persistence.model.Parent;
import io.codeforall.hackaton.service.KidService;
import io.codeforall.hackaton.service.ParentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/kid")
public class RestParentController {

    private KidService kidService;
    private ParentService parentService;
    private ParentToParentDto parentToParentDto;
    private ParentDtoToParent parentDtoToParent;

    @Autowired
    public void setKidService(KidService kidService) {
        this.kidService = kidService;
    }

    @Autowired
    public void setParentService(ParentService parentService) {
        this.parentService = parentService;
    }

    @Autowired
    public void setParentToParentDto(ParentToParentDto parentToParentDto) {
        this.parentToParentDto = parentToParentDto;
    }

    @Autowired
    public void setParentDtoToParent(ParentDtoToParent parentDtoToParent) {
        this.parentDtoToParent = parentDtoToParent;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{cid}/parent")
    public ResponseEntity<List<ParentDto>> listParents(@PathVariable Integer cid) {

        try {

            List<ParentDto> parentDtos = kidService.listParents(cid).stream()
                    .map(parent -> parentToParentDto.convert(parent))
                    .collect(Collectors.toList());

            return new ResponseEntity<>(parentDtos, HttpStatus.OK);

        } catch (KidNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{cid}/parent/{id}")
    public ResponseEntity<ParentDto> showParent(@PathVariable Integer cid, @PathVariable Integer id) {

        Parent parent = parentService.get(id);

        if (parent == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(parentToParentDto.convert(parent), HttpStatus.OK);
    }

    @PostMapping("/{cid}/parent")
    public ResponseEntity<?> addParent(
            @PathVariable Integer cid,
            @Valid @RequestBody ParentDto parentDto,
            BindingResult bindingResult,
            UriComponentsBuilder uriComponentsBuilder
    ) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }

        try {
            if (parentDto.getId() != null) {
                return ResponseEntity.badRequest().build();
            }

            Parent parent = kidService.addParent(cid, parentDtoToParent.convert(parentDto));

            URI location = uriComponentsBuilder.path("/api/kid/{cid}/parent/{pid}")
                    .buildAndExpand(cid, parent.getId()).toUri();

            return ResponseEntity.created(location).build();
        } catch (KidNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }


    @RequestMapping(method = RequestMethod.PUT, path = "/{cid}/parent/{id}")
    public ResponseEntity<ParentDto> editParent(@PathVariable Integer cid, @PathVariable Integer id, @Valid @RequestBody ParentDto parentDto, BindingResult bindingResult) {

        try {

            if (bindingResult.hasErrors()) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            if (parentDto.getId() != null && parentDto.getId() != id) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            parentDto.setId(id);

            Parent savedparent = parentDtoToParent.convert(parentDto);
            kidService.addParent(cid, savedparent);

            return new ResponseEntity<>(HttpStatus.OK);

        } catch (KidNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
    }
}
