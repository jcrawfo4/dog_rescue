package dog_rescue.controller;

import dog_rescue.controller.model.LocationDto;
import dog_rescue.entity.Location;
import dog_rescue.service.RescueService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/location/{locationId}")
    public LocationDto getLocationById(@PathVariable Integer locationId){
        log.info("Getting location by id {}", locationId);
        return rescueService.getLocationById(locationId);
    }

    @GetMapping("/locations")
    public List<LocationDto> retrieveAllLocations(){
        log.info("Retrieving all locations");
        return rescueService.retrieveAllLocations();
    }

    @PutMapping("/location/{locationId}")
    public LocationDto updateLocation(@PathVariable Integer locationId, @RequestBody LocationDto locationDto){
        locationDto.setLocationId(locationId);
        log.info("Updating location by id {}", locationDto);
        return rescueService.saveLocation(locationDto);
    }
}
