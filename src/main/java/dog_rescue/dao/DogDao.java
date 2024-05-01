package dog_rescue.dao;

import dog_rescue.entity.Dog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DogDao extends JpaRepository<Dog, Integer> {
}
