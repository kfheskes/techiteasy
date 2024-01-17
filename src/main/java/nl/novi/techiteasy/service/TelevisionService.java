package nl.novi.techiteasy.service;

import nl.novi.techiteasy.dtos.television.TelevisionDto;
import nl.novi.techiteasy.dtos.television.TelevisionInputDto;
import nl.novi.techiteasy.dtos.wallbracket.WallBracketDto;
import nl.novi.techiteasy.exceptions.RecordNotFoundException;
import nl.novi.techiteasy.models.CIModule;
import nl.novi.techiteasy.models.RemoteController;
import nl.novi.techiteasy.models.Television;
import nl.novi.techiteasy.models.WallBracket;
import nl.novi.techiteasy.repositories.CIModuleRepository;
import nl.novi.techiteasy.repositories.RemoteControllerRepository;
import nl.novi.techiteasy.repositories.TelevisionRepository;
import nl.novi.techiteasy.repositories.WallBracketRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TelevisionService {
//Repositories worden gebruikt om gegevens uit een database te halen, en services worden gebruikt om de bedrijfslogica van de applicatie te implementeren.
private final TelevisionRepository repos;
private final RemoteControllerRepository remoteControllerRepos;
private final RemoteControllerService remoteControllerService;

private final CIModuleService ciModuleService;
private final CIModuleRepository ciModuleRepository;

private final WallBracketService wallBracketService;

private final WallBracketRepository wallBracketRepository;
    public TelevisionService(TelevisionRepository repos, RemoteControllerRepository remoteControllerRepos, RemoteControllerService remoteControllerService, CIModuleService ciModuleService, CIModuleRepository ciModuleRepository, WallBracketService wallBracketService, WallBracketRepository wallBracketRepository) {
        this.repos = repos;
        this.remoteControllerRepos = remoteControllerRepos;
        this.remoteControllerService = remoteControllerService;
        this.ciModuleService = ciModuleService;
        this.ciModuleRepository = ciModuleRepository;
        this.wallBracketService = wallBracketService;
        this.wallBracketRepository = wallBracketRepository;
    }

    // onderstaande haalt data(television) uit de database models Television via de service naar de gebruiker TelevisionDto
    // List<TelevisionDto> wordt gebruikt om geconverteerde DTO-objecten op te slaan voordat ze geroutineerd worden
    public List<TelevisionDto> getAllTelevisions (){
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
    //return waarde is TelevisionDto(outputDTO) de input voor deze methode is TelevisionInputDto. dan ga je het transformeren van een dto naar module Television. dit sla je op in de data base met de repos en dan return je he met behulp van de methode convertTelevisionToTelevisonDto
    public TelevisionDto createTelevision(TelevisionInputDto createTelevisionInputDto) {
        Television tvInputDto = dtoTransferToTelevision(createTelevisionInputDto);
        repos.save(tvInputDto);
        return convertTelevisionToTelevisionDto(tvInputDto);
    }



//    public List<TelevisionDto> transferTvListToDtoList(List<Television> televisions) {
//        List<TelevisionDto> tvDtoList = new ArrayList<>();
//
//        for (Television tv : televisions) {
//            TelevisionDto dto = convertTelevisionToTelevisionDto(tv);
//
//            if (tv.getRemoteController() != null) {
//                dto.setRemoteController(remoteControllerService.convertRemoteControllerToRemoteControllerDto(tv.getRemoteController()));
//            }
//            tvDtoList.add(dto);
//        }
//
//        return tvDtoList;
//    }


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

// eerst controleert of het RemotController niet gelijk is aan null
        if (television.getRemoteController() != null) {
//            remoteControllerService.convertRemoteControllerToRemoteControllerDto(television.getRemoteController()): Als de voorwaarde waar is, wordt deze lijn uitgevoerd. Het roept de methode convertRemoteControllerToRemoteControllerDto aan op de remoteControllerService. Deze methode zet een RemoteController-object om naar een RemoteControllerDto-object. Het resulterende RemoteControllerDto-object wordt vervolgens ingesteld op het overeenkomstige veld (remoteControllerDto) van het TelevisionDto-object (televisionDto).
            televisionDto.setRemoteControllerDto(remoteControllerService.convertRemoteControllerToRemoteControllerDto(television.getRemoteController()));
        }

        if (television.getCIModule() != null){
            televisionDto.setCiModuleDto(ciModuleService.CIModuleTransferToCIModuleDto(television.getCIModule()));
        }
        // Controleert of de Set van WallBracket-objecten niet gelijk is aan null
        if (television.getWallBrackets() != null) {
            // Als de Set van WallBracket-objecten niet null is, wordt een nieuwe Set van WallBracketDto-objecten gemaakt (wallBracketDtos).
            Set<WallBracketDto> wallBracketDtos = new HashSet<>();
            // Voor elk WallBracket-object in de Set van WallBracket-objecten, wordt de methode transferToDto aangeroepen
            // om het WallBracket-object om te zetten naar een WallBracketDto-object.
            // Het resulterende WallBracketDto-object wordt toegevoegd aan de Set wallBracketDtos.
            for (WallBracket wallBracket : television.getWallBrackets()){
                wallBracketDtos.add(wallBracketService.transferToDto(wallBracket));
            }

            // De Set van WallBracketDto-objecten (wallBracketDtos) wordt ingesteld op het overeenkomstige veld (wallBrackets)
            // van het TelevisionDto-object (televisionDto).
            televisionDto.setWallBrackets(wallBracketDtos);
        }

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
        // De methode findById zoekt naar een televisie in de repository(model) op basis van het opgegeven id.
        // Het resultaat wordt verpakt in een Optional omdat het resultaat mogelijk leeg kan zijn.
        Optional<Television> televisionId = repos.findById(id);
        // Controleer of er een televisie is gevonden op basis van het opgegeven id.
        if (televisionId.isPresent()) {
            // Als er een televisie is gevonden, krijgen we het Television-object uit de Optional.
            Television tv = televisionId.get();
            TelevisionDto dto = convertTelevisionToTelevisionDto(tv);
            // Nu roepen we de methode convertTelevisionToTelevisionDto aan om het Television-object
            // om te zetten naar een TelevisionDto-object voordat het wordt geretourneerd.
            return dto;
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

    // geeft TelevisonDto aan omdat je ook de gegevens wilt presenteren aan een de gebruiker.
    // long id: is id van de televisie die moet worden bijgewerkt. Het wordt gebruikt om de juiste televisie in de database te lokaliseren. Het ID is een unieke waarde die elk televisieobject identificeert.
    //Television television: Dit is het televisieobject met bijgewerkte gegevens. In de methode wordt dit object gebruikt om de bestaande televisiegegevens bij te werken. De velden van dit object bevatten de nieuwe waarden die moeten worden ingesteld voor het bijbehorende televisie-ID.
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

    // Deze methode wijst een afstandsbediening toe aan een televisie op basis van de opgegeven ID's.
    public void assignRemoteControllerToTelevision(long televisionId, Long remoteControllerId) {
        // Optioneel ophalen van de televisie op basis van de gegeven ID.
        Optional<Television> optionalTelevision = repos.findById(televisionId);
        // Optioneel ophalen van de afstandsbediening op basis van de gegeven ID.
        Optional<RemoteController> optionalRemoteController = remoteControllerRepos.findById(remoteControllerId);
        // Controleer of zowel de televisie als de afstandsbediening zijn gevonden.
        if (optionalTelevision.isPresent() && optionalRemoteController.isPresent()) {
            // Haal de daadwerkelijke objecten op uit de optionele objecten.
            Television television = optionalTelevision.get();
            RemoteController remoteController = optionalRemoteController.get();
            // Wijs de afstandsbediening toe aan de televisie. door de methode 'setRemoteConroller' aan te roepen van het object 'Television'met als argument 'remoteController'
            television.setRemoteController(remoteController);
            // Sla de gewijzigde televisie op in de repository.
            repos.save(television);
        } else {
            // Gooi een RecordNotFoundException als een van beide objecten niet is gevonden.
            throw new RecordNotFoundException("No television found with id " + televisionId);
        }
    }

    public void assignCIModuleToTelevision(long televisionId, long ciModuleID){
        Optional<Television> optionalTelevision = repos.findById(televisionId);
        Optional<CIModule> optionalCIModule = ciModuleRepository.findById(ciModuleID);
        if(optionalTelevision.isPresent() && optionalCIModule.isPresent()){
            Television television = optionalTelevision.get();
            CIModule ciModule = optionalCIModule.get();
            television.setCIModule(ciModule);
            repos.save(television);
        } else {
            throw new RecordNotFoundException("No television found with id with ci-module");
        }
    }

    public void assignWallBracketToTelevision(long televisionId, long wallBracketID){
        Optional<Television> optionalTelevision = repos.findById(televisionId);
        Optional<WallBracket> optionalWallBracket = wallBracketRepository.findById(wallBracketID);

        if(optionalTelevision.isPresent() && optionalWallBracket.isPresent()){
            Television television = optionalTelevision.get();
            WallBracket wallBracket = optionalWallBracket.get();
            television.getWallBrackets().add(wallBracket);
            repos.save(television);
        } else {
            throw new RecordNotFoundException("No television found with id with ci-module");
        }
    }


//    public TelevisionDto transferToDto(Television television) {
//        TelevisionDto dto = new TelevisionDto();
//
//        dto.setId(television.getId());
//        dto.setAmbiLight(television.getAmbiLight());
//        dto.setAvailableSize(television.getAvailableSize());
//        dto.setBluetooth(television.getBluetooth());
//        dto.setBrand(television.getBrand());
//        dto.setHdr(television.getHdr());
//        dto.setName(television.getName());
//        dto.setOriginalStock(television.getOriginalStock());
//        dto.setPrice(television.getPrice());
//        dto.setRefreshRate(television.getRefreshRate());
//        dto.setScreenQuality(television.getScreenQuality());
//        dto.setScreenType(television.getScreenType());
//        dto.setSmartTv(television.getSmartTv());
//        dto.setSold(television.getSold());
//        dto.setType(television.getType());
//        dto.setVoiceControl(television.getVoiceControl());
//        dto.setWifi(television.getWifi());
//
//        if (television.getRemoteController() != null) {
//            dto.setRemoteController(remoteControllerService.transferToDto(television.getRemoteController()));
//        }
//
//        if (television.getCiModule() != null) {
//            dto.setCiModule(ciModuleService.transferToDto(television.getCiModule()));
//        }
//
//        if (television.getWallBrackets() != null) {
//            Set<WallBracketDto> wallBracketDtos = new HashSet<>();
//            for (WallBracket wallBracket : television.getWallBrackets()) {
//                wallBracketDtos.add(wallBracketService.transferToDto(wallBracket));
//            }
//            dto.setWallBracket(wallBracketDtos);
//        }
//
//        return dto;

    //        if(television.getRemoteController() != null){
//            RemoteController remoteController = television.getRemoteController();
//            RemoteControllerDto remoteControllerDto = new RemoteControllerDto();
//            remoteControllerDto.id = remoteController.getId();
//            remoteControllerDto.brand = remoteController.getBrand();
//            remoteControllerDto.name = remoteController.getName();
//            remoteControllerDto.price = remoteController.getPrice();
//            remoteControllerDto.originalStock = remoteController.getOriginalStock();
//            remoteControllerDto.batteryType = remoteController.getBatteryType();
//            remoteControllerDto.compatibleWith = remoteController.getCompatibleWith();
//            televisionDto.remoteControllerDto = remoteControllerDto;
    }




