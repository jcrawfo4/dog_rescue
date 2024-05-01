package dog_rescue.controller;

import dog_rescue.Dto.LocationDto;
import dog_rescue.service.LocationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/dog_rescue")
@Slf4j
public class LocationController {

    @Autowired
    private LocationService locationService;

    @PostMapping("/location")
    @ResponseStatus(code = HttpStatus.CREATED)
    public LocationDto createLocation(@RequestBody LocationDto locationDto){
        log.info("Creating location {}", locationDto);
        return locationService.saveLocation(locationDto);
    }



    @GetMapping("/location/{locationId}")
    public LocationDto getLocationById(@PathVariable Integer locationId){
        log.info("Getting location by id {}", locationId);
        return locationService.getLocationById(locationId);
    }

    @GetMapping("/locations")
    public List<LocationDto> retrieveAllLocations(){
        log.info("Retrieving all locations");
        return locationService.retrieveAllLocations();
    }

    @PutMapping("/location/{locationId}")
    public LocationDto updateLocation(@PathVariable Integer locationId, @RequestBody LocationDto locationDto){
        locationDto.setLocationId(locationId);
        log.info("Updating location by id {}", locationDto);
        return locationService.saveLocation(locationDto);
    }

    @DeleteMapping("/location/{locationId}")
    public Map<String, String> deleteLocation(@PathVariable Integer locationId ) {
        log.info("Deleting location by id {}", locationId);
        locationService.deleteLocation(locationId);
        return Map.of("message", "Location with id = " + locationId+" deleted successfully");
    }
}
