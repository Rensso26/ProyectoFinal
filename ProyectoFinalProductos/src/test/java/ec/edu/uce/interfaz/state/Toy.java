package ec.edu.uce.interfaz.state;

import jakarta.persistence.*;

@Entity
public class Toy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String parts;
    private long price;
    private String color;

    @OneToOne(mappedBy = "toy")
    private CreationForm creationForm;

    @ManyToOne
    @JoinColumn(name = "category_name")
    private Category category;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParts() {
        return parts;
    }

    public void setParts(String parts) {
        this.parts = parts;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public CreationForm getCreationForm() {
        return creationForm;
    }

    public void setCreationForm(CreationForm creationForm) {
        this.creationForm = creationForm;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
