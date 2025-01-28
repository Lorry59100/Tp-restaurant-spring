package TP.restaurant.Mapper;

import TP.restaurant.Dto.ClientDto;
import TP.restaurant.Dto.DishDto;
import TP.restaurant.Dto.OrderDto;
import TP.restaurant.Entity.Client;
import TP.restaurant.Entity.Dish;
import TP.restaurant.Entity.Order;
import TP.restaurant.Repository.IClientRepository;
import TP.restaurant.Repository.IDishRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper {

    private final IClientRepository clientRepository;
    private final IDishRepository dishRepository;
    private final ClientMapper clientMapper;
    private final DishMapper dishMapper;

    public OrderMapper(IClientRepository clientRepository, IDishRepository dishRepository, ClientMapper clientMapper, DishMapper dishMapper) {
        this.clientRepository = clientRepository;
        this.dishRepository = dishRepository;
        this.clientMapper = clientMapper;
        this.dishMapper = dishMapper;
    }

    public OrderDto entityToDto(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setClient(clientMapper.entityToDto(order.getClient()));
        orderDto.setDishes(order.getDishes().stream().map(dishMapper::entityToDto).collect(Collectors.toList()));
        orderDto.setValidated(order.isValidated());
        return orderDto;
    }

    public Order dtoToEntity(OrderDto orderDto) {
        Order order = new Order();
        order.setId(orderDto.getId());
        Client client = clientRepository.findByEmail(orderDto.getClient().getEmail()).orElseThrow(() -> new RuntimeException("Client inconnu"));
        order.setClient(client);
        List<Dish> dishes = orderDto.getDishes().stream()
                .map(dishDto -> dishRepository.findByName(dishDto.getName()).orElseThrow(() -> new RuntimeException("Plat inconnu")))
                .collect(Collectors.toList());
        order.setDishes(dishes);
        order.setValidated(orderDto.isValidated());
        return order;
    }
}
