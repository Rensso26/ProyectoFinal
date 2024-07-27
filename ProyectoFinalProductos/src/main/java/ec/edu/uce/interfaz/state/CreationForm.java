package ec.edu.uce.interfaz.state;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

@Entity
public class CreationForm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double timeEnsable;
    private double timePinter;
    private double timePackaging;


    @OneToOne
    @JoinColumn(name = "toy_id")
    @JsonBackReference
    private Toy toy;


    public Long getIdToy() {
        return id;
    }

    public void setId(Long idToy) {
        this.id = idToy;
    }

    public Long getId() {
        return id;
    }

    public double getTimeEnsable() {
        return timeEnsable;
    }

    public void setTimeEnsable(double timeEnsable) {
        this.timeEnsable = timeEnsable;
    }

    public double getTimePinter() {
        return timePinter;
    }

    public void setTimePinter(double timePinter) {
        this.timePinter = timePinter;
    }

    public double getTimePackaging() {
        return timePackaging;
    }

    public void setTimePackaging(double timePackaging) {
        this.timePackaging = timePackaging;
    }

    public Toy getToy() {
        return toy;
    }

    public void setToy(Toy toy) {
        this.toy = toy;
    }
}
