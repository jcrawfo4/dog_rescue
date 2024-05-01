package dog_rescue.dao;

import dog_rescue.entity.Dog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DogDao extends JpaRepository<Dog, Integer> {
    Optional<Dog> findByNameAndAge(String name, Integer age);
}
