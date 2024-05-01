package dog_rescue.controller;

import dog_rescue.Dto.DogDto;
import dog_rescue.service.DogService;
import dog_rescue.service.LocationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dog_rescue")
@Slf4j
public class DogController {

    @Autowired
    LocationService locationService;

    @Autowired
    DogService dogService;

    @PostMapping("/dog")
    @ResponseStatus(code = HttpStatus.CREATED)
    public DogDto createDog(@RequestBody DogDto dogDto){
        log.info("Creating dog {}", dogDto);
        return dogService.saveDog(dogDto);
    }


}
