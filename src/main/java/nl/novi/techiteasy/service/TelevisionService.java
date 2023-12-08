package nl.novi.techiteasy.service;

import nl.novi.techiteasy.dtos.television.TelevisionDto;
import nl.novi.techiteasy.dtos.television.TelevisionInputDto;
import nl.novi.techiteasy.exceptions.RecordNotFoundException;
import nl.novi.techiteasy.models.Television;
import nl.novi.techiteasy.repositories.TelevisionRepository;
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
    //public TelevisionDto createTelevision(TelevisionInputDto createTelevisionDto) {: Dit definieert een methode met de naam createTelevision die een object van het type TelevisionInputDto accepteert als invoerparameter en een object van het type TelevisionDto retourneert.
    //
    public TelevisionDto createTelevision(TelevisionInputDto createTelevisionDto) {
        Television tvInputDto = dtoTransferToTelevision(createTelevisionDto);
        repos.save(tvInputDto);
        return convertTelevisionToTelevisionDto(tvInputDto);
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

    public Television dtoTransferToTelevision(TelevisionInputDto dto){
        var television = new Television();

        television.setType(dto.getType());
        television.setBrand(dto.getBrand());
        television.setName(dto.getName());
        television.setPrice(dto.getPrice());
        television.setAvailableSize(dto.getAvailableSize());
        television.setRefreshRate(dto.getRefreshRate());
        television.setScreenType(dto.getScreenType());
        television.setScreenQuality(dto.getScreenQuality());
        television.setSmartTv(dto.getSmartTv());
        television.setWifi(dto.getWifi());
        television.setVoiceControl(dto.getVoiceControl());
        television.setHdr(dto.getHdr());
        television.setBluetooth(dto.getBluetooth());
        television.setAmbiLight(dto.getAmbiLight());
        television.setOriginalStock(dto.getOriginalStock());
        television.setSold(dto.getSold());
        television.setSaleDate(dto.getSaleDate());
        television.setPurchaseDate(dto.getPurchaseDate());

        return television;
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

    // geeft TelevisonDto aan omdat je ook de gegevens wilt presenteren aan een externe laag.
        public TelevisionDto updateTelevision(long id, Television television){
        // haalt het televisieobject op uit repos op basis van id het resultaat wordt in een optinal geweikkeld omdat het ook leeg kan zijn.
        Optional<Television> getTelevision = repos.findById(id);
        if (getTelevision.isEmpty()){
            throw new RecordNotFoundException("No television found with id ");
        } else {
            Television changeTelevision1 = getTelevision.get();
            // alle velden van het object Television wordt bijgewerkt
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
            // het bijgewerkte televisieobject wordt omgezet naar TelevisonDto
            return convertTelevisionToTelevisionDto (returnTelevision);
        }
        }

}
