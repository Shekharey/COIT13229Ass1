package assesment.drones;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class FireAlert implements Serializable {
    private static boolean fireDetected = false;

    private DronePosition dronePosition;
    private String message;

    public FireAlert(DronePosition dronePosition, String message) {
        this.dronePosition = dronePosition;
        this.message = message;
    }

    public DronePosition getDronePosition() {
        return dronePosition;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "FireAlert{" +
                "dronePosition=" + dronePosition +
                ", message='" + message + '\'' +
                '}';
    }
    
    public static ArrayList<FireAlert> checkForFire(ArrayList<DronePosition> dronePositions) {
        ArrayList<FireAlert> alerts = new ArrayList<>();
        for (DronePosition currentPosition : dronePositions) {
            // Check if a fire is detected at the current position
            if (currentPosition.getX() > 50 && currentPosition.getY() > 50) {
                fireDetected = true;
                System.out.println("Fire detected at position: [" + currentPosition.getX() + ", " + currentPosition.getY() + "]");
                alerts.add(new FireAlert(currentPosition, "Fire detected!"));
                reportFireToServer(currentPosition);
            } else {
                fireDetected = false;
            }
        }
        return alerts;
    }

    private static void reportFireToServer(DronePosition currentPosition) {
        Socket s = null;

        String serverIp = "localhost";
        int serverPort = 7896;
        String droneID = "9"; // fire flag
        DronePosition firePosition = currentPosition;

        try {
            // Connect to server
            s = new Socket(serverIp, serverPort);
            ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());

            // Send the fire flag and fire position to the server
            out.writeObject(droneID);
            out.writeObject(firePosition);
            System.out.println("Fire reported to server");

        } catch (UnknownHostException e) {
            System.err.println("Cannot find host: localhost");
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to: localhost");
        } finally {
            try {
                // Close the socket when the program terminates
                if (s != null) {
                    s.close();
                }
            } catch (IOException e) {
                System.err.println("Error closing socket: " + e.getMessage());
            }
        }
    }
}
