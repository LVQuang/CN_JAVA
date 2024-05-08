package dev.lvpq.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    Double price;
    String color;
    String country;
    Integer quantity;
    @ManyToOne(fetch = FetchType.EAGER)
    Manufacturer manufacturer;

    public void print() {
        System.out.println("id = " + id + ", name = " + name + ", price = "
                + price + ", color = " + color + ", country = " + country + ", quantity = " + quantity
        );
    }
}
