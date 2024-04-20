package dog_rescue.dao;

import dog_rescue.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationDao extends JpaRepository<Location, Integer> {
}
