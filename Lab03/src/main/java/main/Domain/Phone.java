package main.Domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "Phone")
@NoArgsConstructor
@ToString
public class Phone {
    @Id
    private Long ID;

    @Column(nullable = false)
    @Size(min = 3, max = 128)
    private String Name;
    @Column(nullable = false)
    private int Price;
    @Column(nullable = false)
    private String Color;
    private String Country;
    private  int Quantity;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manufacture_id")
    private Manufacture manufacture;

    public Phone(Long ID, String Name, int Price, String Color, String Country, int Quantity) {
        this.ID = ID;
        this.Name = Name;
        this.Price = Price;
        this.Color = Color;
        this.Country = Country;
        this.Quantity = Quantity;
    }
}
