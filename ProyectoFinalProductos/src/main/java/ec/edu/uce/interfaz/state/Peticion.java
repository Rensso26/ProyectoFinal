package ec.edu.uce.interfaz.state;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Peticion {

    @Id
    private int id;

    private int cantidad;

}
