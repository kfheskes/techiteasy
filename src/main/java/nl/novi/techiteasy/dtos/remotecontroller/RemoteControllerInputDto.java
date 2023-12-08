package nl.novi.techiteasy.dtos.remotecontroller;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RemoteControllerInputDto {

    public String compatibleWith;
    public String batteryType;
    public String name;
    public String brand;
    public Double price;
    public Integer originalStock;

}
