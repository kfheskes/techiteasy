package nl.novi.techiteasy.service;

import nl.novi.techiteasy.dtos.TelevisionDto;
import nl.novi.techiteasy.exceptions.RecordNotFoundException;
import nl.novi.techiteasy.models.Television;
import nl.novi.techiteasy.repositories.TelevisionRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TelevisionService {

private final TelevisionRepository repos;

    public TelevisionService(TelevisionRepository repos) {
        this.repos = repos;
    }
// achter public geeft aan dat er een object van het type TelevisionDto zal retouneren.
    // (TelevisonDto createTelvisonDto) is een parameterlijst van de methode. het geeft aan dat de methode een parameter verwacht van het type TelevisonDto en deze parmeter wordt beinnen de methode aangeduid als 'createTelevisonDto'
    public TelevisionDto createTelevision (TelevisionDto createTelevisionDto){
        Television television = new Television();
        television.setType(createTelevisionDto.type);
        television.setBrand(createTelevisionDto.brand);
        television.setName(createTelevisionDto.name);
        television.setPrice(createTelevisionDto.price);
        television.setAvailableSize(createTelevisionDto.availableSize);
        television.setRefreshRate(createTelevisionDto.refreshRate);
        television.setScreenType(createTelevisionDto.screenType);
        television.setScreenType(createTelevisionDto.screenType);
        television.setScreenQuality(createTelevisionDto.screenQuality);
        television.setSmartTv(createTelevisionDto.smartTv);
        television.setWifi(createTelevisionDto.wifi);
        television.setVoiceControl(createTelevisionDto.voiceControl);
        television.setHdr(createTelevisionDto.hdr);
        television.setBluetooth(createTelevisionDto.bluetooth);
        television.setAmbiLight(createTelevisionDto.ambiLight);
        television.setOriginalStock(createTelevisionDto.originalStock);
        television.setSold(createTelevisionDto.sold);
        television.setSaleDate(createTelevisionDto.saleDate);
        television.setPurchaseDate(createTelevisionDto.purchaseDate);
        repos.save(television);
        createTelevisionDto.id = television.getId();
        return createTelevisionDto;
    }

    // onderstaande haalt data(television) uit de database models Television via de service naar de gebruiker TelevisionDto
    // List<TelevisionDto> wordt gebruikt om geconverteerde DTO-objecten op te slaan voordat ze geroutineerd worden
    public List<TelevisionDto> getAllTelevision (){
        List<Television> televisionList = repos.findAll();
        List<TelevisionDto> televisionDtoList = new ArrayList<>();
        // for-each lus doorloopt elke element van Television object en stop het in televisionList(collectie van Television objecten) elke element wordt van televisionList wordt toegewezen aan television.
        for (Television television : televisionList) {
            //De notatie (television) geeft aan dat je een argument aan een methode doorgeeft. In dit geval betekent (television) dat het television-object wordt doorgegeven aan de methode convertTelevisionToTelevisionDto.
            //
            //Dus, convertTelevisionToTelevisionDto(television) betekent dat de methode wordt aangeroepen met het television-object als invoer, en het resultaat wordt toegevoegd aan de lijst televisionDtoList.
            televisionDtoList.add(convertTelevisionToTelevisionDto(television));
        }
            return televisionDtoList;
    }

    // onderstaande methode neemt een object van het model Television en convert het naar een DTO van het type TeacherDto
    // television is een parameter om Television aan te spreken en de data daar uit te halen.
    public TelevisionDto convertTelevisionToTelevisionDto(Television television) {
        // wordt een nieuw object gemaakt om data op te slaan televisionDto
        TelevisionDto televisionDto = new TelevisionDto();
        televisionDto.id = television.getId();
        televisionDto.type = television.getType();
        televisionDto.brand = (television.getBrand());
        televisionDto.name = (television.getName());
        televisionDto.price = (television.getPrice());
        televisionDto.availableSize = (television.getAvailableSize());
        televisionDto.refreshRate = (television.getRefreshRate());
        televisionDto.screenType = (television.getScreenType());
        televisionDto.screenQuality = (television.getScreenQuality());
        televisionDto.smartTv = (television.getSmartTv());
        televisionDto.wifi = (television.getWifi());
        televisionDto.voiceControl = (television.getVoiceControl());
        televisionDto.hdr = (television.getHdr());
        televisionDto.bluetooth = (television.getBluetooth());
        televisionDto.ambiLight = (television.getAmbiLight());
        televisionDto.originalStock = (television.getOriginalStock());
        televisionDto.sold = (television.getSold());
        televisionDto.saleDate = (television.getSaleDate());
        televisionDto.purchaseDate = (television.getPurchaseDate());

        return televisionDto;
    }

    public TelevisionDto getTelevisionId(long id) {
        // De methode findById zoekt naar een televisie in de repository op basis van het opgegeven id.
        // Het resultaat wordt verpakt in een Optional omdat het resultaat mogelijk leeg kan zijn.
        Optional<Television> televisionId = repos.findById(id);
        // Controleer of er een televisie is gevonden op basis van het opgegeven id.
        if (televisionId.isPresent()) {
            // Als er een televisie is gevonden, krijgen we het Television-object uit de Optional.
            Television tv = televisionId.get();
            // Nu roepen we de methode convertTelevisionToTelevisionDto aan om het Television-object
            // om te zetten naar een TelevisionDto-object voordat het wordt geretourneerd.
            return convertTelevisionToTelevisionDto(tv);
        } else {
            // Als er geen televisie is gevonden, wordt een RecordNotFoundException gegooid.
            // Dit is een aangepaste uitzondering die wordt gebruikt om aan te geven dat er geen record is gevonden.
            throw new RecordNotFoundException("No television found with id ");
        }
    }

    // het is een void omdat de methode geen resultaat hoeft terug te geven. kan het zelfde doen als getTelevisionId om dan terug te geven welke verwijderd is.
    public void deleteTelevision(long id) {
        // De methode wordt gebruikt om een televisie te verwijderen op basis van het opgegeven id.
        // Als er geen televisie wordt gevonden met het opgegeven id, heeft deze methode geen effect (doet niets).
        repos.deleteById(id);
    }

        public void updateTelevision(long id, Television television){
        Optional<Television> getTelevision = repos.findById(id);
        if (getTelevision.isEmpty()){
            throw new RecordNotFoundException("No television found with id ");
        } else {
            Television changeTelevision1 = getTelevision.get();
            changeTelevision1.setType(television.getType());
            changeTelevision1.setBrand(television.getBrand());
            changeTelevision1.setName(television.getName());
            changeTelevision1.setPrice(television.getPrice());
            changeTelevision1.setAvailableSize(television.getAvailableSize());
            changeTelevision1.setRefreshRate(television.getRefreshRate());
            changeTelevision1.setScreenType(television.getScreenType());
            changeTelevision1.setScreenQuality(television.getScreenQuality());
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
            Television returnTelevision = repos.save(changeTelevision1);
            return convertTelevisionToTelevisionDto (returnTelevision);
        }
        }

}
