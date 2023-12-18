package nl.novi.techiteasy.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
@Table(name = "remote_controller")
public class RemoteController {

    @Id
    @GeneratedValue
    private long id;

    private String compatibleWith;
    private String batteryType;
    private String name;
    private String brand;
    private Double price;
    private int originalStock;

    // dit is de target dit komt door mappedBy
    @OneToOne(mappedBy = "remoteController")
    private Television television;

    public RemoteController(long id, String compatibleWith, String batteryType, String name, String brand, Double price, int originalStock) {
        this.id = id;
        this.compatibleWith = compatibleWith;
        this.batteryType = batteryType;
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.originalStock = originalStock;
    }

    public RemoteController(){
    }

}
