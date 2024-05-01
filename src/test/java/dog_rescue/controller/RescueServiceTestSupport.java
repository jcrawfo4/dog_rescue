package dog_rescue.controller;

import dog_rescue.Dto.LocationDto;
import dog_rescue.entity.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.jdbc.JdbcTestUtils;

import java.util.LinkedList;
import java.util.List;

public class RescueServiceTestSupport {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private LocationController locationController;

    public static final String LOCATION = "location";// @formatter:off
    //@formatter:off
    private LocationDto insertAddress1() {
        return new LocationDto(
                (int) 1,
                "Darylls Doggy Daycare",
                "123 Main St",
                "Anytown",
                "NY",
                "12345",
                "555-1212"
        );
    }
    private LocationDto insertAddress2() {

        return new LocationDto(
                (int)2,
                "Paws and Claws",
                "456 Elm St",
                "Othertown",
                "NY",
                "12345",
                "555-1234"
        );
    }
    LocationDto updateAddress1 = new LocationDto(
            (int)1,
            "Darylls Doggy Daycare",
            "123 E. Main St",
            "Edwardsville",
            "IL",
            "12345",
            "618-555-1212"
    );
    //@formatter:on

    protected int rowsInLocationTable() {
        return JdbcTestUtils.countRowsInTable(jdbcTemplate, LOCATION);
    }

    protected LocationDto insertLocation(LocationDto locationDto) {
        Location location = locationDto.toLocation();
        LocationDto clone = new LocationDto(location);
        clone.setLocationId(null);
        return locationController.createLocation(clone);
    }


    protected LocationDto buildInsertLocation(int which){
        return which == 1 ? insertAddress1() : insertAddress2();
    }

    protected LocationDto getLocationById(Integer locationId) {
        return locationController.getLocationById(locationId);
    }

    protected List<LocationDto> retrieveAllLocations() {
        return locationController.retrieveAllLocations();
    }
    protected List<LocationDto> insertTwoLocations() {
        LocationDto location1 = insertLocation(insertAddress1());
        LocationDto location2 = insertLocation(insertAddress2());
        return List.of(location1, location2);
    }
    protected List<LocationDto> sorted(List<LocationDto> lst) {
        List<LocationDto> data = new LinkedList<>(lst);
        data.sort((l1, l2) -> (int) l1.getLocationId()
                .compareTo((int)l2.getLocationId() ));
        return lst;
    }

    protected LocationDto updateLocation(LocationDto locationDto) {
       return locationController.updateLocation(locationDto.getLocationId(), locationDto);
    }
    protected LocationDto buildUpdateLocation() {
        return updateAddress1;
    }

    protected void deleteLocation(LocationDto locationDto) {
        locationController.deleteLocation(locationDto.getLocationId());
    }

}
