package dog_rescue.service;

import dog_rescue.controller.model.LocationDto;
import dog_rescue.dao.LocationDao;
import dog_rescue.entity.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
