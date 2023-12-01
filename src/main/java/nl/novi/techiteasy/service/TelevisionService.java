package nl.novi.techiteasy.service;

import nl.novi.techiteasy.dtos.TelevisionDto;
import nl.novi.techiteasy.models.Television;
import nl.novi.techiteasy.repositories.TelevisionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<TelevisionDto> getAllTelevision (TelevisionDto getAllTelevision){

        .

    }


}
