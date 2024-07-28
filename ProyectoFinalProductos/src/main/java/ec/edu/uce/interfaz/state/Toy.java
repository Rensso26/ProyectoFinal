package ec.edu.uce.interfaz.state;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Toy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String parts;
    private long price;
    private String color;
    private String description;
    private String image;

    @OneToOne(mappedBy = "toy", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private CreationForm creationForm;

    @ManyToOne
    @JoinColumn(name = "category_name")
    @JsonBackReference
    private Category category;

}
