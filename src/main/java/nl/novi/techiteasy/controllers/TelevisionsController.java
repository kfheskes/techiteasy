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
//    @PutMapping("televisions/{id}")
//    public ResponseEntity<Optional<Television>> changeTelevision(@PathVariable long id, @RequestBody Television television ){
//       Optional<Television> changeTelevision = televisionRepository.findById(id);
//
//        if (changeTelevision.isEmpty()){
//             throw new RecordNotFoundException("No television found with id" + id);
//
//        }
//        else {
//            Television changeTelevision1 = changeTelevision.get();
//            changeTelevision1.setType(television.getType());
//            changeTelevision1.setBrand(television.getBrand());
//            changeTelevision1.setName(television.getName());
//            changeTelevision1.setPrice(television.getPrice());
//            changeTelevision1.setAvailableSize(television.getAvailableSize());
//            changeTelevision1.setRefreshRate(television.getRefreshRate());
//            changeTelevision1.setScreenType(television.getScreenType());
//            changeTelevision1.setScreenType(television.getScreenType());
//            changeTelevision1.setSmartTv(television.getSmartTv());
//            changeTelevision1.setWifi(television.getWifi());
//            changeTelevision1.setVoiceControl(television.getVoiceControl());
//            changeTelevision1.setHdr(television.getHdr());
//            changeTelevision1.setBluetooth(television.getBluetooth());
//            changeTelevision1.setAmbiLight(television.getAmbiLight());
//            changeTelevision1.setOriginalStock(television.getOriginalStock());
//            changeTelevision1.setSold(television.getSold());
//            changeTelevision1.setSaleDate(television.getSaleDate());
//            changeTelevision1.setPurchaseDate(television.getPurchaseDate());
//            // sla de gewijzigde data op:
//            Television savedTelevision = televisionRepository.save(changeTelevision1);
//
//            return ResponseEntity.ok().body(changeTelevision);
//        }
//
//    }
//


}
