package TP.restaurant.Service;

import TP.restaurant.Dto.DishDto;
import TP.restaurant.Entity.Dish;
import TP.restaurant.Mapper.DishMapper;
import TP.restaurant.Repository.IDishRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DishService {

    private final IDishRepository dishRepository;
    private final DishMapper dishMapper;

    public DishService(IDishRepository dishRepository, DishMapper dishMapper) {
        this.dishRepository = dishRepository;
        this.dishMapper = dishMapper;
    }

    public DishDto addDish(DishDto dishDto) {
        Dish dish = dishMapper.dtoToEntity(dishDto);
        Dish savedDish = dishRepository.save(dish);
        return dishMapper.entityToDto(savedDish);
    }

    public DishDto updateDish(Long id, DishDto dishDetails) throws Exception {
        Optional<Dish> optionalDish = dishRepository.findById(id);
        if(optionalDish.isPresent()) {
            Dish dish = optionalDish.get();
            if(dishDetails.getName() != null) {
                dish.setName(dishDetails.getName());
            }
            if(dishDetails.getPrice() != null) {
                dish.setPrice(dishDetails.getPrice());
            }
            Dish updatedDish = dishRepository.save(dish);
            return dishMapper.entityToDto(updatedDish);
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
