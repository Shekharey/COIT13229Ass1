package assesment.drones;
//Shekhar Sharma (12134685)

import java.io.Serializable;
import java.util.ArrayList;



public class FireDetector implements Serializable{
    private static final int FIRE_DISTANCE_THRESHOLD = 10;

    public static void fireDetected(DronePosition currentPosition) {
        double distanceToFire = calculateDistanceToFire(currentPosition);
        if (distanceToFire <= FIRE_DISTANCE_THRESHOLD) {
            System.out.println("FIRE DETECTED! Distance to fire: " + distanceToFire);
        }
    }

    private static double calculateDistanceToFire(DronePosition currentPosition) {
        // Calculate the distance between the drone's current position and the fire location
        DronePosition firePosition = new DronePosition(125, 130); 
        int dx = currentPosition.getX() - firePosition.getX();
        int dy = currentPosition.getY() - firePosition.getY();
        return Math.sqrt(dx*dx + dy*dy);
    }

    static ArrayList<FireAlert> checkForFire(DronePosition currentPosition) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}