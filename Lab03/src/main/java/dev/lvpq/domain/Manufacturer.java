package dev.lvpq.domain;


import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.List;

@Table(name="Manufacturer")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Manufacturer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String location;
    Integer employeeNumber;
    @OneToMany(mappedBy = "manufacturer")
    List<Phone> phones;

    public void print() {
        System.out.println("id = " + id + ", name = " + name +  ", location = " + location + ", employeeNumber = " + employeeNumber);
    }
}
