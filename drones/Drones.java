package assesment.drones;
//Shekhar Sharma (12134685)
import assesment.drones.DronePosition;
import assesment.drones.DroneRegistration;
import assesment.drones.FireAlert;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Drones {
    private static ArrayList<DroneRegistration> registeredDrones = new ArrayList<>();
    private static ArrayList<DronePosition> currentPositions = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        int portNumber = 7896;
        ServerSocket serverSocket = new ServerSocket(portNumber);
        System.out.println("Server started on port " + portNumber);

        while (true) {
            try {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected: " + clientSocket);

                // Handle client request in a new thread
                new Thread(new ClientHandler(clientSocket)).start();

            } catch (IOException e) {
                System.err.println("Error accepting client connection: " + e.getMessage());
            }
        }
    }

    private static class ClientHandler implements Runnable {
        private final Socket clientSocket;

        public ClientHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        @Override
        public void run() {
            try (
                ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream())
            ) {
                // Receive drone registration or position update from the client
                Object inputObject = in.readObject();
                if (inputObject instanceof DroneRegistration) {
                    // Handle drone registration
                    DroneRegistration registration = (DroneRegistration) inputObject;
                    registeredDrones.add(registration);
                    System.out.println("New drone registered: " + registration);

                    // Send the drone ID back to the client
                    out.writeObject(registration.getID());
                } else if (inputObject instanceof DronePosition) {
                    // Handle drone position update
                    DronePosition currentPosition = (DronePosition) inputObject;
                    int index = getDroneIndex(currentPosition);
                    if (index != -1) {
                        currentPositions.set(index, currentPosition);
                        System.out.println("Received position update from drone: " + registeredDrones.get(index).getID() + " " + currentPosition);

                        // Check for fire and send fire alert to the client if detected
                        ArrayList<FireAlert> fireAlerts = FireAlert.checkForFire(currentPositions);
                        if (!fireAlerts.isEmpty()) {
                            out.writeObject(fireAlerts);
                            System.out.println("Sent fire alert to drone: " + registeredDrones.get(index).getID());
                        }
                    }
                } else {
                    System.err.println("Received unexpected object from client");
                }
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Error handling client request: " + e.getMessage());
            } finally {
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    System.err.println("Error closing client socket: " + e.getMessage());
                }
            }
        }

        private int getDroneIndex(DronePosition currentPosition) {
            for (int i = 0; i < registeredDrones.size(); i++) {
                if (registeredDrones.get(i).getInitialPosition().getX() == currentPosition.getX() &&
                    registeredDrones.get(i).getInitialPosition().getY() == currentPosition.getY()) {
                    return i;
                }
            }
            return -1;
        }
    }
}
