/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uni.adavanced_programming.the8puzzle;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyVetoException;
import java.io.Serializable;
import javax.swing.JButton;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 *
 * @author nicola
 */
public class FlipButton extends JButton implements Serializable, PropertyChangeListener{

    private final static Logger logger=LogManager.getLogger(FlipButton.class);
    
    public FlipButton(){
        super();
    }
    
    /**
     * This method fires a VetoableChange flip event if it receives a flip event
     * @param pce PropertyChangeEvent received
     */
    @Override
    public void propertyChange(PropertyChangeEvent pce) {
        try {
            switch(pce.getPropertyName()){
                case "flip":
                    this.fireVetoableChange("flip", pce.getOldValue(), pce.getNewValue());
                    break;
                default: 
                    break;
            }
        } catch (PropertyVetoException ex) {
            logger.error("Flip is not possibile, the hole is not in position 9");
        }
    }
    
}
