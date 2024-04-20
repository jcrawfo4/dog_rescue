package dog_rescue.controller.model;

import dog_rescue.entity.Breed;
import dog_rescue.entity.Dog;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
public class DogDto {
    private Integer dogId;
    private String name;
    private String age;
    private String color;
    private Set<BreedDto> breeds = new HashSet<>();

    public DogDto(Dog dog) {
        this.dogId = dog.getDogId();
        this.name = dog.getName();
        this.age = dog.getAge();
        this.color = dog.getColor();

        for(Breed breed: dog.getBreeds()){
            this.breeds.add( new BreedDto(breed));
        }
        //location ?location
    }

    public Dog toDog() {
        Dog dog = new Dog();
        dog.setDogId(dogId);
        dog.setName(name);
        dog.setAge(age);
        dog.setColor(color);

        for(BreedDto breedDto: breeds){
            dog.getBreeds().add(breedDto.toBreed());
        }
        return dog;
    }
}