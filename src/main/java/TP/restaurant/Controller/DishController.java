package TP.restaurant.Controller;

import TP.restaurant.Dto.DishDto;
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
    public ResponseEntity<DishDto> addDish(@RequestBody DishDto dishDto) {
        DishDto createdDish = dishService.addDish(dishDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDish);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DishDto> updateDish(@PathVariable Long id, @RequestBody DishDto dishDto) throws Exception {
        DishDto updatedDish = dishService.updateDish(id, dishDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(updatedDish);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDish(@PathVariable Long id) throws Exception {
        String result = dishService.deleteDish(id);
        return ResponseEntity.ok(result);
    }
}
