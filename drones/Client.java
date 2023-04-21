package assesment.drones;
//Shekhar Sharma (12134685)
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Timer;
import java.util.TimerTask;

public class Client{
    private static final int SEND_POSITION_INTERVAL = 10000; // 10 seconds

    public static void main(String[] args) {
        Socket s = null;

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
            s = new Socket(serverIp, serverPort);
            ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());

            // Send the drone registration to the server
            out.writeObject(droneRegistration);
            System.out.println("Registration Sent");

            // Create a timer to send the drone position to the server every 10 seconds
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    try {
                        // Get the current drone position
                        DronePosition currentPosition = new DronePosition(initialX, initialY);
                        out.writeObject(currentPosition);
                        System.out.println("Position Sent: " + currentPosition);

                        // Check if there is any fire detected
                        FireDetector.fireDetected(currentPosition);
                    } catch (IOException e) {
                        System.err.println("Error sending position: " + e.getMessage());
                    }
                }
            }, SEND_POSITION_INTERVAL, SEND_POSITION_INTERVAL);

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
