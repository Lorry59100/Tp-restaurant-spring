package TP.restaurant.Service;

import TP.restaurant.Dto.OrderDto;
import TP.restaurant.Entity.Client;
import TP.restaurant.Entity.Dish;
import TP.restaurant.Entity.Order;
import TP.restaurant.Exception.ClientNotFoundException;
import TP.restaurant.Exception.DishNotFoundException;
import TP.restaurant.Exception.EmptyOrderException;
import TP.restaurant.Exception.OrderNotFoundException;
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

    public OrderDto placeOrder(Long clientId, Long dishId){
        Client client = clientRepository.findById(clientId).orElseThrow(() -> new ClientNotFoundException("Client inconnu"));
        Dish dish = dishRepository.findById(dishId).orElseThrow(() -> new DishNotFoundException("Plat inconnu"));
        Order existingOrder = orderRepository.findByClientAndValidatedFalse(client);
        if (existingOrder != null) {
            existingOrder.getDishes().add(dish);
            Order savedOrder = orderRepository.save(existingOrder);
            return orderMapper.entityToDto(savedOrder);
        } else {
            Order newOrder = new Order();
            newOrder.setClient(client);
            newOrder.setDishes(List.of(dish));
            Order savedOrder = orderRepository.save(newOrder);
            return orderMapper.entityToDto(savedOrder);
        }
    }


    public OrderDto validateOrder(Long clientId, Long orderId){
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new OrderNotFoundException("Commande inconnue"));
        if (order.getDishes().isEmpty()) {
            throw new EmptyOrderException("La commande ne peut pas être validée car elle ne contient aucun plat");
        }
        order.setValidated(true);
        Order savedOrder = orderRepository.save(order);
        return orderMapper.entityToDto(savedOrder);
    }

    public OrderDto clearOrder(Long clientId, Long orderId){
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new OrderNotFoundException("Commande inconnue"));
        List<Dish> emptyDishes = new ArrayList<>();
        order.setDishes(emptyDishes);
        order.setValidated(false);
        Order savedOrder = orderRepository.save(order);
        return orderMapper.entityToDto(savedOrder);
    }

    public OrderDto getHistoric(Long clientId) {
        Order order = orderRepository.findByClientId(clientId).orElseThrow(()-> new ClientNotFoundException("Client inconnu"));
        return orderMapper.entityToDto(order);
    }
}
