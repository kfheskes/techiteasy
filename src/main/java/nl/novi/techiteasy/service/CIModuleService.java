package nl.novi.techiteasy.service;

import nl.novi.techiteasy.dtos.cimodule.CIModuleDto;
import nl.novi.techiteasy.dtos.cimodule.CIModuleInputDto;
import nl.novi.techiteasy.exceptions.RecordNotFoundException;
import nl.novi.techiteasy.models.CIModule;
import nl.novi.techiteasy.models.Television;
import nl.novi.techiteasy.repositories.CIModuleRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CIModuleService {
private final CIModuleRepository CIModuleRepos;

    public CIModuleService(CIModuleRepository ciModuleRepos) {
        CIModuleRepos = ciModuleRepos;
    }

    public CIModule dtoTransferToCIModule(CIModuleInputDto CIModuleInputDto){
        CIModule CIModule = new CIModule();

        CIModule.setName(CIModuleInputDto.getName());
        CIModule.setType(CIModuleInputDto.getType());
        CIModule.setPrice(CIModuleInputDto.getPrice());

        return CIModule;
    }

    public CIModuleDto CIModuleTransferToCIModuleDto(CIModule CIModule){
        CIModuleDto CIModuleDto = new CIModuleDto();

        CIModuleDto.name = CIModule.getName();
        CIModuleDto.type = CIModule.getType();
        CIModuleDto.price = CIModule.getPrice();

        return CIModuleDto;
    }

    public CIModuleDto createCIModule(CIModuleInputDto createCIModule){
        CIModule CIModuleInputDto = dtoTransferToCIModule(createCIModule);
        CIModuleRepos.save(CIModuleInputDto);
        return CIModuleTransferToCIModuleDto(CIModuleInputDto);
    }

    public CIModuleDto getCIModuleId(long id){
        Optional<CIModule> CIModuleId = CIModuleRepos.findById(id);
        if (CIModuleId.isPresent()) {
            CIModule CIModule = CIModuleId.get();
            CIModuleDto CIModuleDto = CIModuleTransferToCIModuleDto(CIModule);

            return CIModuleDto;
        } else {
            throw new RecordNotFoundException("No CIModule found with id ");
        }
    }
    public List<CIModuleDto> getAllCIModules (){
        List<CIModule> CIModuleList = CIModuleRepos.findAll();
        List<CIModuleDto> CIModuleListDto = new ArrayList<>();

        for (CIModule CIModule : CIModuleList) {
            CIModuleListDto.add(CIModuleTransferToCIModuleDto(CIModule));
        }
        return CIModuleListDto;
    }

    public CIModuleDto updateCIModule(long id, CIModule CIModule){
        Optional<CIModule> CIModuleId = CIModuleRepos.findById(id);
        if (CIModuleId.isEmpty()){
            throw new RecordNotFoundException("No CIModule found with id ");
        } else {
            CIModule changeCIModule = CIModuleId.get();

            changeCIModule.setName(CIModule.getName());
            changeCIModule.setType(CIModule.getType());
            changeCIModule.setPrice(CIModule.getPrice());

            CIModule saveCIModule = CIModuleRepos.save(changeCIModule);

            return CIModuleTransferToCIModuleDto(saveCIModule);
        }
    }

    public void deleteCIModule(long id) {
        CIModuleRepos.deleteById(id);
    }

}
