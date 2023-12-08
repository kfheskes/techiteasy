package nl.novi.techiteasy.service;

import nl.novi.techiteasy.dtos.remotecontroller.RemoteControllerDto;
import nl.novi.techiteasy.dtos.remotecontroller.RemoteControllerInputDto;
import nl.novi.techiteasy.models.RemoteController;
import nl.novi.techiteasy.repositories.RemoteControllerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RemoteControllerService {

    private RemoteControllerRepository remoteControllerRepos;

    public RemoteControllerService(RemoteControllerRepository remoteControllerRepos){
        this.remoteControllerRepos = remoteControllerRepos;
    }

    public List<RemoteControllerDto> getAllRemoteControllers(){
        List<RemoteController> remoteControllerList = remoteControllerRepos.findAll();
        List<RemoteControllerDto> remoteControllerDtoList = new ArrayList<>();
        for (RemoteController remoteController : remoteControllerList) {
            remoteControllerDtoList.add(convertRemoteControllerToRemoteControllerDto(remoteController));
        }
        return remoteControllerDtoList;
    }

    public RemoteControllerDto createRemoteController(RemoteControllerInputDto createRemoteController){
        RemoteController remoteControllerInputDto = dtoTransferToRemoteController(createRemoteController);
        remoteControllerRepos.save(remoteControllerInputDto);
        return convertRemoteControllerToRemoteControllerDto(remoteControllerInputDto);
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

}
