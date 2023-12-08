package nl.novi.techiteasy.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter

@Entity
@Table(name = "Wall_Bracket")
public class WallBracket {

    @Id
    @GeneratedValue
    private long id;

    private String size;
    private boolean adjustable;
    private String name;
    private double price;

    @ManyToMany(mappedBy = "wallBrackets")
    private Set<Television> televisions = new HashSet<>();


    public WallBracket(Long id, String size, boolean adjustable, String name, double price) {
        this.id = id;
        this.size = size;
        this.adjustable = adjustable;
        this.name = name;
        this.price = price;
    }

    public WallBracket () {

    }



}
