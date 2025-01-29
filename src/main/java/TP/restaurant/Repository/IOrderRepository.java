package TP.restaurant.Repository;

import TP.restaurant.Entity.Client;
import TP.restaurant.Entity.Dish;
import TP.restaurant.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IOrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findByClientId(Long id);
    Order findByClientAndValidatedFalse(Client client);
}
