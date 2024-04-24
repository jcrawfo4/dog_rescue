package dog_rescue.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer locationId;
    private String businessName;
    private String streetAddress;
    private String city;
    private String state;
    private String zip;
    private String phone;


    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "location")
    private Set<Dog> dogs = new HashSet<>();
}
