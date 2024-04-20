package dog_rescue.controller.model;


import dog_rescue.entity.Breed;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BreedDto {
    private Integer breedId;
    private String breedName;

    public BreedDto(Breed breed) {
        this.breedId = breed.getBreedId();
        this.breedName = breed.getBreedName();
    }

    public Breed toBreed(){
        Breed breed = new Breed();
        breed.setBreedId(breedId);
        breed.setBreedName(breedName);
        return breed;
    }

}
