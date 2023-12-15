package nl.novi.techiteasy.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter

@Entity
@Table(name = "CI-Module")
public class CIModule {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

   private String name;
   private String type;
   private Double price;

   @OneToMany(mappedBy = "CIModule")
   private Set<Television> televisions = new HashSet<>();

    public CIModule(long id, String name, String type, Double price) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.price = price;
    }

    public CIModule(){
    }

}
