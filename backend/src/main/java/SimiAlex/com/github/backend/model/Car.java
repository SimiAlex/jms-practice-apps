package SimiAlex.com.github.backend.model;

import jakarta.xml.bind.annotation.XmlRootElement;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@XmlRootElement
@Entity
@Table(name = "cars")
public class Car implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    private String make;
    private String model;
    private int year;

    public Car(Long id, String make, String model, int year) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.year = year;
    }

    public Car(){

    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                '}';
    }
}
