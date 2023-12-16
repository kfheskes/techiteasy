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



}
