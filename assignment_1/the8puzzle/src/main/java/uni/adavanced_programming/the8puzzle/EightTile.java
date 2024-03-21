/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uni.adavanced_programming.the8puzzle;

import java.awt.Color;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.JButton;

/**
 *
 * @author nicola
 */
public class EightTile extends JButton implements Serializable, PropertyChangeListener {
    
    private int position;
    private String label;
    
    public EightTile(){
        super();
    }
    
    public EightTile(int position, String label){
        super();
        this.position=position;
        this.label=label;
        
        this.changeProperty(label);
    }

    @Override
    public void propertyChange(PropertyChangeEvent pce) {
        if(pce.getPropertyName().equals("restart")){
            ArrayList<Integer> newConfiguration=(ArrayList<Integer>) pce.getNewValue();
            this.setLabel(newConfiguration.get(position-1).toString());
        }
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
        
        this.changeProperty(label);
    }
    
    private void changeProperty(String label){
        if(this.label.equals("9")){
            this.setText(" ");
            this.setBackground(Color.GRAY);
        } else{
            this.setText(label);
            if(Integer.parseInt(label)==position)
                this.setBackground(Color.GREEN);
            else
                this.setBackground(Color.YELLOW);
        } 
    }
}
