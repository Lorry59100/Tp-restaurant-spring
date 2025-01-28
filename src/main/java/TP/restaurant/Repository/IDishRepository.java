package TP.restaurant.Repository;

import TP.restaurant.Entity.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IDishRepository extends JpaRepository<Dish, Long> {
    Optional<Dish> findByName(String name);
}
