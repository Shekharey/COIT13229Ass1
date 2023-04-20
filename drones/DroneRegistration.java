package assesment.drones;
//Shekhar Sharma (12134685)
import java.io.Serializable;

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
   

