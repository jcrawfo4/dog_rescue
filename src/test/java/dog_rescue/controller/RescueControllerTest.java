package dog_rescue.controller;

import dog_rescue.DogRescueApplication;
import dog_rescue.Dto.LocationDto;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = DogRescueApplication.class)
@ActiveProfiles("test")
@Sql(scripts = {"classpath:schema.sql", "classpath:data.sql"})
@SqlConfig(encoding = "utf-8")
public class RescueControllerTest extends RescueServiceTestSupport {

    @Test
    void testInsertLocation() {
        //given a location
        LocationDto request = buildInsertLocation(1);
        LocationDto expected = buildInsertLocation(1);
        //when: the location is inserted to the location table
        LocationDto actual = insertLocation(request);
        //then: the location returned is what is expected
        assertThat(actual).isEqualTo(expected);
        //and: the location is in the location table
        assertThat(rowsInLocationTable()).isOne();
    }

    @Test
    void testGetLocationById() {
        //given: a location in the location table
        LocationDto locationDto = insertLocation(buildInsertLocation(1));
        LocationDto expected = buildInsertLocation(1);
        //when: the location is retrieved by id
        LocationDto actual = getLocationById(locationDto.getLocationId());

        //then: the location returned is what is expected
        assertThat(actual).isEqualTo(expected);
        assertThat(rowsInLocationTable()).isOne();
    }

    @Test
    void testRetrieveAllLocations() {
        //given: two locations in the location table
        List<LocationDto> expected = insertTwoLocations();
        //when: all locations are retrieved
        List<LocationDto> actual = retrieveAllLocations();
        Integer expectedSize = 2;
        //then: the number of locations returned is what is expected
        assertThat(sorted(actual)).isEqualTo(expected);
        assertThat(rowsInLocationTable()).isEqualTo(expectedSize);
    }

    @Test
    void testUpdateLocation() {
        //given: a location in the update request
        insertLocation(buildInsertLocation(1));
        LocationDto expected = buildUpdateLocation();
        //when: the location is updated
        LocationDto actual = updateLocation(expected);
        //then: the location in the location table is what is expected
        assertThat(actual).isEqualTo(expected);
        assertThat(rowsInLocationTable()).isOne();
    }

    @Test
    void testDeleteLocation() {
        //given: a location in the location table
        LocationDto locationDto = insertLocation(buildInsertLocation(2));
        //when: the location is deleted
        deleteLocation(locationDto);
        //then: the location is no longer in the location table
        assertThat(rowsInLocationTable()).isZero();
    }

}
