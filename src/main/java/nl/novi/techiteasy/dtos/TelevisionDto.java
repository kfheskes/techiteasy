package nl.novi.techiteasy.dtos;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class TelevisionDto {

    public long id;
    @NotNull(message = "Type is required")
    public String type;
    public String brand;
    @NotBlank
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
