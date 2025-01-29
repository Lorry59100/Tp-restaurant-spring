package TP.restaurant.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "Vous devez renseigner un nom de plat")
    @NotBlank(message = "Le nom du plat ne peut être vide")
    private String name;

    @NotNull(message = "Vous devez renseigner un prix")
    private Double price;

    @ManyToMany(mappedBy = "dishes")
    @JsonIgnoreProperties("dishes")
    private List<Order> orders;
}
