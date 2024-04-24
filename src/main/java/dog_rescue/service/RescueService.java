package dog_rescue.service;

import dog_rescue.controller.model.LocationDto;
import dog_rescue.dao.LocationDao;
import dog_rescue.entity.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
public class RescueService {

    @Autowired
    private LocationDao locationDao;

    @Transactional(readOnly = false)
    public LocationDto saveLocation(LocationDto locationDto){
        Location location = locationDto.toLocation();
        Location databaseLocation = locationDao.save(location);

        return new LocationDto(databaseLocation);
    }

    @Transactional(readOnly = true)
    public LocationDto getLocationById(Integer locationId) {
        Location location = findLocationById(locationId);
        return new LocationDto(location);
    }

    private Location findLocationById(Integer locationId) {
        return locationDao.findById(locationId)
                .orElseThrow(() -> new NoSuchElementException("Location with ID=" + locationId + " not found!"));
    }
}
