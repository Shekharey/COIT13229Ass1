package assesment.drones;
//Shekhar Sharma (12134685)
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class FireDetector {
    private static boolean fireDetected = false;

    public static boolean isFireDetected() {
        return fireDetected;
    }

    public static void fireDetected(DronePosition currentPosition) throws IOException {
        // Check if a fire is detected at the current position
        if (currentPosition.getX() > 50 && currentPosition.getY() > 50) {
            fireDetected = true;
            System.out.println("Fire detected at position: [" + currentPosition.getX() + ", " + currentPosition.getY() + "]");
            reportFireToServer(currentPosition);
        } else {
            fireDetected = false;
        }
    }

    private static void reportFireToServer(DronePosition currentPosition) throws IOException {
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
