package TP.restaurant.Mapper;

import TP.restaurant.Dto.DishDto;
import TP.restaurant.Entity.Dish;
import org.springframework.stereotype.Component;

@Component
public class DishMapper {

    public DishDto entityToDto(Dish dish) {
        DishDto dishDto = new DishDto();
        dishDto.setName(dish.getName());
        dishDto.setPrice(dish.getPrice());
        return dishDto;
    }

    public Dish dtoToEntity(DishDto dishDto) {
        Dish dish = new Dish();
        dish.setName(dishDto.getName());
        dish.setPrice(dishDto.getPrice());
        return dish;
    }
}
