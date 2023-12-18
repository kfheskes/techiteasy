package nl.novi.techiteasy.dtos.wallbracket;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class WallBracketDto {


    public Long id;
    public String size;
    public Boolean adjustable;

    public String name;

    public Double price;

    public WallBracketDto(Long id, String size, Boolean adjustable, String name, Double price) {
        this.id = id;
        this.size = size;
        this.adjustable = adjustable;
        this.name = name;
        this.price = price;
    }

    public WallBracketDto(){

    }

}
