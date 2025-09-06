/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uni.adavanced_programming.the8puzzle;

import java.awt.Color;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyVetoException;
import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.Timer;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 *
 * @author nicola
 */
public class EightTile extends JButton implements Serializable, PropertyChangeListener {
    
    private final static Logger logger=LogManager.getLogger(EightTile.class);
    //Position on the board 
    private int position;
    //Actual label of the tile
    private String label;
    private String debugMsg;
    
    public EightTile(){
        super();
    }
    
    /**
     * Constructor with parameters
     * @param position position on the board passed by the board itself
     * @param label label set by the board through the random initial configuration array
     */
    public EightTile(int position, String label){
        super();
        this.position=position;
        this.label=label;
        
        this.debugMsg="EightTile"+position+" --- ";
        //Change the graphical aspect of the tile
        this.changeProperty(label);
    }
    
    /**
     * This method handles the PropertyChangeEvent received in four different ways
     * 1. restart: when the restart button is clicked and a new configuration is generated, the tile takes its own value and update its label;
     * 2. click: when the tile is clicked, it has to fire a new Vetoable Change event with "swap" and set its label if the EightController doesn't veto the change;
     * 3. swap: when the tile received an answer of a swap event from the EightController that involves it in the swap, set the new label;
     * 4. flip: when the flip button is clicked and it changes its label value
     * @param pce the property change event received
     */
    @Override
    public void propertyChange(PropertyChangeEvent pce) {
        try{
            switch (pce.getPropertyName()){
                case "restart":
                    ArrayList<Integer> newConfiguration=(ArrayList<Integer>) pce.getNewValue();
                    this.setLabel(newConfiguration.get(position-1).toString());
                    break;
                case "click":
                    logger.debug(debugMsg+"Handling click event, changing label value if not vetoed");
                    this.fireVetoableChange("swap", pce.getOldValue().toString(), pce.getNewValue().toString());

                    this.setLabel(pce.getNewValue().toString());
                    break;
                //Maybe this is wrong,  we have to understand how to notify the changes
                case "swap":
                    if(pce.getOldValue().equals(this.label)){
                        logger.debug(debugMsg+"Handling swap event, setting new label");
                        logger.debug(debugMsg+"Actual label is "+this.label);
                        logger.debug(debugMsg+"New label is "+pce.getNewValue().toString());
                        this.setLabel(pce.getNewValue().toString());
                    }
                    break;
                case "flip":
                    if(pce.getOldValue().equals(this.label)){
                        this.setLabel(pce.getNewValue().toString());
                    } else if(pce.getNewValue().equals(this.label)){
                        this.setLabel(pce.getOldValue().toString());
                    }
                default:
                    break;
            }
        }catch(PropertyVetoException ex){
            logger.debug("Change is vetoed, it's not a valid move");
            this.setBackground(Color.RED);
            new Timer(500, e -> changeProperty(this.label)).start();
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
    
    /**
     * Private function that manages the graphical aspect of the tile
     * @param label the tile label to set
     */
    private void changeProperty(String label){
        logger.debug(debugMsg+"Received new label to update value and background color");
        if(this.label.equals("9")){
            this.setText(" ");
            this.setBackground(Color.GRAY);
        } else {
            this.setText(label);
            if(Integer.parseInt(label)==position)
                this.setBackground(Color.GREEN);
            else
                this.setBackground(Color.YELLOW);
        } 
    }
}
