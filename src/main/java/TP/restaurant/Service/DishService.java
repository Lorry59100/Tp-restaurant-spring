package TP.restaurant.Service;

import TP.restaurant.Entity.Dish;
import TP.restaurant.Repository.IDishRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DishService {

    private final IDishRepository dishRepository;

    public DishService(IDishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    public Dish addDish(Dish dish) {
        return dishRepository.save(dish);
    }

    public Dish updateDish(Long id, Dish dishDetails) throws Exception {
        Optional<Dish> optionalDish = dishRepository.findById(id);
        if(optionalDish.isPresent()) {
            Dish dish = optionalDish.get();
            if(dishDetails.getName() != null) {
                dish.setName(dishDetails.getName());
            }
            if(dishDetails.getPrice() != null) {
                dish.setPrice(dishDetails.getPrice());
            }
            return dishRepository.save(dish);
        } else {
            throw new Exception("Plat inconnu");
        }
    }

    public String deleteDish(Long id) throws Exception {
        Optional<Dish> optionalDish = dishRepository.findById(id);
        if (optionalDish.isPresent()) {
        Dish dish = optionalDish.get();
        dishRepository.delete(dish);
        return "Plat supprimé";
        } else {
            throw new Exception("Plat inconnu");
        }
    }
}
