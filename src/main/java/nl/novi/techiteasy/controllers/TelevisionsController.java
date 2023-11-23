package nl.novi.techiteasy.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class TelevisionsController {

    private ArrayList<String> tvlist = new ArrayList<>();

    @GetMapping("/televisions")
    public ResponseEntity<Object> getAllTelevisions(){
        return ResponseEntity.ok("televisions");
    }

    @GetMapping("/televisions/{id}")
    public ResponseEntity<Object> getTelevision(@PathVariable int id){
        return ResponseEntity.ok("televisions with id: " + id);
    }

    // is om te data erint te zetten(posten)
    @PostMapping("/televisions")
    public ResponseEntity<Object> addTelevision(@RequestBody String television){
        this.tvlist.add(television);

        return ResponseEntity.created(null).body("telelevision");
    }

    //Put is om te bewerken
    @PutMapping("televisions/{id}")
    public ResponseEntity<Object> changeTelevision(@PathVariable int id, @RequestBody String television ){
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/televisions/{id}")
    public ResponseEntity<Object> deleteTelevision(@PathVariable int id){
        return ResponseEntity.noContent().build();
    }

}
