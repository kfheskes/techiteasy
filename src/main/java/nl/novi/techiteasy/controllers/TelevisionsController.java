package nl.novi.techiteasy.controllers;

import nl.novi.techiteasy.dtos.television.TelevisionDto;
import nl.novi.techiteasy.dtos.television.TelevisionInputDto;
import nl.novi.techiteasy.exceptions.RecordNotFoundException;
import nl.novi.techiteasy.exceptions.ValidationException;
import nl.novi.techiteasy.models.Television;
import nl.novi.techiteasy.service.TelevisionService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.net.URI;
import java.util.List;

import static nl.novi.techiteasy.controllers.ControllerHelper.checkForBindingResult;

@RequestMapping("/televisions")
@RestController
public class TelevisionsController {


    private final TelevisionService televisionService;

    public TelevisionsController( TelevisionService televisionService) {
        this.televisionService = televisionService;
    }

    @GetMapping
    public ResponseEntity<List<TelevisionDto>> getAllTelevisions(){
        return ResponseEntity.ok(televisionService.getAllTelevision());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TelevisionDto> getTelevision(@PathVariable long id){
       if (id > 0) {
           TelevisionDto televisionDto = televisionService.getTelevisionId(id);
           return ResponseEntity.ok(televisionDto);
       } else {
           throw new RecordNotFoundException("there is no television");
       }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Television> deleteTelevision(@PathVariable long id){

            televisionService.deleteTelevision(id);
            return ResponseEntity.noContent().build();

    }

    // is om data erin te zetten(posten)
    @PostMapping
    public ResponseEntity<TelevisionDto> addTelevision(@RequestBody TelevisionInputDto televisionInputDto, BindingResult br){
    if (br.hasFieldErrors()) {
        throw new ValidationException(checkForBindingResult(br));
    } else {
        TelevisionDto savedTelevision = televisionService.createTelevision(televisionInputDto);
        URI uri = URI.create(
                ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/" + savedTelevision.id).toUriString());
        return ResponseEntity.created(uri).body(savedTelevision);
    }
    }

    //Put is om te bewerken
    @PutMapping("/{id}")
    public ResponseEntity<TelevisionDto> changeTelevision(@PathVariable long id, @RequestBody Television television ) {
        TelevisionDto changeTelevisionId = televisionService.updateTelevision(id, television);

        return ResponseEntity.ok().body(changeTelevisionId);
    }
}
