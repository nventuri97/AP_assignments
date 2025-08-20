/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uni.adavanced_programming.matchingpairsgame;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import javax.swing.JLabel;

/**
 *
 * @author nicola
 */
public class Controller extends JLabel implements VetoableChangeListener{
    
    public Controller(){
        super();
    }

    @Override
    public void vetoableChange(PropertyChangeEvent pce) throws PropertyVetoException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
