package TP.restaurant.Controller;

import TP.restaurant.Entity.Order;
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
    public Order placeOrder(@RequestBody Map<String, Long> requestBody) throws Exception {
        Long clientId = requestBody.get("clientId");
        Long dishId = requestBody.get("dishId");
        return orderService.placeOrder(clientId, dishId);
    }

    @PutMapping("/{id}")
    public Order updateOrder(@PathVariable Long orderId, @RequestParam Long clientId, @RequestParam Long dishId) throws Exception {
        return orderService.updateOrder(orderId, clientId, dishId);
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long orderId) throws Exception {
        orderService.deleteOrder(orderId);
    }
}
