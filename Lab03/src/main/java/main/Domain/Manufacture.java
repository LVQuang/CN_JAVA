package main.Domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.Size;
import java.util.List;

@Data
@Entity
@Table(name="Manufacturer")
@NoArgsConstructor
@ToString
public class Manufacture {
    @Id
    private Long ID;

    @Column(nullable = false)
    @Size(min = 3, max = 128)
    private String Name;

    @Column(nullable = false)
    private String Location;

    private int Employee;

    @OneToMany(mappedBy = "manufacture")
    private List<Phone> phone;

    public Manufacture(Long ID, String Name, String Location, int Employee) {
        this.ID = ID;
        this.Name = Name;
        this.Location = Location;
        this.Employee = Employee;
    }
}
