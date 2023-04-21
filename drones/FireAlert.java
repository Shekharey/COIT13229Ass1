package assesment.drones;
//Shekhar Sharma (12134685)
import java.io.Serializable;

public class FireAlert implements Serializable {
    private String message;

    public FireAlert(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "FireAlert{" +
                "message='" + message + '\'' +
                '}';
    }
}

