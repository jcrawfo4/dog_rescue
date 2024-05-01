package dog_rescue.service;

import dog_rescue.Dto.DogDto;
import dog_rescue.dao.DogDao;
import dog_rescue.dao.LocationDao;
import dog_rescue.entity.Dog;
import dog_rescue.entity.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

@Service
public class DogService {

    @Autowired
    private DogDao dogDao;
    @Autowired
    private LocationDao locationDao;



    public DogDto saveDog(DogDto dogDto) {
        if(dogDto.getLocationId() == null ){
            throw new IllegalArgumentException("Location ID is required to create a dog");
        }
        Dog dog = dogDto.toDog();

        Location location = locationDao.findById(dogDto.getLocationId())
                .orElseThrow( () -> new NoSuchElementException("Location with ID = " + dogDto.getLocationId() + " was not found"));
        dog.setLocation(location);
        Dog databaseDog = dogDao.save(dog);
        return new DogDto(databaseDog);
    }

    public DogDto getDogById(Integer dogId) {
        Dog dog = findDogById(dogId);
        return new DogDto(dog);
    }

    private Dog findDogById(Integer dogId) {
        return dogDao.findById(dogId).orElseThrow();
    }

    public Map<String, String> deleteDog(Integer dogId) {
        dogDao.deleteById(dogId);
        return Map.of("message", "Student with id = "+ dogId+" deleted successfully.");
    }

    public DogDto updateDog(Integer dogId, DogDto dogDto){
        dogDto.setDogId(dogId);
        return saveDog(dogDto);
    }

    private Dog findOrCreateDog(Integer dogId, String name, Integer age) {
        Dog dog;
        if(Objects.isNull(dogId)){
            Optional<Dog> dogOptional = dogDao.findByNameAndAge(name, age);
            if(dogOptional.isPresent()){
                throw new DuplicateKeyException("Dog with name = " + name+ "that is "+ age+ "years old already exists");
            }
            else{
                dog = new Dog();
            }
        }
        else{
           dog = findDogById(dogId);
        }
        return dog;
    }



}
