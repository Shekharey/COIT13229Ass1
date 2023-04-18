package assesment.assesment1;
import java.io.Serializable;
/**
 *
 * @author Asus
 */
public class Position implements Serializable {
    private double x;
    private double y;
    
    //getters
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
    
    //setters

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }
    
    //constructors

    public Position(double x, double y) {
        this.x = x;
        this.y = y;
    }

Position position = new Position(0, 0);
}

