package nl.novi.techiteasy.service;

import nl.novi.techiteasy.dtos.remotecontroller.RemoteControllerDto;
import nl.novi.techiteasy.dtos.remotecontroller.RemoteControllerInputDto;
import nl.novi.techiteasy.exceptions.RecordNotFoundException;
import nl.novi.techiteasy.models.RemoteController;
import nl.novi.techiteasy.repositories.RemoteControllerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RemoteControllerService {

    private final RemoteControllerRepository remoteControllerRepos;

    public RemoteControllerService(RemoteControllerRepository remoteControllerRepos){
        this.remoteControllerRepos = remoteControllerRepos;
    }

    public List<RemoteControllerDto> getAllRemoteControllers(){
        List<RemoteControllerDto> remoteControllerDtoList = new ArrayList<>();
        List<RemoteController> remoteControllerList = remoteControllerRepos.findAll();
        for (RemoteController remoteController : remoteControllerList) {
            remoteControllerDtoList.add(convertRemoteControllerToRemoteControllerDto(remoteController));
        }
        return remoteControllerDtoList;
    }

    public RemoteControllerDto convertRemoteControllerToRemoteControllerDto (RemoteController remoteController){
        RemoteControllerDto remoteControllerDto = new RemoteControllerDto();
        remoteControllerDto.id = remoteController.getId();
        remoteControllerDto.compatibleWith = remoteController.getCompatibleWith();
        remoteControllerDto.batteryType = remoteController.getBatteryType();
        remoteControllerDto.name = remoteController.getName();
        remoteControllerDto.brand = remoteController.getBrand();
        remoteControllerDto.price = remoteController.getPrice();
        remoteControllerDto.originalStock = remoteController.getOriginalStock();

        return remoteControllerDto;
    }

    public RemoteControllerDto getRemoteControllerId (long id) {
        Optional<RemoteController> remoteControllerId = remoteControllerRepos.findById(id);
        if (remoteControllerId.isPresent()){
            RemoteController rc = remoteControllerId.get();
            return convertRemoteControllerToRemoteControllerDto(rc);
        } else {
            throw new RecordNotFoundException("No remote controller id found");
        }
    }

    public RemoteControllerDto createRemoteController(RemoteControllerInputDto createRemoteControllerDto){
        RemoteController remoteControllerInputDto = dtoTransferToRemoteController(createRemoteControllerDto);
        remoteControllerRepos.save(remoteControllerInputDto);
        return convertRemoteControllerToRemoteControllerDto(remoteControllerInputDto);
    }


    public RemoteController dtoTransferToRemoteController(RemoteControllerInputDto dto) {
        var remoteController = new RemoteController();
        remoteController.setCompatibleWith(dto.getCompatibleWith());
        remoteController.setBatteryType(dto.getBatteryType());
        remoteController.setName(dto.getName());
        remoteController.setBrand(dto.getBrand());
        remoteController.setPrice(dto.getPrice());
        remoteController.setOriginalStock(dto.getOriginalStock());

        return remoteController;
    }

    public RemoteControllerDto updateRC(long id, RemoteController remoteController){
        Optional<RemoteController> getRC = remoteControllerRepos.findById(id);
        if (getRC.isEmpty()){
            throw new RecordNotFoundException("No remote controller found with id");
        } else {
            RemoteController changeRC = getRC.get();
            changeRC.setCompatibleWith(remoteController.getCompatibleWith());
            changeRC.setBatteryType(remoteController.getBatteryType());
            changeRC.setName(remoteController.getName());
            changeRC.setBrand(remoteController.getBrand());
            changeRC.setPrice(remoteController.getPrice());
            changeRC.setOriginalStock(remoteController.getOriginalStock());

            RemoteController returnRC = remoteControllerRepos.save(changeRC);
            return convertRemoteControllerToRemoteControllerDto(returnRC);
        }
    }


    public void deleteRC(long id) {
        remoteControllerRepos.deleteById(id);
    }

}
