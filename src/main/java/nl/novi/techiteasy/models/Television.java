package nl.novi.techiteasy.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter

@Entity
@Table(name = "televisions")
public class Television {
    @Id
    @GeneratedValue
    private long id;
    private String type;
   private String brand;
   private String name;
   private Double price;
   private Double availableSize;
   private Double refreshRate;
   private String screenType;
   private String screenQuality;
   private Boolean smartTv;
   private Boolean wifi;
   private Boolean voiceControl;
   private Boolean hdr;
   private Boolean bluetooth;
   private Boolean ambiLight;
   private Integer originalStock;
   private Integer sold;

   private LocalDate saleDate;
   private LocalDate purchaseDate;

   public Television(){}

    public Television(long id, String type, String brand, String name, Double price, Double availableSize, Double refreshRate, String screenType, String screenQuality, Boolean smartTv, Boolean wifi, Boolean voiceControl, Boolean hdr, Boolean bluetooth, Boolean ambiLight, Integer originalStock, Integer sold, LocalDate saleDate, LocalDate purchaseDate) {
        this.id = id;
        this.type = type;
        this.brand = brand;
        this.name = name;
        this.price = price;
        this.availableSize = availableSize;
        this.refreshRate = refreshRate;
        this.screenType = screenType;
        this.screenQuality = screenQuality;
        this.smartTv = smartTv;
        this.wifi = wifi;
        this.voiceControl = voiceControl;
        this.hdr = hdr;
        this.bluetooth = bluetooth;
        this.ambiLight = ambiLight;
        this.originalStock = originalStock;
        this.sold = sold;
        this.saleDate = saleDate;
        this.purchaseDate = purchaseDate;
    }



}
