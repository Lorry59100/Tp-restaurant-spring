package TP.restaurant.Controller;

import TP.restaurant.Dto.OrderDto;
import TP.restaurant.Service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public OrderDto placeOrder(@RequestBody Map<String, Long> requestBody) throws Exception {
        Long clientId = requestBody.get("clientId");
        Long dishId = requestBody.get("dishId");
        return orderService.placeOrder(clientId, dishId);
    }

    @PutMapping("/{id}")
    public OrderDto validateOrder(@PathVariable Long id, @RequestBody Map<String, Long> requestBody) throws Exception {
        Long clientId = requestBody.get("clientId");
        return orderService.validateOrder(clientId, id);
    }

    @DeleteMapping("/{id}")
    public OrderDto clearOrder(@PathVariable Long id, @RequestBody Map<String, Long> requestBody) throws Exception {
        Long clientId = requestBody.get("clientId");
        return orderService.clearOrder(clientId, id);
    }
}
