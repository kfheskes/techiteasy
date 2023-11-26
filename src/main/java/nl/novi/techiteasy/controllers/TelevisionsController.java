package nl.novi.techiteasy.controllers;

import nl.novi.techiteasy.exceptions.RecordNotFoundException;
import nl.novi.techiteasy.models.Television;
import nl.novi.techiteasy.repositories.TelevisionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
public class TelevisionsController {

    @Autowired
    private TelevisionRepository televisionRepository;

    @GetMapping("/televisions")
    public ResponseEntity<List<Television>> getAllTelevisions(){
        List<Television> televisions = televisionRepository.findAll();

        return ResponseEntity.ok().body(televisions);
    }

    @GetMapping("/televisions/{id}")
    public ResponseEntity<Television> getTelevision(@PathVariable long id){
        Television televisions = televisionRepository.getReferenceById(id);

        return ResponseEntity.ok(televisions);
    }

    // is om data erin te zetten(posten)
    @PostMapping("/televisions")
    public ResponseEntity<Television> addTelevision(@RequestBody Television television){
    Television savedTelevision = televisionRepository.save(television);

        return ResponseEntity.created(null).body(savedTelevision);
    }

    //Put is om te bewerken
    @PutMapping("televisions/{id}")
    public ResponseEntity<Optional<Television>> changeTelevision(@PathVariable long id, @RequestBody Television television ){
       Optional<Television> changeTelevision = televisionRepository.findById(id);

        if (changeTelevision.isEmpty()){
             throw new RecordNotFoundException("No television found with id" + id);

        }
        else {
            Television changeTelevision1 = changeTelevision.get();
            changeTelevision1.setType(television.getType());
            changeTelevision1.setBrand(television.getBrand());
            changeTelevision1.setName(television.getName());
            changeTelevision1.setPrice(television.getPrice());
            changeTelevision1.setAvailableSize(television.getAvailableSize());
            changeTelevision1.setRefreshRate(television.getRefreshRate());
            changeTelevision1.setScreenType(television.getScreenType());
            changeTelevision1.setScreenType(television.getScreenType());
            changeTelevision1.setSmartTv(television.getSmartTv());
            changeTelevision1.setWifi(television.getWifi());
            changeTelevision1.setVoiceControl(television.getVoiceControl());
            changeTelevision1.setHdr(television.getHdr());
            changeTelevision1.setBluetooth(television.getBluetooth());
            changeTelevision1.setAmbiLight(television.getAmbiLight());
            changeTelevision1.setOriginalStock(television.getOriginalStock());
            changeTelevision1.setSold(television.getSold());
            changeTelevision1.setSaleDate(television.getSaleDate());
            changeTelevision1.setPurchaseDate(television.getPurchaseDate());
            // sla de gewijzigde data op:
            Television savedTelevision = televisionRepository.save(changeTelevision1);

            return ResponseEntity.ok().body(changeTelevision);
        }

    }

    @DeleteMapping("/televisions/{id}")
    public ResponseEntity<Television> deleteTelevision(@PathVariable long id){
        televisionRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

}
