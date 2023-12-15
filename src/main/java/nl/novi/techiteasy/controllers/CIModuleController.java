package nl.novi.techiteasy.controllers;

import jakarta.validation.Valid;
import nl.novi.techiteasy.dtos.cimodule.CIModuleDto;
import nl.novi.techiteasy.dtos.cimodule.CIModuleInputDto;
import nl.novi.techiteasy.exceptions.ValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static nl.novi.techiteasy.controllers.ControllerHelper.checkForBindingResult;

@RequestMapping("/cimodules")
@RestController
public class CIModuleController {

@PostMapping
    public ResponseEntity<CIModuleDto> createCIModule(@Valid @RequestBody CIModuleInputDto CIModuleInputDto, BindingResult br){
    if (br.hasFieldErrors()) {
        throw new ValidationException(checkForBindingResult(br));
} else


}
