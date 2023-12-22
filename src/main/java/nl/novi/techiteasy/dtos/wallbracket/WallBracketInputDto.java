package nl.novi.techiteasy.dtos.wallbracket;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class WallBracketInputDto {



    public String size;
    public Boolean adjustable;
    public String name;
    public Double price;
    public WallBracketInputDto(String size, Boolean adjustable, String name, Double price) {
        this.size = size;
        this.adjustable = adjustable;
        this.name = name;
        this.price = price;
    }

}
