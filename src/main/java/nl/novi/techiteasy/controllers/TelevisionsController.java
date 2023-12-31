package nl.novi.techiteasy.controllers;

import jakarta.validation.Valid;
import nl.novi.techiteasy.dtos.IdInputDto;
import nl.novi.techiteasy.dtos.television.TelevisionDto;
import nl.novi.techiteasy.dtos.television.TelevisionInputDto;
import nl.novi.techiteasy.exceptions.RecordNotFoundException;
import nl.novi.techiteasy.exceptions.ValidationException;
import nl.novi.techiteasy.models.RemoteController;
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
    // geeft aan dat deze methode een ResponsEntity teruggeeft die een lijst van TelevisionDto bevat
    // getAllTelevisions is de methode van TelevisionService die een lijst teruggeeft van TelevisionDto
    public ResponseEntity<List<TelevisionDto>> getAllTelevisions(){
        //creert een HTTP200 ok-response met de lijst van TelevisionDto als body
        return ResponseEntity.ok(televisionService.getAllTelevisions());
    }

    // geeft aan dat deze methode reageert op HTTP GET verzoek op de aangegeven URI met de padvariable "id"
    @GetMapping("/{id}")
    // geeft methode een ResponseEntity terug die een TelevisionDto bevat
    // de methoede roep getTelevision(id)
    public ResponseEntity<TelevisionDto> getTelevision(@PathVariable long id){
           TelevisionDto televisionDto = televisionService.getTelevisionId(id);
           return ResponseEntity.ok(televisionDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Television> deleteTelevision(@PathVariable long id){
            // roept de methode om television te verwijderen op basis van id
            televisionService.deleteTelevision(id);
            // creert een HTTP 204 No-Content-response omdat de succesvolle verwijdering geen inhoud teruggeeft.
            return ResponseEntity.noContent().build();
    }

    // is om data erin te zetten(posten)
    @PostMapping
    public ResponseEntity<TelevisionDto> createTelevision(@Valid @RequestBody TelevisionInputDto televisionInputDto, BindingResult br){
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

    @PutMapping("/{id}/remotecontrollers")
    public ResponseEntity<TelevisionDto> assignRemoteControllerToTelevision(@PathVariable long id, @RequestBody IdInputDto input) {
        // De methode neemt het ID van de televisie (uit de URI) en het ID van de afstandsbediening (uit de request body).
        televisionService.assignRemoteControllerToTelevision(id, input.id);
        // ResponseEntity.noContent() retourneert een HTTP 204 No Content-response.
        // Dit wordt gebruikt om aan te geven dat de operatie met succes is uitgevoerd, maar er is geen specifieke inhoud om terug te sturen.
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/cimodules")
    public ResponseEntity<TelevisionDto> assignCIModuleToTelevision(@PathVariable  long id, @RequestBody IdInputDto input) {
        televisionService.assignCIModuleToTelevision(id, input.id);
        return ResponseEntity.noContent().build();
    }
}
