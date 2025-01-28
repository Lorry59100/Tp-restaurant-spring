package TP.restaurant.Controller;

import TP.restaurant.Entity.Dish;
import TP.restaurant.Service.DishService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dishes")
public class DishController {

    private final DishService dishService;

    public DishController(DishService dishService) {
        this.dishService = dishService;
    }

    @PostMapping
    public ResponseEntity<Dish> addDish(@RequestBody Dish dish) {
        Dish createdDish = dishService.addDish(dish);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDish);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Dish> updateDish(@PathVariable Long id, @RequestBody Dish dish) throws Exception {
        Dish updatedDish = dishService.updateDish(id, dish);
        return ResponseEntity.status(HttpStatus.CREATED).body(updatedDish);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDish(@PathVariable Long id) throws Exception {
        String result = dishService.deleteDish(id);
        return ResponseEntity.ok(result);
    }
}
