package assesment.assesment1;
//Shekhar Sharma (12134685)


public class FireDetector {
    private static final int FIRE_DISTANCE_THRESHOLD = 10;

    public static void fireDetected(DronePosition currentPosition) {
        double distanceToFire = calculateDistanceToFire(currentPosition);
        if (distanceToFire <= FIRE_DISTANCE_THRESHOLD) {
            System.out.println("FIRE DETECTED! Distance to fire: " + distanceToFire);
            // Call the fire department or take any other necessary action
        }
    }

    private static double calculateDistanceToFire(DronePosition currentPosition) {
        // Calculate the distance between the drone's current position and the fire location
        DronePosition firePosition = new DronePosition(50, 50); // This is just a sample location, replace with actual fire location
        int dx = currentPosition.getX() - firePosition.getX();
        int dy = currentPosition.getY() - firePosition.getY();
        return Math.sqrt(dx*dx + dy*dy);
    }
}