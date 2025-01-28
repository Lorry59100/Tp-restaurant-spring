package TP.restaurant.Service;

import TP.restaurant.Dto.OrderDto;
import TP.restaurant.Entity.Client;
import TP.restaurant.Entity.Dish;
import TP.restaurant.Entity.Order;
import TP.restaurant.Mapper.OrderMapper;
import TP.restaurant.Repository.IClientRepository;
import TP.restaurant.Repository.IDishRepository;
import TP.restaurant.Repository.IOrderRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    private final IOrderRepository orderRepository;
    private final IClientRepository clientRepository;
    private final IDishRepository dishRepository;
    private final OrderMapper orderMapper;

    public OrderService(IOrderRepository orderRepository, IClientRepository clientRepository, IDishRepository dishRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.clientRepository = clientRepository;
        this.dishRepository = dishRepository;
        this.orderMapper = orderMapper;
    }

    public OrderDto placeOrder(Long clientId, Long dishId) throws Exception {
        Client client = clientRepository.findById(clientId).orElseThrow(() -> new Exception("Client inconnu"));
        Dish dish = dishRepository.findById(dishId).orElseThrow(() -> new Exception("Plat inconnu"));
        Order order = new Order();
        order.setClient(client);
        order.setDishes(List.of(dish));
        Order savedOrder = orderRepository.save(order);
        return orderMapper.entityToDto(savedOrder);
    }

    public OrderDto validateOrder(Long clientId, Long orderId) throws Exception {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new Exception("Commande inconnue"));
        if (!order.getClient().getId().equals(clientId)) {
            throw new Exception("Client non autorisé à valider cette commande");
        }
        if (order.getDishes().isEmpty()) {
            throw new Exception("La commande ne peut pas être validée car elle ne contient aucun plat");
        }
        order.setValidated(true);
        Order savedOrder = orderRepository.save(order);
        return orderMapper.entityToDto(savedOrder);
    }

    public OrderDto clearOrder(Long clientId, Long orderId) throws Exception {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new Exception("Commande inconnue"));
        if (!order.getClient().getId().equals(clientId)) {
            throw new Exception("Client non autorisé à vider cette commande");
        }
        List<Dish> emptyDishes = new ArrayList<>();
        order.setDishes(emptyDishes);
        order.setValidated(false);
        Order savedOrder = orderRepository.save(order);
        return orderMapper.entityToDto(savedOrder);
    }
}
