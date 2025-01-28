package TP.restaurant.Service;

import TP.restaurant.Entity.Client;
import TP.restaurant.Entity.Dish;
import TP.restaurant.Entity.Order;
import TP.restaurant.Repository.IClientRepository;
import TP.restaurant.Repository.IDishRepository;
import TP.restaurant.Repository.IOrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private final IOrderRepository orderRepository;
    private final IClientRepository clientRepository;
    private final IDishRepository dishRepository;

    public OrderService(IOrderRepository orderRepository, IClientRepository clientRepository, IDishRepository dishRepository) {
        this.orderRepository = orderRepository;
        this.clientRepository = clientRepository;
        this.dishRepository = dishRepository;
    }

    public Order placeOrder(Long clientId, Long dishId) throws Exception {
        Client client = clientRepository.findById(clientId).orElseThrow(()-> new Exception("Client inconnu"));
        Dish dish = dishRepository.findById(dishId).orElseThrow(()-> new Exception("Plat inconnu"));
        Order order = new Order();
        order.setClient(client);
        order.setDishes(List.of(dish));
        return orderRepository.save(order);
    }

    public Order updateOrder(Long orderId, Long clientId, Long dishId) throws Exception {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new Exception("Commande inconnue"));
        Client client = clientRepository.findById(clientId).orElseThrow(() -> new Exception("Client inconnu"));
        Dish dish = dishRepository.findById(dishId).orElseThrow(() -> new Exception("Plat inconnu"));
        order.setClient(client);
        order.setDishes(List.of(dish));
        return orderRepository.save(order);
    }

    public void deleteOrder(Long orderId) throws Exception {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new Exception("Commande inconnue"));
        orderRepository.delete(order);
    }
}
