package nl.novi.techiteasy.controllers;

import nl.novi.techiteasy.models.Television;
import nl.novi.techiteasy.repositories.TelevisionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

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
    Television savedTelevison = televisionRepository.save(television);

        return ResponseEntity.created(null).body(savedTelevison);
    }

    //Put is om te bewerken
    @PutMapping("televisions/{id}")
    public ResponseEntity<Television> changeTelevision(@PathVariable int id, @RequestBody Television television ){
        if (id >= 0){

        }
        else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/televisions/{id}")
    public ResponseEntity<Television> deleteTelevision(@PathVariable int id){
        return ResponseEntity.noContent().build();
    }

}
