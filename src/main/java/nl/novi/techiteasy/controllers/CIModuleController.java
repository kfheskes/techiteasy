package nl.novi.techiteasy.controllers;

import jakarta.validation.Valid;
import nl.novi.techiteasy.dtos.cimodule.CIModuleDto;
import nl.novi.techiteasy.dtos.cimodule.CIModuleInputDto;
import nl.novi.techiteasy.dtos.television.TelevisionDto;
import nl.novi.techiteasy.exceptions.ValidationException;
import nl.novi.techiteasy.models.CIModule;
import nl.novi.techiteasy.models.Television;
import nl.novi.techiteasy.service.CIModuleService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static nl.novi.techiteasy.controllers.ControllerHelper.checkForBindingResult;

@RequestMapping("/cimodules")
@RestController
public class CIModuleController {

    private final CIModuleService CIModuleService;

    public CIModuleController(nl.novi.techiteasy.service.CIModuleService ciModuleService) {
        CIModuleService = ciModuleService;
    }


    @PostMapping
    public ResponseEntity<CIModuleDto> createCIModule(@Valid @RequestBody CIModuleInputDto CIModuleInputDto, BindingResult br) {
    if (br.hasFieldErrors()) {
        throw new ValidationException(checkForBindingResult(br));
    } else {
        CIModuleDto savedCIModule = CIModuleService.createCIModule(CIModuleInputDto);
        URI uri = URI.create(
                ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/" + savedCIModule.id).toUriString());
        return ResponseEntity.created(uri).body(savedCIModule);
    }
}

@GetMapping
    public ResponseEntity<List<CIModuleDto>> getAllCIModules(){

    return ResponseEntity.ok(CIModuleService.getAllCIModules());
}

@GetMapping("/{id}")
    public ResponseEntity<CIModuleDto> getCIModule(@PathVariable long id){
        CIModuleDto ciModuleDto = CIModuleService.getCIModuleId(id);
        return ResponseEntity.ok(ciModuleDto);
}

    @PutMapping("/{id}")
    public ResponseEntity<CIModuleDto> changeTelevision(@PathVariable long id, @RequestBody CIModule CIModule ) {
        CIModuleDto changeCIModuleId = CIModuleService.updateCIModule(id, CIModule);

        return ResponseEntity.ok().body(changeCIModuleId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CIModule> deleteCIModule(@PathVariable long id){

        CIModuleService.deleteCIModule(id);

        return ResponseEntity.noContent().build();
    }



}
