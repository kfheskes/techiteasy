package nl.novi.techiteasy.controllers;


import nl.novi.techiteasy.dtos.remotecontroller.RemoteControllerDto;
import nl.novi.techiteasy.dtos.remotecontroller.RemoteControllerInputDto;
import nl.novi.techiteasy.exceptions.RecordNotFoundException;
import nl.novi.techiteasy.exceptions.ValidationException;
import nl.novi.techiteasy.models.RemoteController;
import nl.novi.techiteasy.service.RemoteControllerService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.net.URI;
import java.util.List;

import static nl.novi.techiteasy.controllers.ControllerHelper.checkForBindingResult;

@RequestMapping("/remotecontrollers")
@RestController
public class RemoteControllerController {

    private final RemoteControllerService remoteControllerService;

    public RemoteControllerController(RemoteControllerService remoteControllerService) {
        this.remoteControllerService = remoteControllerService;
    }

    @GetMapping
    public ResponseEntity<List<RemoteControllerDto>> getAllTelevisions(){
        return ResponseEntity.ok(remoteControllerService.getAllRemoteControllers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RemoteControllerDto> getRemoteControllerId(@PathVariable long id){
        if (id > 0) {
            RemoteControllerDto rcDto = remoteControllerService.getRemoteControllerId(id);
            return ResponseEntity.ok(rcDto);
        } else {
            throw new RecordNotFoundException("there is no television");
        }
    }

    @PostMapping
    public ResponseEntity<RemoteControllerDto> addRc (@RequestBody RemoteControllerInputDto remoteControllerInputDto, BindingResult br) {
        if (br.hasFieldErrors()){
            throw new ValidationException(checkForBindingResult(br));
        } else {
            RemoteControllerDto savedRC = remoteControllerService.createRemoteController(remoteControllerInputDto);
            URI uri = URI.create(
                    ServletUriComponentsBuilder
                            .fromCurrentRequest()
                            .path("/" + savedRC.id).toUriString());
            return ResponseEntity.created(uri).body(savedRC);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<RemoteControllerDto> changeRC(@PathVariable long id, @RequestBody RemoteController remoteController) {
        RemoteControllerDto changeRC = remoteControllerService.updateRC(id, remoteController);

        return ResponseEntity.ok().body(changeRC);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RemoteController> deleteRC(@PathVariable long id) {
        remoteControllerService.deleteRC(id);
        return ResponseEntity.noContent().build();
    }

}
