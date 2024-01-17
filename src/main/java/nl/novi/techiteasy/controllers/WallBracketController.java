package nl.novi.techiteasy.controllers;

import jakarta.validation.Valid;
import nl.novi.techiteasy.dtos.wallbracket.WallBracketDto;
import nl.novi.techiteasy.service.WallBracketService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/wallbrackets")
@RestController
public class WallBracketController {
    private final WallBracketService wallBracketService;
    public WallBracketController(WallBracketService wallBracketService){
        this.wallBracketService = wallBracketService;
    }

    @GetMapping()
    public ResponseEntity<List<WallBracketDto>> getAllWallBrackets() {

        List<WallBracketDto> wallBrackets = wallBracketService.getAllWallBrackets();

        return ResponseEntity.ok(wallBrackets);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WallBracketDto> getWallBracket(@PathVariable("id") Long id) {

        WallBracketDto wallBracketDto = wallBracketService.getWallBracketId(id);

        return ResponseEntity.ok(wallBracketDto);
    }
//TODO: postmapping aanpassen
    @PostMapping()
    public ResponseEntity<WallBracketDto> createWallBracket(@Valid @RequestBody WallBracketDto dto, BindingResult br ) {
        WallBracketDto wallBracket = wallBracketService.createWallBracket(dto);
        return ResponseEntity.created(null).body(wallBracket);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteWallBracket(@PathVariable("id") Long id) {
        wallBracketService.deleteWallBracket(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<WallBracketDto> updateWallBracket(@PathVariable("id") Long id, @RequestBody WallBracketDto dto) {
        var returnDto = wallBracketService.updateWallBracket(id, dto);
        return ResponseEntity.ok(returnDto);
    }

    // Deze methode haalt alle televisies op die aan een bepaalde wallbracket gekoppeld zijn.
    // Deze methode maakt gebruikt van de televisionWallBracketService.
//    @GetMapping("/televisions/{wallBracketId}")
//    public ResponseEntity<Collection<TelevisionDto>> getTelevisionsByWallBracketId(@PathVariable("wallBracketId") Long wallBracketId){
//        return ResponseEntity.ok(televisionWallBracketService.getTelevisionsByWallBracketId(wallBracketId));
//    }

}
