package dog_rescue.service;

import dog_rescue.Dto.DogDto;
import dog_rescue.dao.DogDao;
import dog_rescue.dao.LocationDao;
import dog_rescue.entity.Dog;
import dog_rescue.entity.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

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
}
