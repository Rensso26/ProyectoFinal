package ec.edu.uce.interfaz.state;


import jakarta.persistence.*;

@Entity
public class ProductRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productName;
    private int fabricationTime; // Tiempo en segundos
    private String status;

    // Getters y setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getFabricationTime() {
        return fabricationTime;
    }

    public void setFabricationTime(int fabricationTime) {
        this.fabricationTime = fabricationTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
