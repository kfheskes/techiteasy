package nl.novi.techiteasy.service;

import nl.novi.techiteasy.dtos.wallbracket.WallBracketDto;
import nl.novi.techiteasy.exceptions.RecordNotFoundException;
import nl.novi.techiteasy.models.WallBracket;
import nl.novi.techiteasy.repositories.WallBracketRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WallBracketService {
    private final WallBracketRepository wallBracketRepository;

    public WallBracketService(WallBracketRepository wallBracketRepository) {
        this.wallBracketRepository = wallBracketRepository;
    }

    public WallBracket transferToWallBracket(WallBracketDto wallBracketDto){
        WallBracket wallBracket = new WallBracket();
        wallBracket.setId(wallBracketDto.getId());
        wallBracket.setName(wallBracketDto.getName());
        wallBracket.setSize(wallBracketDto.getSize());
        wallBracket.setAdjustable(wallBracketDto.getAdjustable());
        wallBracket.setPrice(wallBracketDto.getPrice());

        return wallBracket;
    }

    public WallBracketDto transferToDto(WallBracket wallBracket){
        WallBracketDto dto = new WallBracketDto();

        dto.setId(wallBracket.getId());
        dto.setName(wallBracket.getName());
        dto.setSize(wallBracket.getSize());
        dto.setAdjustable(wallBracket.getAdjustable());
        dto.setPrice(wallBracket.getPrice());

        return dto;
    }

    public WallBracketDto createWallbracket(WallBracketDto wallBracketDto) {
        WallBracket wallBracket = transferToWallBracket(wallBracketDto);
        wallBracketRepository.save(wallBracket);
        return transferToDto(wallBracket);
    }

    public List<WallBracketDto> getAllWallBrackets() {
        List<WallBracket> wallBracketList = wallBracketRepository.findAll();
        List<WallBracketDto> dtos = new ArrayList<>();
        for (WallBracket wb : wallBracketList) {
            dtos.add(transferToDto(wb));
        }
        return dtos;
    }

    public WallBracketDto getWallBracketId(long id) {
        Optional<WallBracket> wallBracket = wallBracketRepository.findById(id);
        if(wallBracket.isPresent()) {
            WallBracket wallBracket1 = wallBracket.get();
            WallBracketDto wallBracketDto = transferToDto(wallBracket1);

            return wallBracketDto;
        } else {
            throw new  RecordNotFoundException("No wallbracket found");
        }
    }

    public WallBracketDto updateWallBracket(Long id, WallBracketDto wallBracketDto) {
        Optional<WallBracket> wallBracketId = wallBracketRepository.findById(id);

        if (wallBracketId.isEmpty()) {
            throw new RecordNotFoundException("No wallbracket found");
        } else {
            WallBracket changeWallBracket = wallBracketId.get();

            changeWallBracket.setSize(wallBracketDto.getSize());
            changeWallBracket.setAdjustable(wallBracketDto.getAdjustable());
            changeWallBracket.setName(wallBracketDto.getName());
            changeWallBracket.setPrice(wallBracketDto.getPrice());
            return transferToDto(wallBracketRepository.save(changeWallBracket));
        }
    }

        public void deleteWallBracket ( long id){
            wallBracketRepository.deleteById(id);
        }


}
