package assessment.assessment1;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    public static void main(String[] args) {
        String serverIp = "localhost";
        int serverPort = 7896;
        String droneID = args[0];
        String droneName = args[1];
        int initialX = Integer.parseInt(args[2]);
        int initialY = Integer.parseInt(args[3]);
        DronePosition initialPosition = new DronePosition(initialX, initialY);
        DroneRegistration droneRegistration = new DroneRegistration(droneID, droneName, initialPosition);
        
        try {
            // Connect to server
            Socket socket = new Socket(serverIp, serverPort);
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            out.writeObject(droneRegistration);
            System.out.println("Registration sent");

            // Receive the DronePosition object from the server and print it
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            DronePosition receivedPosition = (DronePosition) in.readObject();
            System.out.println("Received position data from server: " + receivedPosition);

            // Close the input/output streams and socket
            in.close();
            out.close();
            socket.close();
        } catch (UnknownHostException e) {
            System.err.println("Cannot find host: " + serverIp);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to: " + serverIp);
        } catch (ClassNotFoundException e) {
            System.err.println("Object received from unknown class");
        }
    }
}
