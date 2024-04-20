package dog_rescue.controller.model;

import dog_rescue.entity.Dog;
import dog_rescue.entity.Location;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
public class LocationDto {

    private Integer locationId;
    private String businessName;
    private String streetAddress;
    private String city;
    private String state;
    private String zip;
    private String phone;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<DogDto> dogs = new HashSet<>();

    public LocationDto(Location location) {
        this.locationId = location.getLocationId();
        this.businessName = location.getBusinessName();
        this.streetAddress = location.getStreetAddress();
        this.city = location.getCity();
        this.state = location.getState();
        this.zip = location.getZip();
        this.phone = location.getPhone();

        for(Dog dog: location.getDogs()){
            this.dogs.add(new DogDto(dog));
        }
    }


    public LocationDto(Integer locationId, String businessName, String streetAddress, String city, String state, String zip, String phone) {
        this.locationId = locationId;
        this.businessName = businessName;
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.phone = phone;

    }
    public Location toLocation(){
        Location location = new Location();
        location.setLocationId(locationId);
        location.setBusinessName(businessName);
        location.setStreetAddress(streetAddress);
        location.setCity(city);
        location.setState(state);
        location.setZip(zip);
        location.setPhone(phone);
        for(DogDto dogDto: dogs){
            location.getDogs().add(dogDto.toDog());
        }
        return location;
    }
}
