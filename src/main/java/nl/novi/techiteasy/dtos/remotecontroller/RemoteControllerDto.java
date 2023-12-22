package nl.novi.techiteasy.dtos.remotecontroller;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RemoteControllerDto {


    public Long id;
    public String compatibleWith;
    public String batteryType;
    public String name;
    public String brand;
    public Double price;
    public Integer originalStock;




}
