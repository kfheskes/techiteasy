package nl.novi.techiteasy.dtos.television;



import nl.novi.techiteasy.dtos.remotecontroller.RemoteControllerDto;
import nl.novi.techiteasy.models.RemoteController;

import java.time.LocalDate;

public class TelevisionDto {

    public long id;

    public String type;
    public String brand;

    public String name;

    public Double price;
    public Double availableSize;
    public Double refreshRate;
    public String screenType;
    public String screenQuality;
    public Boolean smartTv;
    public Boolean wifi;
    public Boolean voiceControl;
    public Boolean hdr;
    public Boolean bluetooth;
    public Boolean ambiLight;
    public Integer originalStock;
    public Integer sold;

    public LocalDate saleDate;
    public LocalDate purchaseDate;

    public RemoteControllerDto remoteController;


}
