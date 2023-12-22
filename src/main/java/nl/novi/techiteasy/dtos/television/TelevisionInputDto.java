package nl.novi.techiteasy.dtos.television;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TelevisionInputDto {

    @NotNull(message = "Type is required")
   public String type;
    public String brand;
    @Size (max = 20, message = "name must be less than 20 characters")
    public String name;
    @Positive(message = "Price has to be more than 0")
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
    @Past
    public LocalDate saleDate;
    public LocalDate purchaseDate;


}
