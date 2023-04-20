package assesment.drones;
//Shekhar Sharma (12134685)
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Drones{
    private static ArrayList<DroneRegistration> droneRegistrations = new ArrayList<>();

    public static void main(String[] args) {
        try {
            int serverPort = 7896;
            ServerSocket serverSocket = new ServerSocket(serverPort);
            System.out.println("Server started. Waiting for clients...");

            while (true) {
                // Accept incoming client connections
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress().getHostAddress());

                // Handle each client in a separate thread
                Thread t = new Thread(new ClientHandler(clientSocket));
                t.start();
            }

        } catch (IOException e) {
            System.err.println("Server error: " + e.getMessage());
        }
    }

    // Thread class to handle each client connection
    private static class ClientHandler implements Runnable {
        private Socket clientSocket;

        public ClientHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        @Override
        public void run() {
            try {
                ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
                ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());

                // Receive drone registration from client
                DroneRegistration droneRegistration = (DroneRegistration) in.readObject();
                System.out.println("Drone registered: " + droneRegistration);

                // Add drone registration to the list of registered drones
                synchronized (droneRegistrations) {
                    droneRegistrations.add(droneRegistration);
                }

                // Receive drone position updates from client
                while (true) {
                    DronePosition currentPosition = (DronePosition) in.readObject();
                    System.out.println("Drone position update: " + currentPosition);

                    // Check for any fires near the drone
                    ArrayList<FireAlert> alerts = FireDetector.checkForFire(currentPosition);

                    // Send fire alerts back to the client
                    synchronized (out) {
                        out.writeObject(alerts);
                        out.flush();
                    }
                }

            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Client error: " + e.getMessage());
            } finally {
                try {
                    // Remove the drone registration from the list when the client disconnects
                    clientSocket.close();
                    synchronized (droneRegistrations) {
                    }
                } catch (IOException e) {
                    System.err.println("Error closing client socket: " + e.getMessage());
                }
            }
        }
    }
}
