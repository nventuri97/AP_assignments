/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uni.adavanced_programming.the8puzzle;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.JLabel;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 *
 * @author nicola
 */
public class EightController extends JLabel implements Serializable, VetoableChangeListener, PropertyChangeListener{
    
    private final static Logger logger=LogManager.getLogger(EightController.class);
    private ArrayList<Integer> currentBoardConfiguration=new ArrayList<Integer>();
    private PropertyChangeSupport support=new PropertyChangeSupport(this);
    
    public EightController(){
        super();
    }

    @Override
    public void vetoableChange(PropertyChangeEvent pce) throws PropertyVetoException {
        switch(pce.getPropertyName()){
            case "swap":
                if(pce.getNewValue().toString().equals(pce.getOldValue().toString()) || !this.isAdjacent(pce.getOldValue().toString())){
                    this.setText("KO");
                    throw new PropertyVetoException("Invalid move", pce);
                } else {
                    this.setText("OK"); 
                    for(var listener: support.getPropertyChangeListeners()){
                        listener.propertyChange(new PropertyChangeEvent(support, "swap", pce.getNewValue().toString(), pce.getOldValue().toString()));
                    }
                }
                break;
            case "flip":
                int firstLabel=Integer.parseInt(pce.getOldValue().toString());
                int secondLabel=Integer.parseInt(pce.getNewValue().toString());
                
                if(this.currentBoardConfiguration.indexOf(9)==8){
                    for(var listener : support.getPropertyChangeListeners()){
                        listener.propertyChange(new PropertyChangeEvent(support, pce.getPropertyName(), pce.getOldValue().toString(), pce.getNewValue().toString()));
                    }
                    currentBoardConfiguration.set(0, secondLabel);
                    currentBoardConfiguration.set(1, firstLabel);
                } else {
                    throw new PropertyVetoException("Flip is vetoed", pce);
                }
                break;
            default: break;
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent pce) {
        
    }
    
     public ArrayList<Integer> getCurrentBoardConfiguration() {
        return currentBoardConfiguration;
    }

    public void setCurrentBoardConfiguration(ArrayList<Integer> currentBoardConfiguration) {
        this.currentBoardConfiguration = currentBoardConfiguration;
    }
    
    
    public boolean isAdjacent(String label){
        int tilePosition=currentBoardConfiguration.indexOf(Integer.parseInt(label));
        int holePosition=currentBoardConfiguration.indexOf(9);
        
        // Calculate row and column indices of the tile and the hole.
        int tileRow = tilePosition / 3;
        int tileCol = tilePosition % 3;
        int holeRow = holePosition / 3;
        int holeCol = holePosition % 3;
        
        boolean adjacent=Math.abs(tileRow - holeRow) + Math.abs(tileCol - holeCol) == 1;
        if(adjacent){
            currentBoardConfiguration.set(holePosition, Integer.parseInt(label));
            currentBoardConfiguration.set(tilePosition, 9);
        }
        
        return adjacent;
    }
    
    public void addControllerPropertyChangeListener(PropertyChangeListener list){
        support.addPropertyChangeListener(list);
    }
}
