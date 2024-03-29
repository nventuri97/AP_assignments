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
    private int position;
    private String label;
    private String debugMsg;
    
    public EightTile(){
        super();
    }
    
    public EightTile(int position, String label){
        super();
        this.position=position;
        this.label=label;
        
        this.debugMsg="EightTile"+position+" --- ";
        this.changeProperty(label);
    }

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
