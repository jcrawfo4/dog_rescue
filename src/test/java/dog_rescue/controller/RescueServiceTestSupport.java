package dog_rescue.controller;

import dog_rescue.controller.RescueController;
import dog_rescue.controller.model.LocationDto;
import dog_rescue.entity.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.jdbc.JdbcTestUtils;

public class RescueServiceTestSupport {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private RescueController rescueController;

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
                2,
                "Paws and Claws",
                "456 Elm St",
                "Othertown",
                "NY",
                "12345",
                "555-1234"
        );
    }
    //@formatter:on

    protected int rowsInLocationTable() {
        return JdbcTestUtils.countRowsInTable(jdbcTemplate, LOCATION);
    }

    protected LocationDto insertLocation(LocationDto locationDto) {
        Location location = locationDto.toLocation();
        LocationDto clone = new LocationDto(location);
        clone.setLocationId(null);
        return rescueController.createLocation(clone);
    }


    protected LocationDto buildInsertLocation(int which){
        return which == 1 ? insertAddress1() : insertAddress2();
    }

    protected LocationDto getLocationById(Integer locationId) {
        return rescueController.getLocationById(locationId);
    }

}
