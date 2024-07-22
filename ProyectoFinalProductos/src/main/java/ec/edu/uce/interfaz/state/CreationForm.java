package ec.edu.uce.interfaz.state;

import jakarta.persistence.*;

@Entity
public class CreationForm {

    @Id
    @Column(unique = true, nullable = false)
    private Long idToy;

    private double timeEnsable;
    private double timePinter;
    private double timePackaging;

    @OneToOne
    @JoinColumn(name = "idToy")
    private Toy toy;

    public Long getIdToy() {
        return idToy;
    }

    public void setIdToy(Long idToy) {
        this.idToy = idToy;
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
