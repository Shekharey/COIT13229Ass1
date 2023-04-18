
import java.io.Serializable;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Asus
 */
public class Position implements Serializable{
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
    
}
