package dog_rescue.Dto;

import dog_rescue.entity.Breed;
import dog_rescue.entity.Dog;
import dog_rescue.entity.Location;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
public class    DogDto {
    private Integer dogId;
    private String name;
    private String age;
    private String color;
    private Set<BreedDto> breeds = new HashSet<>();

    @Getter
    @Setter
    private Integer locationId;


    public DogDto(Dog dog) {
        this.dogId = dog.getDogId();
        this.name = dog.getName();
        this.age = dog.getAge();
        this.color = dog.getColor();

        for(Breed breed: dog.getBreeds()){
            this.breeds.add( new BreedDto(breed));
        }
    }



    public Dog toDog() {
        Dog dog = new Dog();
        dog.setDogId(dogId);
        dog.setName(name);
        dog.setAge(age);
        dog.setColor(color);

        Location location = new Location();
        location.setLocationId(locationId);
        dog.setLocation(location);

        for(BreedDto breedDto: breeds){
            dog.getBreeds().add(breedDto.toBreed());
        }
        return dog;
    }
}