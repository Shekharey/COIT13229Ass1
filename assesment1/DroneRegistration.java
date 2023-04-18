/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assesment.assesment1;

import java.io.Serializable;
import javax.swing.text.Position;

/**
 *
 * @author Asus
 */
public class DroneRegistration implements Serializable{
    private String Name;
    private Integer ID;
    private Position position;

    public void setName(String Name) {
        this.Name = Name;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public String getName() {
        return Name;
    }

    public Integer getID() {
        return ID;
    }

    public Position getPosition() {
        return position;
    }

    public DroneRegistration(String Name, Integer ID, Position position) {
        this.Name = Name;
        this.ID = ID;
        this.position = position;
    }
}   

