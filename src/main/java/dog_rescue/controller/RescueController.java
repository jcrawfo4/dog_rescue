package dog_rescue.controller;

import dog_rescue.controller.model.LocationDto;
import dog_rescue.service.RescueService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dog_rescue")
@Slf4j
public class RescueController {

    @Autowired
    private RescueService rescueService;
    @PostMapping("/location")
    @ResponseStatus(code = HttpStatus.CREATED)
    public LocationDto createLocation(@RequestBody LocationDto locationDto){
        log.info("Creating location {}", locationDto);
        return rescueService.saveLocation(locationDto);
    }
}
