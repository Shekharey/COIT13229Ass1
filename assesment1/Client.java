package assesment.assesment1;
import java.net.Socket;
import java.io.*;
import java.util.ArrayList;

/**
 *
 * @author Asus
 */
public class Client{
	public static void main(String args[]) {
		Socket s = null;

		String serverIp = "localhost";
		int serverPort = 7896;

                // create a new DroneRegistration object
DroneRegistration drone = new DroneRegistration("Drone 1", 12345, 0.0, 0.0);

// create a new Position object
Position position = new Position(0.0, 0.0);

// create a new socket and connect to the server
Socket socket = new Socket("localhost", 12345);
		try {
			System.out.println("Start to connect to the server: ");
			s = new Socket(serverIp, serverPort);

			ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());

			System.out.println("Start to send a drone registration to the server: ");
			out.writeObject(drone);
			System.out.println("Data sent.");

			ObjectInputStream in = new ObjectInputStream(s.getInputStream());

			DroneRegistration receivedDrone = (DroneRegistration) in.readObject();
			System.out.println("Drone name received from the server: " + receivedDrone.getName());
			System.out.println("Drone ID received from the server: " + receivedDrone.getID());
			System.out.println("Drone position received from the server: " + receivedDrone.getPosition());
			
		} catch (ClassNotFoundException e) {
			System.out.println("Error: " + e.getMessage());
		} catch (EOFException e) {
			System.out.println("EOF:" + e.getMessage());
		} catch (IOException e) {
			System.out.println("IO:" + e.getMessage());
		} finally {
			if (s != null)
				try {
					s.close();
				} catch (IOException e) {
					System.out.println("close:" + e.getMessage());
				}
		}
	}
}
