package assesment.assesment1;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    public static void main(String[] args) {
        Socket socket = null;

        try {
            // Get command line arguments
            String droneID = args[0];
            String droneName = args[1];
            int initialX = Integer.parseInt(args[2]);
            int initialY = Integer.parseInt(args[3]);

            // Create DronePosition object for initial position
            DronePosition initialPosition = new DronePosition(initialX, initialY);

            // Create DroneRegistration object with ID, name, and initial position
            DroneRegistration droneRegistration = new DroneRegistration(droneID, droneName, initialPosition);

            // Connect to server
            String serverIp = "localhost";
            int serverPort = 7896;
            socket = new Socket(serverIp, serverPort);

            // Send DroneRegistration object to server
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            out.writeObject(droneRegistration);
            System.out.println("Registration Sent");

            // Receive DronePosition object from server and print it
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            DronePosition receivedPosition = (DronePosition) in.readObject();
            System.out.println("Received position data from server: " + receivedPosition);
        } catch (UnknownHostException e) {
            System.err.println("Cannot find host: localhost");
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to: localhost");
        } catch (ClassNotFoundException e) {
            System.err.println("Object received from unknown class");
        } finally {
            try {
                // Close the input/output streams and socket
                if (socket != null) socket.close();
            } catch (IOException e) {
                System.err.println("Error closing streams/sockets: " + e.getMessage());
            }
        }
    }
}
