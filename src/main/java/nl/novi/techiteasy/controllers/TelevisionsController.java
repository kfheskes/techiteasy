package nl.novi.techiteasy.controllers;

import nl.novi.techiteasy.dtos.TelevisionDto;
import nl.novi.techiteasy.models.Television;
import nl.novi.techiteasy.service.TelevisionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.net.URI;
import java.util.List;

@RestController
public class TelevisionsController {


    private final TelevisionService televisionService;

    public TelevisionsController( TelevisionService televisionService) {
        this.televisionService = televisionService;
    }

    @GetMapping("/televisions")
    public ResponseEntity<List<TelevisionDto>> getAllTelevisions(){
        return ResponseEntity.ok(televisionService.getAllTelevision());
    }

    @GetMapping("/televisions/{id}")
    public ResponseEntity<TelevisionDto> getTelevision(@PathVariable long id){
        TelevisionDto televisionDto = televisionService.getTelevisionId(id);
        return ResponseEntity.ok(televisionDto);
    }

    @DeleteMapping("/televisions/{id}")
    public ResponseEntity<Television> deleteTelevision(@PathVariable long id){
            televisionService.deleteTelevision(id);
        return ResponseEntity.noContent().build();
    }

    // is om data erin te zetten(posten)
    @PostMapping("/televisions")
    public ResponseEntity<TelevisionDto> addTelevision(@RequestBody TelevisionDto televisionDto){
    TelevisionDto savedTelevision = televisionService.createTelevision(televisionDto);
        URI uri = URI.create(
                ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/" + savedTelevision.id).toUriString());
        return ResponseEntity.created(uri).body(savedTelevision);
    }

    //Put is om te bewerken
    @PutMapping("televisions/{id}")
    public ResponseEntity<TelevisionDto> changeTelevision(@PathVariable long id, @RequestBody Television television ) {
        TelevisionDto changeTelevisionId = televisionService.updateTelevision(id, television);

        return ResponseEntity.ok().body(changeTelevisionId);
    }
}
