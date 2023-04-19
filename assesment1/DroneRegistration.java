/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assesment.assesment1;
import java.io.Serializable;
/**
 *
 * @author Asus
 */
public class DroneRegistration implements Serializable {
    private String id;
    private String name;
    private DronePosition initialPosition;

    public DroneRegistration(String id, String name, DronePosition initialPosition) {
        this.id = id;
        this.name = name;
        this.initialPosition = initialPosition;
    }

    // Getters and setters
    public String getID() {
        return id;
    }

    public void setID(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DronePosition getInitialPosition() {
        return initialPosition;
    }

    public void setInitialPosition(DronePosition initialPosition) {
        this.initialPosition = initialPosition;
    }

    @Override
    public String toString() {
        return "DroneRegistration{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", initialPosition=" + initialPosition +
                '}';
    }
}
   

